package com.habitual.demo.entity;

import com.habitual.demo.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.io.Serial;

/**
 * 实体类 旅游攻略
 */
@Getter
@Setter
@Entity
@Table(name = "tourist_guide")
@Comment("旅游攻略")
public class TouristGuideEntity extends BaseEntity {

    /**
     * 序列化版本号
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 作者用户id
     */
    @Comment("作者用户id")
    private Long userId;

    /**
     * 攻略名称
     */
    @Comment("攻略标题")
    private String title;

    /**
     * 攻略内容
     */
    @Comment("攻略内容")
    private String context;

    /**
     * 攻略图片
     */
    @Comment("攻略图片")
    private String image;

    /**
     * 攻略类型
     */
    @Comment("攻略类型")
    private String type;
}
