package com.example.javamaildemo.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.example.javamaildemo.interceptor.LogHandlerInterceptor;
import com.example.javamaildemo.interceptor.LogHandlerInterceptor2;
import com.example.javamaildemo.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 1.使用FastJSON
 * 2.配置时间格式化
 * 3.解决中文乱码
 * 4.添加自定义拦截器
 *
 * @Author niujinpeng
 * @Date 2018/12/13 15:35
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 添加自定义拦截器
     * .addPathPatterns("/**")  拦截的请求路径
     * .excludePathPatterns("/user"); 排除的请求路径
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截器执行顺序根据声明的顺序执行
        // preHandle ---> 拦截器2，拦截器1
        //                拦截器2，拦截器1 <--- postHandle
        registry.addInterceptor(new LogHandlerInterceptor2())
                .addPathPatterns("/**");
        registry.addInterceptor(new LogHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/user");

        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/page")
                .excludePathPatterns("/login");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 拦截所有的请求
                .allowedOrigins("http://www.abc.com")  // 可跨域的域名，可以为 *
                .allowCredentials(true)
                .allowedMethods("*")   // 允许跨域的方法，可以单独配置
                .allowedHeaders("*");  // 允许跨域的请求头，可以单独配置
    }

}
