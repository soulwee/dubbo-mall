package com.gudong.dbmitem;

import com.gudong.dbmitem.entity.Product;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class DbmItemApplicationTests {

	@Autowired
	Configuration configuration;

	@Test
	void createHTMLTest() throws IOException, TemplateException {
		//模板+数据=输出
		//1.获取到模板对象
		Template template = configuration.getTemplate("freemarker.ftl");
		//2.组装数据
		Map<String,Object> data = new HashMap<>();
		data.put("username","ftl");
		//保存对象
		Product product = new Product("3天带你精通Java",1999L,new Date());
		data.put("product",product);
		//保存集合
		List<Product> list = new ArrayList<>();
		list.add(new Product("1天带你精通MySQL",999L,new Date()));
		list.add(new Product("7天带你精通jQuery",888L,new Date()));
		data.put("list",list);
		//保存当前的财富
		data.put("money",100000);
		//3.模板+数据结合
		FileWriter fileWriter = new FileWriter(
				"D:\\IdeaMyPros\\dubbo-mall\\dbm-web\\dbm-item\\src\\main\\resources\\static\\f.html");
		template.process(data,fileWriter);

		System.out.println("生成静态页面成功！");
	}

}
