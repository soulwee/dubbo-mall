package com.gudong.dbm.base;

import java.util.List;

/**
 * @description
 * @author gudong
 * @date 2021-05-25 15:19
 */
public interface IBaseDao<T> {
    int deleteByPrimaryKey(Integer id);

    int insert(T t);

    int insertSelective(T t);

    T selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(T t);

    int updateByPrimaryKey(T t);

    List<T> list();
}