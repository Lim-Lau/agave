package com.ansimpleasy.ans.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LiuCan
 * @date 2019/8/7 12:48
 */
@Configuration
public class DruidConfiguration {
    @Bean
    public ServletRegistrationBean startViewServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        // 白名单
        servletRegistrationBean.addInitParameter("allow","127.0.0.1");
        // 黑名单
        // deny 与  allow 同时存在时，deny优于allow
        servletRegistrationBean.addInitParameter("deny","127.0.0.1");
        //控制台管理用户
        servletRegistrationBean.addInitParameter("loginUserName","Agave");
        servletRegistrationBean.addInitParameter("loginPassWord","G00dl^ck");
        //是否能够重置数据
        servletRegistrationBean.addInitParameter("resetEnable","false");

        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean startFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        // 添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        // 忽略过滤的格式
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
