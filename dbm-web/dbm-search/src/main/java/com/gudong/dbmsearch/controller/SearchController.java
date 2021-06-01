package com.gudong.dbmsearch.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gudong.dbm.entity.Product;
import com.gudong.dbm.pojo.PageResultBean;
import com.gudong.dbm.pojo.ResultBean;
import com.gudong.dbm.service.ISearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author gudong
 * @description
 * @date 2021-06-01 11:08
 */
@Controller
@RequestMapping("search")
public class SearchController {
    @Reference
    ISearchService searchService;

    @RequestMapping("initAllData")
    @ResponseBody
    public ResultBean init(){
        return searchService.initAllData();
    }

    @RequestMapping("keyword")
    @ResponseBody
    public ResultBean<List<Product>> keyword(String keyword){
        return searchService.searchByKeywords(keyword);
    }

    @RequestMapping("keywordList")
    public String keywordList(Model model,String keyword, Integer index, Integer size){
        PageResultBean<Product> pageInfo = searchService.searchByKeywords(keyword,index,size);
        model.addAttribute("page", pageInfo);
        return "list";
    }
}
