package com.habitual.demo.collect.entity;

import com.habitual.demo.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.io.Serial;

/**
 * 实体类 收藏
 */
@Getter
@Setter
@Entity
@Table(name = "collect")
@Comment("收藏")
public class CollectEntity extends BaseEntity {

    /**
     * 序列化版本号
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 被收藏资源id
     */
    @Comment("被收藏资源id")
    private String businessId;

    /**
     * 收藏用户id
     */
    @Comment("收藏用户id")
    private Long userId;

    /**
     * 收藏类别
     */
    @Comment("收藏类别")
    private String type;

}
