package com.ansimpleasy.agave.ans.exception;

import com.ansimpleasy.agave.ans.result.Result;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 自定义全局异常
 * @author LiuCan
 * @date 2019/8/20 9:54
 */
@RestController
@ControllerAdvice
public class AnsExceptionHandler {
    Log log = Logs.get();

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result defaultErrorHandler(HttpServletResponse response, Exception e){
        log.error("error=>", e);
        if (e instanceof AnsException) {
            log.infof("自定义异常", e);
            return Result.exception(e.getMessage());
        } else {
            return Result.exception("系统异常");
        }
    }
}
