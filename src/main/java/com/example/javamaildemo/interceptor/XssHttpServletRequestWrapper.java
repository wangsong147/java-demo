package com.example.javamaildemo.interceptor;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (StrUtil.isEmpty(value)) {
            return value;
        }
        return HtmlUtils.htmlEscape(value);
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        if (StrUtil.isEmpty(value)) {
            return value;
        }
        return HtmlUtils.htmlEscape(value);
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] values = super.getParameterValues(name);
        if(ObjectUtil.isEmpty(values)){
            return values;
        }
        for (int i = 0; i < values.length; i++) {
            values[i] = HtmlUtils.htmlEscape(values[i]);
        }
        return values;
    }
}
