package com.lrichiewl.seckill.dao;

import com.lrichiewl.seckill.entity.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author Ryan Lu
 * @date 2019-05-08 00:17
 */

public interface SeckillDao {

    /**
        * @description: 进行库存减少操作
        * @param seckillId 1
        * @param killTime 2 抢购开始时间
        * @author lwl
        * @return int；如果返回值>=1，表示更新的记录行数;如果返回值为0，则表示更新失败
        * @date : 2019-05-08 01:00
     */
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    /**
        * @description: 根据ID查询抢购的商品对象
        * @param seckillId 1
        * @author lwl
        * @return com.lrichiewl.seckill.entity.Seckill
        * @date : 2019-05-08 13:34
     */
    Seckill queryById(long seckillId);

    /**
        * @description: 查询抢购的商品的列表，根据偏移量OFFET查询LIMIT条记录
        * @param offet 1
    	* @param limit 2
        * @author lwl
        * @return java.util.List<com.lrichiewl.seckill.entity.Seckill>
        * @date : 2019-05-08 13:36
     * 由于JAVA运行时，行参不会有记录，即offset limit两个参数在实际运行时叫"arg0"和"arg1"，所以下面要使用Param注释
     */
    List<Seckill> queryAll(@Param("offset") int offet, @Param("limit") int limit);
}
