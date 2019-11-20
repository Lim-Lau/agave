package com.ansimpleasy.ans.multiple;

/**
 * @author LiuCan
 * @date 2019/8/7 11:42
 */
public class DataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /**
     * 设置数据源
     * @author LiuCan
     * @date 2019/8/7 11:44
     * @param db
     * @return void
     */
    public static void setDataSource(String db) {
        contextHolder.set(db);
    }

    /**
     * 获取当前数据源
     * @author LiuCan
     * @date 2019/8/7 11:46
     * @param
     * @return java.lang.String
     */
    public static String getDataSource() {
        return contextHolder.get();
    }

    /**
     * 清除上下文数据
     * @author LiuCan
     * @date 2019/8/7 11:47
     * @param
     * @return void
     */
    public static void clearDataSource() {
        contextHolder.remove();
    }
}
