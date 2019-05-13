package com.lrichiewl.seckill.dao;

import com.lrichiewl.seckill.entity.SuccessSeckilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author Ryan Lu
 * @date 2019-05-09 14:28
 */

//配置spring与junit的整合，junit启动时加载springIOC容器
@RunWith(SpringJUnit4ClassRunner.class)

//告诉junit Spring的配置文件，在加载spring容器时自动应用spring-dao.xml，能够用验证MyBatis与Spring整合以及数据库连接池的配置是否正确
@ContextConfiguration({"classpath:spring/spring-dao.xml"})

public class SuccessKilledDaoTest {

    @Resource
    private SuccessKilledDao successKilledDao;

    @Test
    public void testInsertSuccessKilled() {
        long successID = 1001L;
        long userPhoneNum = 10086100861L;
        int insertCount = successKilledDao.insertSuccessKilled(successID, userPhoneNum);
        System.out.println("insertCount = " + insertCount);
    }

    @Test
    public void testQueryByIdWithSeckill() {
        long successID = 1001L;
        long userPhoneNum = 10086100861L;
        SuccessSeckilled successSeckilled = successKilledDao.queryByIdWithSeckill(successID, userPhoneNum);
        System.out.println(successSeckilled);
        System.out.println(successSeckilled.getSeckill());

    }
}