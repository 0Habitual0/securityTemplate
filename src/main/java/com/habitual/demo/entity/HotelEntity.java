package com.habitual.demo.entity;

import com.habitual.demo.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.io.Serial;

/**
 * 实体类 酒店客房
 */
@Getter
@Setter
@Entity
@Table(name = "hotel")
@Comment("酒店客房")
public class HotelEntity extends BaseEntity {

    /**
     * 序列化版本号
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 酒店名称
     */
    @Comment("酒店名称")
    private String name;

    /**
     * 酒店类型
     */
    @Comment("酒店类型")
    private String type;

    /**
     * 酒店图片
     */
    @Comment("酒店图片")
    private String image;

    /**
     * 酒店地址
     */
    @Comment("酒店地址")
    private String location;

    /**
     * 联系电话
     */
    @Comment("联系电话")
    private String tel;

    /**
     * 酒店网址
     */
    @Comment("酒店网址")
    private String url;

    /**
     * 酒店介绍
     */
    @Comment("酒店介绍")
    private String introduce;

}
