package com.lrichiewl.seckill.dto;

import com.lrichiewl.seckill.entity.SuccessSeckilled;
import com.lrichiewl.seckill.enums.SeckillStatEnum;

/**
 * 本DTO封装抢购执行后的结果
 * state时执行抢购后的结果状态；
 * stateInfo:状态表示；
 *
 * @author Ryan Lu
 * @date 2019-05-12 15:12
 */
public class SeckillExecution {

    private long seckillId;
    private int state;
    private String stateInfo;
    private SuccessSeckilled successSeckilled;

    public SeckillExecution(long seckillId, SeckillStatEnum statEnum) {
        this.seckillId = seckillId;
        this.state = statEnum.getState();
        this.stateInfo = statEnum.getStateInfo();
    }

    public SeckillExecution(long seckillId, SeckillStatEnum statEnum, SuccessSeckilled successSeckilled) {
        this.seckillId = seckillId;
        this.state = statEnum.getState();
        this.stateInfo = statEnum.getStateInfo();
        this.successSeckilled = successSeckilled;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessSeckilled getSuccessSeckilled() {
        return successSeckilled;
    }

    public void setSuccessSeckilled(SuccessSeckilled successSeckilled) {
        this.successSeckilled = successSeckilled;
    }

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successSeckilled=" + successSeckilled +
                '}';
    }
}
