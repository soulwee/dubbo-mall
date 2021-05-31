package com.gudong.dbmindex.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gudong.dbm.entity.ProductType;
import com.gudong.dbm.service.IProductTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author gudong
 * @description
 * @date 2021-05-31 14:09
 */
@Controller
@RequestMapping("index")
public class IndexController {

    @Reference
    IProductTypeService productTypeService;

    @RequestMapping("")
    public String index(Model model){
        List<ProductType> list = productTypeService.list();
        model.addAttribute("list", list);
        return "index";
    }

}
