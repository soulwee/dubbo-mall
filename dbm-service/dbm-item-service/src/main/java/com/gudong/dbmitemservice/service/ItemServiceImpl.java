package com.gudong.dbmitemservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.gudong.dbm.entity.Product;
import com.gudong.dbm.mapper.ProductMapper;
import com.gudong.dbm.pojo.ResultBean;
import com.gudong.dbm.service.IItemService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author gudong
 * @description
 * @date 2021-06-02 16:41
 */
@Component
@Service
public class ItemServiceImpl implements IItemService {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    Configuration configuration;

    @Value("${html.path}")
    String htmlPath;
/*
    //JDK提供了线程池
    //1.单例线程池，特点是只有线程，保证我们提交给他的任务按顺序执行
    //存在隐患:队列太长了Integer.MAX_VALUE
    // OOM内存溢出
    private ExecutorService pool = Executors.newSingleThreadExecutor();
    // 2.定长线程池
    //存在隐患:队列太长了Integer.MAX_VALUE
    // OOM内存溢出
    private ExecutorService pool2 = Executors.newFixedThreadPool(10);
    //3.线程的数量只受限于内存
    //存在隐患:创建的线程数太多 Integer.MAX_VALUE
    // OOM内存溢出
    private ExecutorService pool3 = Executors.newCachedThreadPool();
    //线程池的关键参数
    //初始化线程池数(4)，最大线程数(8)，发呆时间(3000)，等待队列(100)
    // 4个忙不过来，排队
    //队满了，创建新的线程（最大线程数）
    */
    private final int corePoolSize = Runtime.getRuntime().availableProcessors();
    ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("demo-pool-%d").build();
    //自定义名字的线程池 要使用带有ThreadFactory参数的ThreadPoolExecutor构造方法哦,这样你就可以方便的设置线程名字啦
    ExecutorService pool = new ThreadPoolExecutor(corePoolSize,corePoolSize*2,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(100), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    //结合真实服务器硬件条件来设置

    //结论就是自己来创建线程池
   // private ExecutorService pool = new ThreadPoolExecutor( corePoolSize,corePoolSize*2,0L,
    // TimeUnit.SECONDS,new LinkedBlockingQueue(100));

    @Override
    public ResultBean createHtmlById(Long id) {
        Product product = productMapper.selectByPrimaryKey(id);
        Template template = null;
        try {
            template = configuration.getTemplate("item.ftl");
            Map<String, Object> data = new HashMap<>();
            data.put("product", product);
            String serverpath = ResourceUtils.getURL("classpath:static").getPath();
            //String serverpath = htmlPath;
            FileWriter out = new FileWriter(serverpath + File.separator + id + ".html");
            template.process(data, out);
            System.out.println(Thread.currentThread().getName()+"在target目录下，生成静态页面成功！ ");
        } catch (Exception e) {
            System.out.println("出错:"+id);
            e.printStackTrace();
        }
        return new ResultBean();
    }

    @Override
    public ResultBean BatchCreateHtmlById(List<Long> idList) {
        System.out.println("核心线程数："+corePoolSize);
        List<Future<Boolean>> list = new ArrayList<>();
        idList.forEach(id->{
            Future<Boolean> future = pool.submit(new CreateHtmlTask(id));
            list.add(future);
        });
        list.forEach(f->{
            try {
                System.out.println(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });
        return new ResultBean();
    }

    class CreateHtmlTask implements Callable<Boolean> {
        private Long proId;

        public CreateHtmlTask() {

        }

        public CreateHtmlTask(Long proId) {
            this.proId = proId;
        }

        @Override
        public Boolean call() throws Exception {
            createHtmlById(proId);
            return true;
        }
    }
}
