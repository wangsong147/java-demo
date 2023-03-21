package com.example.javamaildemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_users")
public class Users implements Serializable {
    @TableId(type = IdType.AUTO, value = "id")
    private Long id;
    private String username;
    private String password;

//    private String nickName;
//    private String salt;
//    private String token;
}
