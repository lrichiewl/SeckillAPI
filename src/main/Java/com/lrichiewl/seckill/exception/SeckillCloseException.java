package com.lrichiewl.seckill.exception;

import com.lrichiewl.seckill.dto.SeckillExecution;

/**
 * 抢购关闭（超时或库存为0）异常
 * @author Ryan Lu
 * @date 2019-05-12 17:51
 */
public class SeckillCloseException extends SeckillException {

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
