package com.habitual.demo.user.entity;

import com.habitual.demo.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * 实体类 用户
 */
@Getter
@Setter
@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class UserEntity extends BaseEntity {

    /**
     * 序列化版本号
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 登录账号
     */
    @Column(unique = true, nullable = false)
    private String username;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 用户名
     */
    private String nickname;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private Long age;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话号
     */
    private String tel;

    /**
     * 角色
     */
    private String role;

    /**
     * 状态 0启用 1禁用
     */
    private Long status;

}