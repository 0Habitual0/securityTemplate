package com.habitual.demo.common.service;

import com.habitual.demo.common.exception.TokenValidationException;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 业务层 异常处理
 */
public interface ExceptionHandlerService {

    // 处理Token验证异常
    void handleTokenValidationException(HttpServletResponse response, TokenValidationException ex) throws IOException;

    // 自定义处理异常逻辑
    void handleException(HttpServletResponse response, int statusCode, int errorCode, String message) throws IOException;

}
