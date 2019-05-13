package com.lrichiewl.seckill.service.impl;

import com.lrichiewl.seckill.dao.SeckillDao;
import com.lrichiewl.seckill.dao.SuccessKilledDao;
import com.lrichiewl.seckill.dto.Exposer;
import com.lrichiewl.seckill.dto.SeckillExecution;
import com.lrichiewl.seckill.entity.Seckill;
import com.lrichiewl.seckill.entity.SuccessSeckilled;
import com.lrichiewl.seckill.enums.SeckillStatEnum;
import com.lrichiewl.seckill.exception.RepeatKillException;
import com.lrichiewl.seckill.exception.SeckillCloseException;
import com.lrichiewl.seckill.exception.SeckillException;
import com.lrichiewl.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @author Ryan Lu
 * @date 2019-05-13 08:10
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    //注入service依赖
    @Autowired
    private SeckillDao seckillDao;
    @Autowired
    private SuccessKilledDao successKilledDao;

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    public final String salt = "slaenglRsefihAsneBlne^3525312&!#%";

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
     * @param seckillId
     * @return java.lang.String
     * @description: 转化我们的抢购商品ID为独一无二的md5，使用混淆字符串slat后在用专门的构造md5的方法后获取结果
     * @author lwl
     * @date : 2019-05-13 09:04
     */
    public String getMd5(long seckillId) {
        String base = seckillId + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    /**
     * @param seckillId
     * @param userPhoneNum
     * @param md5
     * @return com.lrichiewl.seckill.dto.SeckillExecution
     * @description: 执行抢购操作方法。
     * 如果用户提供的md5与我们的md5不一致，则说明有数据篡改，不执行抢购操作。
     * 执行抢购的业务逻辑：减库存，记录购买行为
     * @author lwl
     * @date : 2019-05-13 09:07
     */
    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userPhoneNum, String md5)
            throws SeckillException, SeckillCloseException, RepeatKillException {
        if (md5 == null || !md5.equals(getMd5(seckillId))) {
            throw new SeckillException("seckill data rewrite");
        }
        Date nowTime = new Date();

        int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
        try {
            if (updateCount <= 0) {
                //0表示没有更新到记录，抢购结束了
                throw new SeckillCloseException("seckill is closed");
            } else {
                int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhoneNum);
                if (insertCount <= 0) {
                    //0表示这项纪录已经存在，为重复抢购
                    throw new RepeatKillException("seckill repeated");
                } else {
                    //抢购成功了也就能查到这一条记录
                    SuccessSeckilled successSeckilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhoneNum);
                    return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, successSeckilled);
                }
            }
        } catch (SeckillCloseException e1) {
            throw e1;
        } catch (RepeatKillException e2) {
            throw e2;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            //将编译器异常转化为运行期异常
            throw new SeckillException("seckill inner error:" + e.getMessage());
        }

    }
}
