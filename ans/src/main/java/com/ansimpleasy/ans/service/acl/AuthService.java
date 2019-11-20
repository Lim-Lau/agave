package com.ansimpleasy.ans.service.acl;

import com.ansimpleasy.ans.auth.AbstractAuthService;
import com.ansimpleasy.ans.auth.AuthUser;
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
     * @param request
     */
    @Autowired
    public AuthService(HttpServletRequest request) {
        super(request);
    }
}
