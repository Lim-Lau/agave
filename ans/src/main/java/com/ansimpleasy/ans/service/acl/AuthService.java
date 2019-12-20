package com.ansimpleasy.ans.service.acl;

import com.ansimpleasy.ans.auth.AbstractAuthService;
import com.ansimpleasy.ans.auth.AuthUser;
import com.ansimpleasy.ans.entity.user.User;
import com.ansimpleasy.ans.service.impl.UserServiceImpl;
import org.nutz.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author LiuCan
 * @date 2019/8/19 11:13
 */
@Service
public class AuthService extends AbstractAuthService {

    @Autowired
    private UserServiceImpl userService;

    @Override
    public List<String> roles() {
        return null;
    }

    @Override
    public List<String> permissions() {
        return null;
    }

    @Override
    public AuthUser user() {
        return null;
    }

    @Override
    public AuthUser login(LoginDto loginDto) {
        return null;
    }
    /**
     * - 当前登录用户信息
     *
     * @return 当前登录用户
     */
    public User info() {
        String userName = userName();
        if (Strings.isBlank(userName)) {
            return null;
        }
        return userService.getByName(userName);
    }

    /**
     * @param request
     */
    @Autowired
    public AuthService(HttpServletRequest request) {
        super(request);
    }
}
