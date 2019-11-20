package com.ansimpleasy.ans.exception;

/**
 * @author LiuCan
 * @date 2019/8/20 9:57
 */
public class AnsException extends RuntimeException {

   private static final long serialVersionUID = 1L;

    public AnsException() {
        super();
    }

    public AnsException(String msg) {
        super(msg);
    }

}
