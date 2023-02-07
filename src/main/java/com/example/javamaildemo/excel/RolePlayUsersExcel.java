package com.example.javamaildemo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Data
public class RolePlayUsersExcel {
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat("yyyy-MM-dd")
    @ColumnWidth(20)
    @ExcelProperty("开始时间")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat("yyyy-MM-dd")
    @ColumnWidth(20)
    @ExcelProperty("结束时间")
    private LocalDateTime endTime;

    public static void main(String[] args) {
        List<Long> list = new ArrayList<>();list.add(111111111L);list.add(222222222L);list.add(333333333L);list.add(444444444L);list.add(555555555L);
        list.sort(Long::compareTo);
        System.out.println(list);
    }



}
