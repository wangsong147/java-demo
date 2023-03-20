package com.example.javamaildemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName permission
 */
@TableName(value ="permission", schema = "wstest")
@Data
public class Permission implements Serializable {
    @TableId(type = IdType.AUTO,value = "id")
    private Long id;
    private Long uid;
    private String permission;
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}