package com.ansimpleasy.agave.ans.multiple;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author LiuCan
 * @date 2019/8/7 11:47
 */
public class MultipleDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return  DataSourceContextHolder.getDataSource();
    }
}
