package com.yt.YcTravelManageSystem.touristRoutes.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yt.YcTravelManageSystem.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.io.Serial;
import java.util.Date;

/**
 * 实体类 旅游线路
 */
@Getter
@Setter
@Entity
@Table(name = "tourist_routes")
@Comment("旅游线路")
public class TouristRoutesEntity extends BaseEntity {

    /**
     * 序列化版本号
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 旅游线路名称
     */
    @Comment("旅游线路名称")
    private String name;

    /**
     * 线路途径景点
     */
    @Comment("线路途径景点")
    private String scenicSpotName;

    /**
     * 旅游线路图片
     */
    @Comment("旅游线路图片")
    private String image;

    /**
     * 旅游线路出发地
     */
    @Comment("旅游线路出发地")
    private String beginSpot;
//
//    /**
//     * 旅游线路途径
//     */
//    @Comment("旅游线路途径")
//    private String waySpot;

    /**
     * 旅游线路目的地
     */
    @Comment("旅游线路目的地")
    private String endSpot;

    /**
     * 旅游线路交通方式
     */
    @Comment("旅游线路交通方式")
    private String traffic;

    /**
     * 旅游线路介绍
     */
    @Comment("旅游线路介绍")
    private String introduce;

    /**
     * 旅游线路出发时间
     */
    @Comment("旅游线路出发时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    private Date startTime;

    /**
     * 价格
     */
    @Comment("价格")
    private Long price;

    /**
     * 库存
     */
    @Comment("库存")
    private Long stock;

    /**
     * 旅游线路类型
     */
    @Comment("旅游线路类型")
    private String type;

    /**
     * 收藏数量
     */
    @Transient
    @Comment("收藏数量")
    private int collectCount;

}
