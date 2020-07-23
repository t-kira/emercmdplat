package com.kira.emercmdplat.annotation;

import java.lang.annotation.*;

/**
 * @Author: kira
 * @Date: 2020/7/23 14:50
 * @Description:机构权限注解
 */
@Target(ElementType.METHOD) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
public @interface MechanismPermission {

    String message() default "机构权限配置";
}
