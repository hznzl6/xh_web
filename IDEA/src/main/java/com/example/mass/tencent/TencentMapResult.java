package com.example.mass.tencent;

import lombok.Data;

@Data
public class TencentMapResult <T>{

    /**
     * 状态码，0为正常，其它为异常
     */
    private Integer status;

    /**
     * 状态说明
     */
    private String message;

    /**
     * 本次请求的唯一标识
     */
    private String request_id;

    /**
     * 查询结果总数量
     */
    private Integer count;

    /**
     * 返回数据 > 数组
     */
    private T data;

    /**
     * 返回数据 > 对象
     */
    private T result;
}
