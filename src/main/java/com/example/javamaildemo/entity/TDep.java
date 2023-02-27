package com.example.javamaildemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName t_dep
 */
@TableName(value ="t_dep")
@Data
public class TDep implements Serializable {
    /**
     * 部门ID
     */
    @TableId
    private Long depId;

    /**
     * 部门名称
     */
    private String depName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}