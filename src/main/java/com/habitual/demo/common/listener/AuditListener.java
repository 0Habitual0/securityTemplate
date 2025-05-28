package com.habitual.demo.common.listener;

import com.habitual.demo.common.entity.BaseEntity;
import com.habitual.demo.common.security.context.UserContext;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;

/**
 * 监听器 用于自动补充时间与用户信息
 */
public class AuditListener {

    @PrePersist
    public void setCreatedAtAndCreatedBy(BaseEntity entity) {
        entity.setCreateTime(new Date());
        if (shouldAudit()) {
            entity.setCreateBy(getCurrentUser());
        }
    }

    @PreUpdate
    public void setUpdatedAtAndUpdatedBy(BaseEntity entity) {
        entity.setUpdateTime(new Date());
        if (shouldAudit()) {
            entity.setUpdateBy(getCurrentUser());
        }
    }

    private boolean shouldAudit() {
        // 检查是否需要身份验证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated() && !(authentication instanceof AnonymousAuthenticationToken);
    }

    private String getCurrentUser() {
        return UserContext.getNickname();
    }
}
