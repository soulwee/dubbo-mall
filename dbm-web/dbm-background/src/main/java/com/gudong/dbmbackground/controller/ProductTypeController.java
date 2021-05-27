package com.gudong.dbmbackground.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.gudong.dbm.entity.ProductType;
import com.gudong.dbm.service.IProductTypeService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description
 * @author gudong
 * @date 2021-05-26 9:13
 */
@RequestMapping("productType")
@RestController
public class ProductTypeController {

    @Reference
    IProductTypeService productTypeService;

    @GetMapping("list")
    public List<ProductType> list(){
        List<ProductType> list = productTypeService.list();
        return list;
    }

    @GetMapping("page/{num}/{size}")
    public PageInfo<ProductType> list(@PathVariable("num")Integer num, @PathVariable("size") Integer size){
        PageInfo<ProductType> pageInfo = productTypeService.page(num,size);
        return pageInfo;
    }

}
