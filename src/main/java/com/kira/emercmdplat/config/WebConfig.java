package com.kira.emercmdplat.config;

import com.kira.emercmdplat.interceptor.LoginHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.MappedInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: kira
 * @Date: 2020/6/1 10:34
 * @Description:
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginHandlerInterceptor interceptor;

    /**
     * Add Spring MVC lifecycle interceptors for pre- and post-processing of
     * controller method invocations. Interceptors can be registered to apply
     * to all requests or be limited to a subset of URL patterns.
     * <p><strong>Note</strong> that interceptors registered here only apply to
     * controllers and not to resource handler requests. To intercept requests for
     * static resources either declare a
     * {@link MappedInterceptor MappedInterceptor}
     * bean or switch to advanced configuration mode by extending
     * {@link WebMvcConfigurationSupport
     * WebMvcConfigurationSupport} and then override {@code resourceHandlerMapping}.
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> patterns = new ArrayList();
        patterns.add("/login");
        patterns.add("/app/login");
        registry.addInterceptor(interceptor).addPathPatterns("/**")
                .excludePathPatterns(patterns);
    }
}
