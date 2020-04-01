package com.dute7liang.pay.tool.demo.response;

import lombok.Data;

/**
 * @author: zl
 * @Date: 2020-4-1 14:21
 */
@Data
public class Result<T> {

    private int status;
    private String msg;
    private boolean success;

    private T t;

    public static <T> Result success(T t){
        Result result = new Result();
        result.setStatus(200);
        result.setSuccess(true);
        result.setT(t);
        return result;
    }

}
