package com.xunyat.encrypt.starter.exception;

/**
 *
 *
 * @author Li Xu
 * @version 1.0
 * @date 16/11/2023
 */
public class ParamsCryptoException extends RuntimeException {

    public ParamsCryptoException() {
    }

    public ParamsCryptoException(String message) {
        super(message);
    }

    public ParamsCryptoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamsCryptoException(Throwable cause) {
        super(cause);
    }

    public ParamsCryptoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
