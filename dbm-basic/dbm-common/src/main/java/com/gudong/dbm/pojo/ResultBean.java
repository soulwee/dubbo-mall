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
public class ResultBean implements Serializable {
    private Object data;
    private String msg;
    private Integer code;

    public ResultBean() {
        this.msg = "success";
        this.code = 200;
    }

    public ResultBean ok(Object data){
        this.data = data;
        return this;
    }

    public ResultBean error(String msg){
        this.msg = msg;
        this.code = 500;
        return this;
    }
}
