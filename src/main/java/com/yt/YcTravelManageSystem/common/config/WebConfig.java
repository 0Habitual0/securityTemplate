package com.yt.YcTravelManageSystem.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web配置类
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 配置/uploads/路径映射到本地文件系统
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:static/uploads/");
    }

}
