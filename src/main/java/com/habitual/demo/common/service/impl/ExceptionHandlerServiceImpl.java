package com.habitual.demo.common.service.impl;

import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.habitual.demo.common.entity.CommonResponse;
import com.habitual.demo.common.exception.TokenValidationException;
import com.habitual.demo.common.service.ExceptionHandlerService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * 业务层实现 异常处理
 */
@Service
public class ExceptionHandlerServiceImpl implements ExceptionHandlerService {

    // 处理Token验证异常
    public void handleTokenValidationException(HttpServletResponse response, TokenValidationException ex) throws IOException {
        handleException(response, HttpStatus.UNAUTHORIZED.value(), ex.getErrorCode(), ex.getMessage());
    }

    // 自定义处理异常逻辑
    public void handleException(HttpServletResponse response, int statusCode, int errorCode, String message) throws IOException {
        // 设置响应状态码
        response.setStatus(statusCode);
        // 设置响应内容类型
        response.setContentType("application/json;charset=utf-8");
        try (PrintWriter writer = response.getWriter()) {
            // 确保对象映射器（ObjectMapper）使用UTF-8编码
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(JsonWriteFeature.ESCAPE_NON_ASCII.mappedFeature(), false);
            // 将自定义响应对象转换为 JSON 并写入响应
            writer.write(objectMapper.writeValueAsString(CommonResponse.fail(errorCode, message)));
            writer.flush();
        }
    }

}
