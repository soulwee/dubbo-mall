package com.gudong.dbm.vo;

import com.gudong.dbm.entity.Product;

import java.io.Serializable;

/**
 * @author gudong
 * @description
 * @date 2021-05-27 11:55
 */
public class ProductVo implements Serializable {
    private Product prod;
    private String desc;

    public Product getProd() {
        return prod;
    }

    public void setProd(Product prod) {
        this.prod = prod;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "ProductVo{" +
                "product=" + prod +
                ", desc='" + desc + '\'' +
                '}';
    }
}
