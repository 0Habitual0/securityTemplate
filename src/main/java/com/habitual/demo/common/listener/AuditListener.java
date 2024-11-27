package com.habitual.demo.common.listener;

import com.habitual.demo.common.entity.BaseEntity;
import com.habitual.demo.common.security.context.UserContext;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.util.Date;

/**
 * 监听器 用于自动补充时间与用户信息
 */
public class AuditListener {

    @PrePersist
    public void setCreatedAtAndCreatedBy(BaseEntity entity) {
        entity.setCreateTime(new Date());
        entity.setCreateBy(getCurrentUser());
    }

    @PreUpdate
    public void setUpdatedAtAndUpdatedBy(BaseEntity entity) {
        entity.setUpdateTime(new Date());
        entity.setUpdateBy(getCurrentUser());
    }

    /**
     * 获取当前线程用户名
     */
    private String getCurrentUser() {
        return UserContext.getNickname();
    }

}
