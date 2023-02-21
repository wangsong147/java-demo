package com.example.javamaildemo.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.example.javamaildemo.excel.RolePlayUsersExcel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@RequestMapping("excel")
@RestController
public class EasyExcelController {
    @GetMapping("downLoad")
    public void downLoad(HttpServletResponse response) throws IOException {
        List<RolePlayUsersExcel> excels = new ArrayList<>();
        excels.add(new RolePlayUsersExcel());
        excels.add(new RolePlayUsersExcel());

        excels.forEach(r -> {
                    r.setStartTime(LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0));
                    r.setEndTime(LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0));
                    log.info("{}", r.getStartTime());
                    log.info("{}", r.getEndTime());
                }
        );

        // 设置响应头
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/form-data");
        response.setHeader("Content-disposition", "attachment;filename=role_play_test.xlsx");

        // 将excel文件写入response流中
        EasyExcel.write(response.getOutputStream(), RolePlayUsersExcel.class)
                .excelType(ExcelTypeEnum.XLSX)
                .sheet("人员名单")
                .doWrite(excels);
    }

    public static void main(String[] args) {
        List<RolePlayUsersExcel> excels = new ArrayList<>();
        excels.add(new RolePlayUsersExcel());
        excels.add(new RolePlayUsersExcel());
        excels.forEach(r -> {
                    r.setStartTime(LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0));
                    r.setEndTime(LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0));
                    log.info("{}", r.getStartTime());
                    log.info("{}", r.getEndTime());
                }
        );

        EasyExcel.write("default_path.xlsx", RolePlayUsersExcel.class)
                .excelType(ExcelTypeEnum.XLSX)
                .sheet("人员名单")
                .doWrite(excels);
    }
}
