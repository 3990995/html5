package com.liao27.permission;

import java.lang.annotation.*;

/**
 * 与拦截器结合使用 验证权限
 * Created by main on 2018/6/13.
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RequiredPermission {
    String value();
}