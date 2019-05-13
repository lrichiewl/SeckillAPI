package com.lrichiewl.seckill.service;

import com.lrichiewl.seckill.dto.Exposer;
import com.lrichiewl.seckill.dto.SeckillExecution;
import com.lrichiewl.seckill.entity.Seckill;
import com.lrichiewl.seckill.exception.RepeatKillException;
import com.lrichiewl.seckill.exception.SeckillCloseException;
import com.lrichiewl.seckill.exception.SeckillException;

import java.util.List;

/**
 * @author Ryan Lu
 * @date 2019-05-12 14:48
 */
public interface SeckillService {

    /**
        * @description: 查询所有抢购记录
        * @author lwl
        * @return java.util.List<com.lrichiewl.seckill.entity.Seckill>
        * @date : 2019-05-12 14:56
     */
    List<Seckill> getSeckillList();

    /**
        * @description: 查询一条抢购记录
        * @param seckillId
        * @author lwl
        * @return com.lrichiewl.seckill.entity.Seckill
        * @date : 2019-05-12 14:59
     */
    Seckill getById(long seckillId);

    /**
        * @description: 抢购开始时输出抢购接口地址；如果还没开始抢购，则输出系统时间和抢购时间。
        * @param seckillId
        * @author lwl
        * @return Exposer
        * @date : 2019-05-12 15:00
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
        * @description: 执行抢购操作
        * @param seckillId
    	* @param userPhoneNum
    	* @param md5
        * @author lwl
        * @return SeckillExecution
        * @date : 2019-05-12 15:12
     */
    SeckillExecution executeSeckill(long seckillId, long userPhoneNum, String md5)
            throws SeckillException, SeckillCloseException, RepeatKillException;
}
