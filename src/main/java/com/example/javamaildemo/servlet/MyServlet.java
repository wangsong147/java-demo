package com.example.javamaildemo.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintWriter;

@Slf4j
@WebServlet(urlPatterns = "/myservlet")
public class MyServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        log.info("Servlet 开始初始化");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Servlet 开始处理 GET 方法");
        PrintWriter writer = resp.getWriter();
        writer.println("Hello Servlet");
        writer.flush();
        writer.close();
    }

    @Override
    public void destroy() {
        log.info("Servlet 开始销毁");
        super.destroy();
    }
}
