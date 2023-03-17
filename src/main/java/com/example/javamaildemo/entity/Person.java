package com.example.javamaildemo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@KeySequence(value = "person_id_seq")
//@TableName("t_person") 配置文件统一设置了prefix所以这里不能写死
public class Person {
    // 不输入会默认设置主键id为null报错
    // 1.要添加主键生成配置 2.添加@KeySequence(value = "person_id_seq")
    // 原理是：先select nextval('person_id_seq')查到了下一个id是什么，然后赋值给id（执行了两次sql）
    //    --> 且配置了主键自动生策略,所以可以在id为null的时候从seq中generate主键
    @TableId(value = "id", type = IdType.INPUT)
    // @TableId(value = "id", type = IdType.UUID)
    // 底层默认雪花算法, 但是实体上如果设置了id,则以用户输入为准
    // @TableId(value = "id", type = IdType.NONE)
    // mybatis-plus底层不会在sql上insert id字段
    // INSERT INTO t_person (name, age) VALUES(?,?)
    // @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("name")
    private String name;

    @TableField("age")
    private int age;

    @TableField("password")
    private String password;
//    默认1是已删除，delete/remove方法会默认改成update语句
//    @TableLogic
//    private Integer deleted;


}
