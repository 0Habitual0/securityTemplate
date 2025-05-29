package com.habitual.demo.common.security.Properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 使用 @ConfigurationProperties 让 Spring Boot 自动将配置值绑定到该类的属性
 */
@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "system.security")
public class SecurityProperties {

    // 允许访问的 URL 列表，从配置文件中读取
    private List<String> permitUrls;

    // 同一用户最多允许的会话数
    private int maximumSessions = 1;

    // false表示后登录的用户会顶掉先登录的用户 和sessionInformationExpiredStrategy搭配使用
    private boolean maxSessionsPreventsLogin = false;

}
