package com.example.javamaildemo.utils.time_util;


import com.example.javamaildemo.common.Gender;
import lombok.extern.slf4j.Slf4j;

import java.time.Month;
import java.time.Year;

@Slf4j
public class CalendarUtils {

    public static Integer getQuarter(Month month) {
        int quarter;
        switch (month) {
            case JANUARY:
            case FEBRUARY:
            case MARCH:
                quarter = 1;
                break;
            case APRIL:
            case MAY:
            case JUNE:
                quarter = 2;
                break;
            case JULY:
            case AUGUST:
            case SEPTEMBER:
                quarter = 3;
                break;
            case OCTOBER:
            case NOVEMBER:
            case DECEMBER:
                quarter = 4;
                break;
            default:
                log.info("传入月份参数不合法");
                throw new RuntimeException("参数异常");
        }
        return quarter;
    }

    public static void main(String[] args) {
        System.out.println(Gender.female);
        System.out.println(Gender.male.name());

//        Month of = Month.of(1);
//        log.info("{}", of);
//
//        for (Month month : Month.values()) {
//            log.info("{}", month);
//        }

    }
}