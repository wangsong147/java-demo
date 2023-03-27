package com.example.javamaildemo.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
public class PlusModel extends Model<PlusModel> {
    private String username;
    private String password;
}
