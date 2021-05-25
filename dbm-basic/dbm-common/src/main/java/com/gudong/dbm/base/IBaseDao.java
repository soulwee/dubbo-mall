package com.gudong.dbm.base;

/**
 * @Description
 * @Author gudong
 * @Date 2021-05-25 15:19
 */
public interface IBaseDao<T> {
    int deleteByPrimaryKey(Integer id);

    int insert(T t);

    int insertSelective(T t);

    T selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(T t);

    int updateByPrimaryKey(T t);
}
