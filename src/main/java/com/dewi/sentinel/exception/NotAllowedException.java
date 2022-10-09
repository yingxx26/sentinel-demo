package com.dewi.sentinel.exception;

/**
 * 自定义资源不存在异常类
 *
 * @author wendell
 */
public class NotAllowedException extends RuntimeException {

   private static final long serialVersionUID = -6916154462432027437L;

   public NotAllowedException(String message) {
      super(message);
   }

}
