package com.habitual.demo.touristGuide.entity;

import com.habitual.demo.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.io.Serial;
import java.io.Serializable;

/**
 * 实体类 旅游攻略轮播图
 */
@Getter
@Setter
@Entity
@Table(name = "tourist_guide_carousel_image")
@Comment("旅游攻略轮播图")
public class TouristGuideCarouselImageEntity extends BaseEntity {

    /**
     * 序列化版本号
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 轮播图地址
     */
    @Comment("轮播图地址")
    private String url;

    /**
     * 旅游攻略id
     */
    @Comment("旅游攻略id")
    private String businessId;

}
