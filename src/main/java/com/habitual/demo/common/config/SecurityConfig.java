package com.habitual.demo.common.config;

import com.habitual.demo.common.security.Properties.SecurityProperties;
import com.habitual.demo.common.security.session.CustomSessionInformationExpiredStrategy;
import com.habitual.demo.common.security.filter.TokenAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;

/**
 * SpringSecurity配置类
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * 注入 SecurityProperties 配置类，用于获取安全相关的配置
     */
    private final SecurityProperties securityProperties;

    public SecurityConfig(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable) // 禁用 CORS防护（跨域资源共享）
                .csrf(AbstractHttpConfigurer::disable) // 禁用 CSRF防护（跨站请求伪造）
                .authorizeHttpRequests(authorize -> authorize // HTTP 请求授权
                        .requestMatchers(securityProperties.getPermitUrls().toArray(new String[0])).permitAll() // 允许路径的请求不进行身份验证, 允许访问/uploads/路径下的所有资源(在WebConfig中配置了映射)
                        .anyRequest().authenticated() // 其他所有请求都需要身份验证
                )
                .sessionManagement(sessionManagement -> sessionManagement
                        .maximumSessions(securityProperties.getMaximumSessions()) // 设置同一用户最多允许的会话数
                        .maxSessionsPreventsLogin(securityProperties.isMaxSessionsPreventsLogin()) // false表示后登录的用户会顶掉先登录的用户 和sessionInformationExpiredStrategy搭配使用
                        .sessionRegistry(sessionRegistry())
                        .expiredSessionStrategy(sessionInformationExpiredStrategy()) // 重写过期策略
                )
                .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // 添加Token身份验证过滤器
        return http.build();
    }

    /**
     * 注册自定义过期策略
     */
    @Bean
    public CustomSessionInformationExpiredStrategy sessionInformationExpiredStrategy() {
        return new CustomSessionInformationExpiredStrategy();
    }

    /**
     * 注册Token身份验证过滤器
     */
    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }

    /**
     * 注册SessionRegistry，该 Bean 用于管理 Session 会话并发控制
     */
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    /**
     * 配置 Session 的监听器（注意：如果使用并发 Session 控制，一般都需要配置该监听器）
     * 解决 Session 失效后, SessionRegistry 中 SessionInformation 没有同步失效的问题
     */
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

}
