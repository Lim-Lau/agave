package com.ansimpleasy.ans.controller;

import com.ansimpleasy.ans.entity.User;
import com.ansimpleasy.ans.service.acl.AuthService;
import com.chinare.axe.auth.AuthException;
import org.nutz.json.Json;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author LiuCan
 * @date 2019/11/20 16:21
 */
public class BaseController {
    public static final Log logger = Logs.get();
    @Autowired
    protected AuthService authService;

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    public String base() {
        return request.getContextPath();
    }

    public int fixPage(int page) {
        return ((page <= 0) ? 1 : page);
    }

    public String fixSearchKey(String key) {
        return ((Strings.isBlank(key)) ? "" : key);
    }

    public String ip() {
        return Lang.getIP(request);
    }

    public HttpServletRequest request() {
        return request;
    }

    public User user() {
        logger.infof("authUser的请求:%s,%s", request.getHeader("token"), Json.toJson(request.getParameterMap()));
        User user = authService.info();
        if (user == null) {
            throw new AuthException();
        }
        return user;
    }

}
