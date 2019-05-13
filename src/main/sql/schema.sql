/**
 * @author RyanLu
 * 本文件为脚本文件，在终端运行，不在IDEA上运行。
 */

CREATE DATABASE seckill;
use seckill;

CREATE TABLE seckill(
    `seckill_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '商品库存ID',
    `name` varchar(120) NOT NULL COMMENT '商品名称',
    `number` int NOT NULL COMMENT '库存数量',
    `start_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '抢购开始时间',
    `end_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '抢购结束时间',
    `create_time` timestamp  NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
-- 最后一个KEY哪里不能有逗号！！！在这里错了很久
    PRIMARY KEY (seckill_id),
    KEY idx_start_time(start_time),
    KEY idx_end_time(end_time),
    KEY idx_create_time(create_time)
)ENGINE =InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='库存列表';

-- 对数据进行初始化
INSERT INTO
    seckill(name,number,start_time,end_time)
VALUES
    ('X宝双十一大促－1000元抢购商品A',500,'2019-05-31 00:00:00','2019-07-01 00:00:00'),
    ('X宝双十一大促－500元抢购商品B',250,'2019-05-31 00:00:00','2019-07-01 00:00:00'),
    ('X宝双十一大促－300元抢购商品C',100,'2019-05-31 00:00:00','2019-07-01 00:00:00'),
    ('X宝双十一大促－100元抢购商品D',150,'2019-05-31 00:00:00','2019-07-01 00:00:00')

-- 记录抢购成功的明细表。由于本项目不含用户管理模块，所以用户登录认证相关的信息也放在这里
CREATE TABLE success_seckill(
    `seckill_id` BIGINT NOT NULL COMMENT '下单商品ID',
    `user_phone_num` BIGINT NOT NULL COMMENT '用户手机号码',
    `state` TINYINT NOT NULL DEFAULT -1 COMMENT '状态标识：－1无效，0下单成功，1已付款',
    `create_time` TIMESTAMP NOT NULL COMMENT '下单创建时间',
    -- 主键使用联合主键，用两个独一无二数据来确定下单的唯一性，同时防止同一用户对于同一产品重复下单
    PRIMARY KEY(seckill_id, user_phone_num),
    key idx_create_time(create_time)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='成功下单的明细表';

-- 连接数据库控制台
mySQL -uroot -p