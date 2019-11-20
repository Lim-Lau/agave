package com.ansimpleasy.ans.auth;

import java.util.List;

/**
 * @author LiuCan
 * @date 2019/8/19 11:06
 */
public interface AuthServiceImpl {
    public static class LoginDto {
        String name;
        String password;
        boolean rememberMe;

        public boolean isRememberMe() {
            return rememberMe;
        }

        public void setRememberMe(boolean rememberMe) {
            this.rememberMe = rememberMe;
        }

        public LoginDto() {
            super();
        }

        /**
         * @param name
         *            用户名
         * @param password
         *            密码
         */
        public LoginDto(String name, String password) {
            super();
            this.name = name;
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

    }

    public List<String> roles();

    public List<String> permissions();

    public AuthUser user();

    public String token();

    public String userName();

    public AuthUser login(LoginDto loginDto);

    /**
     * 认证检查
     *
     * @param withoutAuthenticationUrlRegulars
     *            不需要检查的url正则表达式
     * @return 认证检查通过状态
     */
    public boolean authentication(List<String> withoutAuthenticationUrlRegulars);
}
