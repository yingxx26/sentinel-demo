package com.dewi.sentinel.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @author MrBird
 */
@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler {


    @ExceptionHandler(value = HzfcException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public HzfcResponse handleFebsException(HzfcException e) {
        log.error("业务错误", e);
        return new HzfcResponse().fail(e.getMessage() + "  全局");
    }


    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public HzfcResponse handleException(Exception e) {
        log.error("系统内部异常，异常信息", e);
        return new HzfcResponse().message("系统内部异常");
    }



}
