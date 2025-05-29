package com.habitual.demo.user.entity;

import com.habitual.demo.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import java.io.Serial;

/**
 * 实体类 角色
 */
@Getter
@Setter
@Entity
@Table(name = "role", uniqueConstraints = @UniqueConstraint(columnNames = "role_code"))
@Comment("用户表")
public class RoleEntity extends BaseEntity {

    /**
     * 序列化版本号
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 角色编码
     */
    @Column(unique = true, nullable = false)
    @Comment("角色编码")
    private String roleCode;

    /**
     * 角色名称
     */
    @Comment("角色名称")
    private String roleName;

    /**
     * 状态 0禁用 1启用
     */
    @Comment("状态 0禁用 1启用")
    private Long status;

}
