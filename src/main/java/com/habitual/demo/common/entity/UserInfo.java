package com.habitual.demo.common.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * UserContext类使用 用户信息类
 */
@Getter
@Setter
public class UserInfo {

    /**
     * 唯一主键
     */
    private Long id;

    /**
     * 登录账号
     */
    private String username;

    /**
     * 用户名
     */
    private String nickname;

    /**
     * 角色
     */
    private String role;

}
