package com.habitual.demo.entity;

import com.habitual.demo.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.io.Serial;

/**
 * 实体类 景点门票
 */
@Getter
@Setter
@Entity
@Table(name = "scenic_spot")
@Comment("景点门票")
public class ScenicSpotEntity extends BaseEntity {

    /**
     * 序列化版本号
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 景点名称
     */
    @Comment("景点名称")
    private String name;

    /**
     * 景点类型
     */
    @Comment("景点类型")
    private String type;

    /**
     * 景点图片
     */
    @Comment("景点图片")
    private String image;

    /**
     * 景点等级
     */
    @Comment("景点等级")
    private String level;

    /**
     * 景点地址
     */
    @Comment("景点地址")
    private String location;

    /**
     * 门票价格
     */
    @Comment("门票价格")
    private Long price;

    /**
     * 门票库存
     */
    @Comment("门票库存")
    private Long stock;

    /**
     * 开放时间
     */
    @Comment("开放时间")
    private String openTime;

    /**
     * 注意事项
     */
    @Comment("注意事项")
    private String precautions;

    /**
     * 交通指南
     */
    @Comment("交通指南")
    private String trafficGuide;

    /**
     * 景点介绍
     */
    @Comment("景点介绍")
    private String introduce;

}
