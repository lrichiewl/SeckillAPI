package com.lrichiewl.seckill.enums;

/**
 * 使用枚举表述常量数据字段
 *
 * @author Ryan Lu
 * @date 2019-05-13 11:02
 */
public enum SeckillStatEnum {

    SUCCESS(1, "抢购成功"),
    END(0, "抢购结束"),
    REPEAT_KILL(-1, "重复抢购"),
    INNER_ERROR(-2, "系统异常"),
    DATA_REWRITE(-3, "数据篡改");

    private int state;
    private String stateInfo;

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    SeckillStatEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public static SeckillStatEnum stateOf(int index) {
        for (SeckillStatEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }
}
