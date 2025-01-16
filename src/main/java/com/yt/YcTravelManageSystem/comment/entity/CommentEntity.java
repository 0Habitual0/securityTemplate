package com.yt.YcTravelManageSystem.comment.entity;

import com.yt.YcTravelManageSystem.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.io.Serial;
import java.util.List;

/**
 * 实体类 评论
 */
@Getter
@Setter
@Entity
@Table(name = "comment")
@Comment("评论")
public class CommentEntity extends BaseEntity {

    /**
     * 序列化版本号
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 被评论资源id
     */
    @Comment("被评论资源id")
    private String businessId;

    /**
     * 评论用户id
     */
    @Comment("评论用户id")
    private Long userId;

    /**
     * 父评论id
     */
    @Comment("父评论id")
    private String parentId;

    /**
     * 评论类别
     */
    @Comment("评论类别")
    private String type;

    /**
     * 子评论
     */
    @Transient
    @Comment("子评论")
    private List<CommentEntity> children;

}
