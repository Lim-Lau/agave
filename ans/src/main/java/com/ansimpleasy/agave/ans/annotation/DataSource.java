package com.ansimpleasy.agave.ans.annotation;

import com.ansimpleasy.agave.ans.enums.DataSourceEnum;

import java.lang.annotation.*;

/**
 * @author LiuCan
 * @date 2019/8/7 11:53
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    DataSourceEnum value() default DataSourceEnum.RELEASE;
}
