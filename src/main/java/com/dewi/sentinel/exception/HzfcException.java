package com.dewi.sentinel.exception;

/**
 * FEBS系统异常
 *
 * @author MrBird
 */
public class HzfcException extends RuntimeException {

   private static final long serialVersionUID = -6916154462432027437L;

   public HzfcException(String message) {
      super(message);
   }
}
