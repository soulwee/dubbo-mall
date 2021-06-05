package com.gudong.dbm.service;

import com.gudong.dbm.pojo.ResultBean;

import java.util.List;

/**
 * @author gudong
 * @description
 * @date 2021-06-02 16:37
 */
public interface IItemService {
    ResultBean createHtmlById(Long id);

    ResultBean BatchCreateHtmlById(List<Long> idList);
}
