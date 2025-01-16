package com.yt.YcTravelManageSystem.common.security.filter;

import com.yt.YcTravelManageSystem.common.entity.UserInfo;
import com.yt.YcTravelManageSystem.common.exception.TokenValidationException;
import com.yt.YcTravelManageSystem.common.security.context.UserContext;
import com.yt.YcTravelManageSystem.common.service.ExceptionHandlerService;
import com.yt.YcTravelManageSystem.common.utils.JwtTokenUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

/**
 * Token身份验证过滤器
 */
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ExceptionHandlerService exceptionHandlerService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("X-Token");
        if (token != null) {
            try {
                UserInfo userInfo = jwtTokenUtil.validateToken(token);
                UserContext.setUser(userInfo);
                Authentication authentication = getAuthentication(userInfo.getUsername());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (TokenValidationException ex) {
                // 过滤器无法被监听，主动调用异常处理
                exceptionHandlerService.handleTokenValidationException(response, ex);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    // 创建简单的认证对象，包含用户名和角色信息
    private Authentication getAuthentication(String username) {
        return new UsernamePasswordAuthenticationToken(
                username,
                null, // 这里可以放置密码，如果需要的话
                Collections.emptyList() // 不使用权限
        );
    }

}
