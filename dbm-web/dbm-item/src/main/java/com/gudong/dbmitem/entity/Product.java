package com.gudong.dbmitem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @author gudong
 * @description
 * @date 2021-06-02 16:24
 */
@Data
@AllArgsConstructor
public class Product {

    private String name;

    private Long price;

    private Date createTime;

}
