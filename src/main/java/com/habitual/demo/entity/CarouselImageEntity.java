package com.habitual.demo.entity;

import com.habitual.demo.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.io.Serial;

/**
 * 实体类 轮播图
 */
@Getter
@Setter
@Entity
@Table(name = "carousel_image")
@Comment("旅游线路")
public class CarouselImageEntity extends BaseEntity {

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

}
