package com.yt.YcTravelManageSystem.common.listener;

import com.yt.YcTravelManageSystem.common.entity.BaseEntity;
import com.yt.YcTravelManageSystem.common.security.context.UserContext;
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
