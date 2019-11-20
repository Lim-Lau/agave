package com.ansimpleasy.ans.aop;

import com.ansimpleasy.ans.annotation.DataSource;
import com.ansimpleasy.ans.multiple.DataSourceContextHolder;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author LiuCan
 * @date 2019/8/7 11:56
 */
@Component
@Aspect
@Order(-1)
public class DataSourceAspect {
    Log logger = Logs.getLog(DataSourceAspect.class);

    @Pointcut("@within(com.ansimpleasy.ans.annotation.DataSource) || @annotation(com.ansimpleasy.ans.annotation.DataSource)")
    public void pointCut() {}

    @Before("pointCut()&&@annotation(dataSource))")
    public void doBefore(DataSource dataSource) {
        logger.info("选择数据源----"+dataSource.value().getValue());
        DataSourceContextHolder.setDataSource(dataSource.value().getValue());
    }

    @After("pointCut()")
    public void doAfter() {
        DataSourceContextHolder.clearDataSource();
    }

}
