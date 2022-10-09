package com.dewi.sentinel.exception;

/**
 * 自定义资源不存在异常类
 *
 * @author wendell
 */
public class ForbiddenException extends RuntimeException {

   private static final long serialVersionUID = -6916154462432027437L;

   public ForbiddenException(String message) {
      super(message);
   }

}
