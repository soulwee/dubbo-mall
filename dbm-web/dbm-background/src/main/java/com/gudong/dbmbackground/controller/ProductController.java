package com.gudong.dbmbackground.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.gudong.dbm.constant.RabbitMQConstant;
import com.gudong.dbm.entity.Product;
import com.gudong.dbm.service.IItemService;
import com.gudong.dbm.service.IProductService;
import com.gudong.dbm.service.ISearchService;
import com.gudong.dbm.vo.ProductVo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author gudong
 * @description
 * @date 2021-05-27 9:36
 */
@Controller
@RequestMapping("product")
public class ProductController {

    @Reference
    IProductService productService;
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Reference
    ISearchService searchService;

    @Reference
    IItemService itemService;

    @GetMapping("list")
    public String list(Model model){
        List<Product> list = productService.list();
        model.addAttribute("list", list);
        return "product/list";
    }

    @GetMapping("page/{num}/{size}")
    public String list(Model model,
                       @PathVariable("num")Integer num,
                       @PathVariable("size") Integer size){
        PageInfo<Product> pageInfo = productService.page(num,size);
        model.addAttribute("page", pageInfo);
        return "product/list";
    }

    @PostMapping("add")
    public String add(ProductVo vo){
        System.out.println(vo);
        Long id = productService.add(vo);
        System.out.println("pid:"+id);
        //发送消息到交换机，搜索服务接收消息在solr添加关键字，item服务接收消息生成静态商品详情页面  解耦
        rabbitTemplate.convertAndSend(RabbitMQConstant.BACKGROUND_EXCHANGE, "product.add",id);

      /*  searchService.updateById(id);
        itemService.createHtmlById(id);*/
        return "redirect:/product/page/1/5";
    }

}
