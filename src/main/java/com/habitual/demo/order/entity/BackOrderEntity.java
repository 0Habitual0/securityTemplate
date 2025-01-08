package com.habitual.demo.order.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.habitual.demo.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.io.Serial;
import java.util.Date;

/**
 * 实体类 退单
 */
@Getter
@Setter
@Entity
@Table(name = "back_order")
@Comment("退单")
public class BackOrderEntity extends BaseEntity {

    /**
     * 序列化版本号
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    @Comment("商品id")
    private Long businessId;

    /**
     * 下单用户id
     */
    @Comment("下单用户id")
    private Long userId;

    /**
     * 联系电话
     */
    @Comment("联系电话")
    private String tel;

    /**
     * 联系人姓名
     */
    @Comment("联系人姓名")
    private String realName;

    /**
     * 订单编号
     */
    @Comment("订单编号")
    private String orderCode;

    /**
     * 单价
     */
    @Comment("单价")
    private Long unitPrice;

    /**
     * 总价
     */
    @Comment("总价")
    private Long totalPrice;

    /**
     * 数量
     */
    @Comment("数量")
    private Long num;

    /**
     * 订单类型
     */
    @Comment("订单类型")
    private String type;

    /**
     * 订单状态
     */
    @Comment("订单状态")
    private String status;

    /**
     * 订单暂存状态
     */
    @Comment("订单暂存状态")
    private String stagingStatus;

    /**
     * 订单图片
     */
    @Comment("订单图片")
    private String image;

    /**
     * 支付方式
     */
    @Comment("支付方式")
    private String payType;

    /**
     * 预约时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    @Comment("预约时间")
    private Date reservationTime;

}
