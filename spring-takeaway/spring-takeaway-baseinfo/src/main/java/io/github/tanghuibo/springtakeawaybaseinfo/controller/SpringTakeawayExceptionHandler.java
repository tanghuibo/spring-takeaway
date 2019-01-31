package io.github.tanghuibo.springtakeawaybaseinfo.controller;

import io.github.tanghuibo.result.entity.Result;
import io.github.tanghuibo.result.util.ResultTool;
import io.github.tanghuibo.springtakeawaybaseinfo.exception.SpringTakeawayException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @description: 统一异常处理
 * @author: tanghuibo aa18984850147@qq.com
 * @create: 2019-02-01 03:20
 **/
@RestControllerAdvice
public class SpringTakeawayExceptionHandler {

    /**
     * 处理自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(SpringTakeawayException.class)
    public Result handleException(SpringTakeawayException e){
        return ResultTool.fail(e.getError());
    }
}
