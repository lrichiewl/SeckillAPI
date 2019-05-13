package com.lrichiewl.seckill.entity;

import java.util.Date;

/**
 * @author Ryan Lu
 * @date 2019-05-08 00:12
 */
public class SuccessSeckilled {
    private long seckillId;
    private long userPhoneNum;
    private short state;
    private Date createTime;

    //由于一款商品可能有多个抢购记录，所以添加seckill属性用于获取Seckill类对象
    private Seckill seckill;

    @Override
    public String toString() {
        return "SuccessSeckill{" +
                "seckillId=" + seckillId +
                ", userPhoneNum=" + userPhoneNum +
                ", state=" + state +
                ", createTime=" + createTime +
                '}';
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getUserPhoneNum() {
        return userPhoneNum;
    }

    public void setUserPhoneNum(long userPhoneNum) {
        this.userPhoneNum = userPhoneNum;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }
}
