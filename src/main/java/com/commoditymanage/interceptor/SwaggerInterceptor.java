package com.commoditymanage.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Swagger拦截器
 * 根据application.properties文件中的swagger.disabled属性控制可否使用Swagger
 * @author ~
 * @date 2022/04/09 17:56
 */
@Slf4j
@Component
@PropertySource("classpath:application.properties")
public class SwaggerInterceptor implements HandlerInterceptor {

    @Value("${swagger.disabled}")
    private Boolean disabledSwagger;

    @Value("${swagger.redirect-uri}")
    private String redirectUri;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (disabledSwagger) {
            String uri = request.getContextPath();
            if (StringUtils.isNotBlank(redirectUri)){
                uri = request.getContextPath() + redirectUri;
            }
            if (StringUtils.isBlank(uri)) {
                uri = "/";
            }
            try {
                response.sendRedirect(uri);
            } catch (IOException e) {
                log.error(String.format("Redirect to '%s' for swagger throw an exception : %s", uri, e.getMessage()), e);
            }
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
