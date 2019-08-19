package com.ansimpleasy.agave.ans.common;

import org.nutz.lang.Lang;

/**
 * @author LiuCan
 * @date 2019/8/19 15:14
 */
public class CredentialsMatcher {
    /**
     *
     */
    protected CredentialsMatcher() {}

    /**
     * 加密
     *
     * @param userName
     *            用户名
     * @param password
     *            密码
     * @return 密码密文
     */
    public static String password(char[] password, String userName) {
        return password(userName, new String(password));
    }

    /**
     * 加密
     *
     * @param userName
     *            用户名
     * @param password
     *            密码
     * @return 密码密文
     */
    public static String password(String userName, String password) {
        return password(password.getBytes(), userName.getBytes());
    }

    /**
     *
     * @param p
     *            密码
     * @param salt
     *            盐
     * @return 密码密文
     */
    public static String password(byte[] p, byte[] salt) {
        return Lang.digest("MD5", p, salt, 2);
    }
}
