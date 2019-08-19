package com.ansimpleasy.agave.ans.auth;

import org.nutz.lang.Strings;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author LiuCan
 * @date 2019/8/19 11:12
 */
public abstract class AbstractAuthService implements AuthServiceImpl{

    static final String AUTHORIZATION_KEY = "Authorization";

    HttpServletRequest request;

    /**
     *
     * @param request
     *            http 请求
     */
    public AbstractAuthService(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public boolean authentication(List<String> withoutAuthenticationUrlRegulars) {
        if (pass(request.getRequestURI(), withoutAuthenticationUrlRegulars)) {
            return true;
        }
        return user() != null;
    }

    /**
     * @param uri
     *            请求地址
     * @param withoutAuthenticationUrlRegulars
     *            放行url正则
     * @return 是否放行
     */
    protected boolean pass(String uri, List<String> withoutAuthenticationUrlRegulars) {
        for (String regular : withoutAuthenticationUrlRegulars) {
            if (Pattern.matches(regular, uri)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String token() {
        String token = request.getHeader(AUTHORIZATION_KEY);
        if (token == null) {
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie ele : cookies) {
                    if (Strings.equalsIgnoreCase(ele.getName(), AUTHORIZATION_KEY)) {
                        token = ele.getValue();
                    }
                }
            }

        }
        if (token == null) {
            token = request.getParameter(AUTHORIZATION_KEY);
        }
        return token;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.chinare.axe.auth.AuthService#userName()
     */
    @Override
    public String userName() {
        if (token() == null) {
            return null;
        }
        return JwtUtil.getUsername(token());
    }
}
