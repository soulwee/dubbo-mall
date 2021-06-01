package com.gudong.dbm.pojo;

import com.gudong.dbm.base.IBaseService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author gudong
 * @description
 * @date 2021-05-28 14:00
 */
@Data
@AllArgsConstructor
public class ResultBean<T> implements Serializable {
    private T data;
    private String msg;
    private Integer code;

    public ResultBean() {
        this.msg = "success";
        this.code = 200;
    }

    public ResultBean(Integer code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public ResultBean<T> ok(T data) {
        this.data = data;
        return this;
    }

    public ResultBean<T> error(String msg) {
        this.msg = msg;
        this.code = 500;
        return this;
    }
}
