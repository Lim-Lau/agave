package com.ansimpleasy.agave.ans.auth;

import org.nutz.lang.util.NutMap;

import java.util.List;

/**
 * @author LiuCan
 * @date 2019/8/19 11:08
 */
public class AuthUser {
    String userName;
    String password;
    String token;
    List<String> roles;
    List<String> permissions;
    NutMap extInfo = NutMap.NEW();

    public List<String> getRoles() {
        return roles;
    }

    public AuthUser setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public AuthUser setPermissions(List<String> permissions) {
        this.permissions = permissions;
        return this;
    }

    public AuthUser() {
    }

    public AuthUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.token = JwtUtil.sign(userName, password);
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    public NutMap getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(NutMap extInfo) {
        this.extInfo = extInfo;
    }

    public String getToken() {
        return token;
    }

    public AuthUser addExt(String key, Object value) {
        extInfo.addv(key, value);
        return this;
    }

    public AuthUser token(String token) {
        setToken(token);
        return this;
    }

    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
