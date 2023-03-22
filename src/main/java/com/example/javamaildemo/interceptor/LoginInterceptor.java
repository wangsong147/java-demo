package com.example.javamaildemo.interceptor;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.alibaba.fastjson.JSON;
import com.example.javamaildemo.common.Constant;
import com.example.javamaildemo.utils.JwtUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        String token = request.getHeader("Authorization");
//
//        if (StringUtils.isNotBlank(token)) {
//            try {
//                //解析token
//                JWT jwt = JWTUtil.parseToken(token);
//                boolean validate = jwt.setKey(Constant.JWT_KEY.getBytes()).validate(0);
//                //验证合法性+时间
//                if (!validate) {
//                    response.setStatus(401);
//                    return false;
//                }
//                return true;
//            } catch (Exception e) {
//                throw new RuntimeException(e.getMessage());
//            }
//        }
//        //没有token，直接返回401
//        response.setStatus(401);
//        return false;
        return  true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
    }
}
