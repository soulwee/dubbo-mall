package com.gudong.dbm.service;

import com.github.pagehelper.PageInfo;
import com.gudong.dbm.base.IBaseService;
import com.gudong.dbm.entity.ProductType;

import java.util.List;

/**
 * @description
 * @author gudong
 * @date 2021-05-26 14:02
 */
public interface IProductTypeService extends IBaseService<ProductType> {


    PageInfo<ProductType> page(Integer num, Integer size);

}
