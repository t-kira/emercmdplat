package com.kira.emercmdplat.annotation;

import java.lang.annotation.*;

/**
 * @Author: kira
 * @Date: 2020/5/4 22:30
 * @Description:自定义注解类
 */
@Target(ElementType.METHOD) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented //生成文档
public @interface MyLog {

    String value() default "";
}
