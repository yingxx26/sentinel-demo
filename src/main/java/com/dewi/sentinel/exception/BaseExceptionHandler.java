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
        return new HzfcResponse().fail(e.getMessage());
    }


    /*@ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public HzfcResponse handleException(Exception e) {
        log.error("系统内部异常，异常信息", e);
        return new HzfcResponse().message("系统内部异常");
    }*/

    /**
     * 自定义资源不存在异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public HzfcResponse notFoundExceptionHandler(NotFoundException e) {
        return new HzfcResponse().message(e.getMessage());
    }

    @ExceptionHandler(value = ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public HzfcResponse handleForbiddenException(ForbiddenException e) {
        return new HzfcResponse().message(e.getMessage());
    }

    @ExceptionHandler(value = NotAllowedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public HzfcResponse handleNotAllowedException(NotAllowedException e) {
        return new HzfcResponse().message(e.getMessage());
    }

  /* @ExceptionHandler(value = AccessDeniedException.class)
   @ResponseStatus(HttpStatus.UNAUTHORIZED)
   public HzfcResponse handleAccessDeniedException() {
      return new HzfcResponse().message("没有权限访问该资源");
   }
*/

    /**
     * 使用@Valid/@Validated注解进行验证时未通过的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HzfcResponse methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        StringBuilder sb = new StringBuilder(10);
        allErrors.stream().forEach(oe -> sb.append(oe.getDefaultMessage()).append(";"));
        String body = sb.toString();
        if (body.endsWith(";")) {
            body = body.substring(0, body.length() - 1);
        }
        return new HzfcResponse().message(body);
    }

    /**
     * 统一处理请求参数校验(实体对象传参)
     *
     * @param e BindException
     * @return FebsResponse
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HzfcResponse handleBindException(BindException e) {
        StringBuilder message = new StringBuilder();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            message.append(error.getField()).append(error.getDefaultMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        return new HzfcResponse().message(message.toString());
    }

    /**
     * 统一处理请求参数校验(普通传参)
     *
     * @param e ConstraintViolationException
     * @return FebsResponse
     */
   /*@ExceptionHandler(value = ConstraintViolationException.class)
   @ResponseStatus(HttpStatus.BAD_REQUEST)
   public HzfcResponse handleConstraintViolationException(ConstraintViolationException e) {
      StringBuilder message = new StringBuilder();
      Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
      for (ConstraintViolation<?> violation : violations) {
         Path path = violation.getPropertyPath();
         String[] pathArr = StringUtils.splitByWholeSeparatorPreserveAllTokens(path.toString(), ".");
         message.append(pathArr[1]).append(violation.getMessage()).append(",");
      }
      message = new StringBuilder(message.substring(0, message.length() - 1));
      return new HzfcResponse().message(message.toString());
   }*/
    @ExceptionHandler(value = FileDownloadException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleFileDownloadException(FileDownloadException e) {
        log.error("FileDownloadException", e);
    }


   /*@ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   public HzfcResponse handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
      return new HzfcResponse().message("改方法不支持" + StringUtils.substringBetween(e.getMessage(), "'", "'") + "媒体类型");
   }

   @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
   @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
   public HzfcResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
      return new HzfcResponse().message("该方法不支持" + StringUtils.substringBetween(e.getMessage(), "'", "'") + "请求方法");
   }*/

}
