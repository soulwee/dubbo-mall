package com.gudong.dbm.service;

import com.github.pagehelper.PageInfo;
import com.gudong.dbm.base.IBaseService;
import com.gudong.dbm.entity.Product;
import com.gudong.dbm.entity.ProductType;
import com.gudong.dbm.vo.ProductVo;

import java.util.List;

/**
 * @description
 * @author gudong
 * @date 2021-05-26 9:13
 */
public interface IProductService extends IBaseService<Product> {

    PageInfo<Product> page(Integer num, Integer size);

    Long add(ProductVo vo);
}
