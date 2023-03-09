package com.example.javamaildemo.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 拦截器
 *
 */
@Slf4j
public class LogHandlerInterceptor2 implements HandlerInterceptor {

    /**
     * 请求方法执行之前
     * 返回true则通过
     *
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        StringBuffer requestURL = request.getRequestURL();
//        log.info("【拦截器2】-> preHandle请求URL：" + requestURL.toString());
        return true;
    }

    /**
     * 调用前提：preHandle返回true
     * 调用时间：Controller方法处理完之后，DispatcherServlet进行视图的渲染之前，也就是说在这个方法中你可以对ModelAndView进行操作
     * 执行顺序：链式Intercepter情况下，Intercepter按照声明的顺序倒着执行。
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
//        log.info("【拦截器2】-> postHandle返回modelAndView之前");
    }

    /**
     * 调用前提：preHandle返回true
     * 调用时间：DispatcherServlet进行视图的渲染之后
     *     ---> controller执行完之后会返回给DispatcherServlet一个redirect静态页面,但现在是前后分离,所以没有这一步了
     * 多用于清理资源
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//        log.info("【拦截器2】-> afterCompletion执行完请求方法完全返回之后");
    }
}
