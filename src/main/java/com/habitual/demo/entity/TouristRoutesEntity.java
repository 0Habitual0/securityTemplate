package com.habitual.demo.entity;

import com.habitual.demo.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.io.Serial;

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
     * 旅游线路类型
     */
    @Comment("旅游线路类型")
    private String type;

    /**
     * 旅游线路图片
     */
    @Comment("旅游线路图片")
    private String image;

    /**
     * 旅游线路行程安排
     */
    @Comment("旅游线路程安排")
    private String trip;

    /**
     * 旅游线路介绍
     */
    @Comment("旅游线路介绍")
    private String introduce;

}
