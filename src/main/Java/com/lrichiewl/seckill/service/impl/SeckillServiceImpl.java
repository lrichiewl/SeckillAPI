package com.lrichiewl.seckill.service.impl;

import com.lrichiewl.seckill.dao.SeckillDao;
import com.lrichiewl.seckill.dao.SuccessKilledDao;
import com.lrichiewl.seckill.dto.Exposer;
import com.lrichiewl.seckill.dto.SeckillExecution;
import com.lrichiewl.seckill.entity.Seckill;
import com.lrichiewl.seckill.exception.RepeatKillException;
import com.lrichiewl.seckill.exception.SeckillCloseException;
import com.lrichiewl.seckill.exception.SeckillException;
import com.lrichiewl.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @author Ryan Lu
 * @date 2019-05-13 08:10
 */
public class SeckillServiceImpl implements SeckillService {

    private SeckillDao seckillDao;
    private SuccessKilledDao successKilledDao;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    public final String slat = "slaenglRsefihAsneBlngileae^3525312&!#%";

    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 4);
    }

    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = seckillDao.queryById(seckillId);
        if (seckill == null) {
            return new Exposer(false, seckillId);
        }

        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        //通过new一个Date类型对象就直接是系统当前时间
        Date nowTime = new Date();

        if (nowTime.getTime() < startTime.getTime()
                || nowTime.getTime() > endTime.getTime()) {
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());

        }
        String md5 = getMd5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    /**
        * @description: 转化我们的抢购商品ID为独一无二的md5，使用混淆字符串slat后在用专门的构造md5的方法后获取结果
        * @param seckillId
        * @author lwl
        * @return java.lang.String
        * @date : 2019-05-13 09:04
     */
    public String getMd5(long seckillId){
        String base = seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    /**
        * @description: 执行抢购操作方法。
            * 如果用户提供的md5与我们的md5不一致，则说明有数据篡改，不执行抢购操作。
            * 执行抢购的业务逻辑：减库存，记录购买行为
        * @param seckillId
    	* @param userPhoneNum
    	* @param md5
        * @author lwl
        * @return com.lrichiewl.seckill.dto.SeckillExecution
        * @date : 2019-05-13 09:07
     */
    public SeckillExecution executeSeckill(long seckillId, long userPhoneNum, String md5)
            throws SeckillException, SeckillCloseException, RepeatKillException {
        if (md5 == null || ! md5.equals(getMd5(seckillId))) {
            throw new SeckillException("seckill data rewrite");
        }
        Date nowTime = new Date();
        
        int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
        if (updateCount <= 0){
            //没有更新到记录，
        }

        return null;
    }
}
