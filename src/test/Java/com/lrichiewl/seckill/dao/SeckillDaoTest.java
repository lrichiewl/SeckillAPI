package com.lrichiewl.seckill.dao;

import com.lrichiewl.seckill.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Ryan Lu
 * @date 2019-05-09 13:19
 */

//配置spring与junit的整合，junit启动时加载springIOC容器
@RunWith(SpringJUnit4ClassRunner.class)

//告诉junit Spring的配置文件，在加载spring容器时自动应用spring-dao.xml，能够用验证MyBatis与Spring整合以及数据库连接池的配置是否正确
@ContextConfiguration({"classpath:spring/spring-dao.xml"})

public class SeckillDaoTest {

    //使用注解来注入Dao实现类的依赖
    @Resource
    private SeckillDao seckillDao;

    @Test

    public void testQueryById() throws Exception {
        long id = 1000;
        Seckill sk = seckillDao.queryById(id);
        System.out.println(sk.getName());
        System.out.println(sk);
    }

    @Test
    public void testQueryAll() throws Exception {
        List<Seckill> seckills = seckillDao.queryAll(0, 100);
        for (Seckill sk : seckills) {
            System.out.println(sk.getName());
            System.out.println(sk);
        }
    }

    @Test
    public void testReduceNumber() throws Exception {
        Date killTime = new Date();
        int updateCount = seckillDao.reduceNumber(1000L, killTime);
        System.out.println("updateCount = " + updateCount);
    }

}