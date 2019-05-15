package com.lrichiewl.seckill.dto;

/**
 * 所有AJAX请求返回类型，封装JSON结果
 * @author Ryan Lu
 * @date 2019-05-15 13:56
 */
public class SeckillResult<T> {
    private boolean success;
    private  T data;
    private  String error;

    public SeckillResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public SeckillResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
