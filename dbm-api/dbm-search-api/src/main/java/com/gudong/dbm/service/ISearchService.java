package com.gudong.dbm.service;

import com.github.pagehelper.PageInfo;
import com.gudong.dbm.entity.Product;
import com.gudong.dbm.pojo.PageResultBean;
import com.gudong.dbm.pojo.ResultBean;

import java.io.IOException;
import java.util.List;

/**
 * @author gudong
 * @description
 * @date 2021-06-01 11:04
 */
public interface ISearchService {

    ResultBean initAllData();

    ResultBean<List<Product>> searchByKeywords(String keyword);

    PageResultBean<Product> searchByKeywords(String keyword, Integer index, Integer size);
}
