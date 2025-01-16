package com.yt.YcTravelManageSystem.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yt.YcTravelManageSystem.common.listener.AuditListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 基类 数据库实体类继承此类
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditListener.class)
public class BaseEntity implements Serializable {

    /**
     * 序列化版本号
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 唯一主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("唯一主键")
    private Long id;

    /**
     * 创建人
     */
    @Comment("创建人")
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    @Comment("创建时间")
    private Date createTime;

    /**
     * 更新人
     */
    @Comment("更新人")
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
    @Comment("更新时间")
    private Date updateTime;

    /**
     * 备注
     */
    @Comment("备注")
    private String remark;

}
