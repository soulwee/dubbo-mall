package com.gudong.dbmproductservice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gudong.dbm.base.BaseServiceImpl;
import com.gudong.dbm.base.IBaseDao;
import com.gudong.dbm.entity.Product;
import com.gudong.dbm.entity.ProductDesc;
import com.gudong.dbm.entity.ProductType;
import com.gudong.dbm.mapper.ProductDescMapper;
import com.gudong.dbm.mapper.ProductMapper;
import com.gudong.dbm.service.IProductService;
import com.gudong.dbm.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author gudong
 * @description
 * @date 2021-05-27 9:34
 */
@Service
@Component
public class ProductService extends BaseServiceImpl<Product> implements IProductService {

    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductDescMapper productDescMapper;
    @Override
    public IBaseDao<Product> getBaseDao() {
        return productMapper;
    }

    @Override
    public PageInfo<Product> page(Integer num, Integer size) {
        PageHelper.startPage(num, size);
        List<Product> list = productMapper.list();
        PageInfo<Product> pageInfo = new PageInfo<>(list, 3);
        return pageInfo;
    }

    @Override
    public void add(ProductVo vo) {
        Product prod = vo.getProd();
        prod.setFlag(true);
        prod.setCreateTime(new Date());
        prod.setUpdateTime(prod.getCreateTime());
        prod.setCreateUser(1L);
        prod.setUpdateUser(prod.getCreateUser());
        productMapper.insertSelective(prod);
        ProductDesc desc = new ProductDesc(vo.getProd().getId(),vo.getDesc());
        productDescMapper.insertSelective(desc);
    }
}
