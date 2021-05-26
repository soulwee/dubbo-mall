package com.gudong.dbmbackground.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.gudong.dbm.entity.ProductType;
import com.gudong.dbm.service.IProductService;
import com.gudong.dbm.service.IProductTypeService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    IProductTypeService productService;

    @GetMapping("list")
    public List<ProductType> list(){
        List<ProductType> list = productService.list();
        return list;
    }

}
