package com.gudong.dbmproductservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gudong.dbm.base.BaseServiceImpl;
import com.gudong.dbm.base.IBaseDao;
import com.gudong.dbm.entity.ProductType;
import com.gudong.dbm.mapper.ProductTypeMapper;
import com.gudong.dbm.service.IProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description
 * @author gudong
 * @date 2021-05-26 9:22
 */
@Component
@Service
public class ProductTypeService extends BaseServiceImpl<ProductType> implements IProductTypeService {

    @Autowired
    ProductTypeMapper productTypeMapper;

    @Override
    public IBaseDao<ProductType> getBaseDao() {
        return productTypeMapper;
    }


    @Override
    public PageInfo<ProductType> page(Integer num, Integer size) {
        PageHelper.startPage(num, size);
        List<ProductType> list = productTypeMapper.list();
        PageInfo<ProductType> pageInfo = new PageInfo<>(list, 3);
        return pageInfo;
    }


}
