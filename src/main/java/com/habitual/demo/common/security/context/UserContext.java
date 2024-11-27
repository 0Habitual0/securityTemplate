package com.habitual.demo.common.security.context;

import com.habitual.demo.common.entity.UserInfo;
import com.habitual.demo.common.exception.TokenValidationException;

/**
 * 线程局部变量类 获取用户信息
 */
public class UserContext {

    private static final ThreadLocal<UserInfo> userHolder = new ThreadLocal<>();

    public static void setUser(UserInfo user) {
        userHolder.set(user);
    }

    private static UserInfo getUser() {
        if (userHolder.get() == null) {
            throw new TokenValidationException("用户信息不存在。请重新登录", 50008);
        }
        return userHolder.get();
    }

    public static Long getId() {
        return getUser().getId();
    }

    public static String getUsername() {
        return getUser().getUsername();
    }

    public static String getNickname() {
        return getUser().getNickname();
    }

    public static void clear() {
        userHolder.remove();
    }

}
