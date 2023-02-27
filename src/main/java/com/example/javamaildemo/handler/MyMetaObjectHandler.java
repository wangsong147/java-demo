package com.example.javamaildemo.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.apache.ibatis.reflection.MetaObject;

public class MyMetaObjectHandler implements MetaObjectHandler {


    @Override
    public void insertFill(MetaObject metaObject) {
//        Object id = metaObject.getValue("deleted");
//        if (ObjectUtils.isNull(id)) {
//            this.strictInsertFill(metaObject,"deleted",Integer.class,11);
//        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
    }

}
