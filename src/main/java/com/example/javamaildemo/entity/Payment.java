package com.example.javamaildemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName payment
 */
@TableName(value ="payment")
@Data
public class Payment implements Serializable {
    /**
     * ID
     */
    @TableId
    private Long id;

    /**
     * 
     */
    private String serial;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}