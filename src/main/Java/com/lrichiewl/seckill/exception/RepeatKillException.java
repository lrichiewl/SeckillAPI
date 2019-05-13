package com.lrichiewl.seckill.exception;

import com.lrichiewl.seckill.dto.SeckillExecution;

/**
 * 定义重复下单异常，是运行期异常。
 * @author Ryan Lu
 * @date 2019-05-12 15:36
 */
public class RepeatKillException extends SeckillException{

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
