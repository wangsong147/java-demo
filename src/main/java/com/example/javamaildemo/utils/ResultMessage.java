package com.example.javamaildemo.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultMessage<T> {
    private T data;
    private Integer status = 200;
    private String message = "success";

    public static ResultMessage<Void> ok() {
       return new ResultMessage<>();
    }


    public static <T> ResultMessage<T> ok(T data) {
        ResultMessage<T> resultMessage = new ResultMessage<>();
        resultMessage.data = data;
        return resultMessage;
    }

    public static <T> ResultMessage<T> error(Integer status, String message) {
        ResultMessage<T> resultMessage = new ResultMessage<>();
        resultMessage.setStatus(status);
        resultMessage.setMessage(message);
        return resultMessage;
    }

}
