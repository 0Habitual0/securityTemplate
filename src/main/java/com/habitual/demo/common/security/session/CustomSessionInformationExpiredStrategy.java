package com.habitual.demo.common.security.session;

import com.habitual.demo.common.exception.TokenValidationException;
import com.habitual.demo.common.service.ExceptionHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import java.io.IOException;

/**
 * 自定义过期策略
 * 前提：Session 并发处理的配置为 maxSessionsPreventsLogin(false)
 * 用户的并发 Session 会话数量达到上限，新会话登录后，最老会话会在下一次请求中失效，并执行此策略
 */
public class CustomSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {

    @Autowired
    private ExceptionHandlerService exceptionHandlerService;

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException {
        TokenValidationException ex = new TokenValidationException("检测到在另一设备上的登录，您已下线。如非本人操作，请尽快更改密码", 50012);
        exceptionHandlerService.handleTokenValidationException(event.getResponse(), ex);
    }

}
