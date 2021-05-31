package com.gudong.dbm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * @author gudong
 * @description
 * @date 2021-05-31 10:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MultiResultBean implements Serializable {
    private Integer errno;
    private List<String> data;

    public MultiResultBean(Integer errno) {
        this.errno = errno;
    }
}
