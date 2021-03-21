package com.wt.basketball.common;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class MyExceptionHandler {

    Logger logger = LoggerFactory.getLogger("Exception");

    @ResponseBody
    @ExceptionHandler(AppException.class)
    public BizResult appExceptionHandler(AppException ex) {
        logger.info(ex.getMsg());
        return BizResult.fall(ex.getMsg());
    }
}
