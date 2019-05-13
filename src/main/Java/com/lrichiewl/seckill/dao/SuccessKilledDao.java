package com.lrichiewl.seckill.dao;

import com.lrichiewl.seckill.entity.SuccessSeckilled;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;

/**
 * @author Ryan Lu
 * @date 2019-05-08 13:38
 */
public interface SuccessKilledDao {

    /**
        * @description: 插入购买明细（结果集），使用联合主键，可以过滤重复
        * @param seckillId 1
    	* @param userPhoneNum 2
        * @author lwl
        * @return int;插入的行数
        * @date : 2019-05-08 13:46
     */
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhoneNum") long userPhoneNum) ;

    /**
        * @description: 根据ID查询SuccessSeckilled下单记录，并携带产品对象实体(即它所携带的数据)
        * @param seckillId
        * @author lwl
        * @return com.lrichiewl.seckill.entity.SuccessSeckilled
        * @date : 2019-05-08 13:49
     */
    SuccessSeckilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhoneNum") long userPhoneNum) ;
}
