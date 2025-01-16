package com.yt.YcTravelManageSystem.common.exception;

import com.yt.YcTravelManageSystem.common.service.ExceptionHandlerService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;

/**
 * 全局异常处理器 对过滤器中发生的异常无效
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private ExceptionHandlerService exceptionHandlerService;

    /**
     * 处理Token验证异常
     */
    @ExceptionHandler(TokenValidationException.class)
    public void handleTokenValidationException(HttpServletResponse response, TokenValidationException ex, WebRequest request) throws IOException {
        exceptionHandlerService.handleTokenValidationException(response, ex);
    }

}
