package com.lrichiewl.seckill.exception;

/**
 * 抢购相关的所有异常
 * @author Ryan Lu
 * @date 2019-05-12 17:53
 */
public class SeckillException extends RuntimeException{

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
