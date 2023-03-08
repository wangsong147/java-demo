package com.example.javamaildemo.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class R<T> {
    private T data;
    private Integer status = 200;
    private String message = "success";

    public static <T> R<T> ok(T data) {
        R<T> r = new R<>();
        r.data = data;
        return r;
    }

    public static <T> R<T> error(Integer status, String message) {
        R<T> r = new R<>();
        r.setStatus(status);
        r.setMessage(message);
        return r;
    }

}
