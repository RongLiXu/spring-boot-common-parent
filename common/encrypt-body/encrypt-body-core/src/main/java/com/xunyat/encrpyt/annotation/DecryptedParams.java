package com.xunyat.encrpyt.annotation;

import com.xunyat.encrpyt.IEncryptor;

import java.lang.annotation.*;

/**
 *
 *
 * @author Li Xu
 * @version 1.0
 * @date 16/11/2023
 */
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DecryptedParams {
    String key() default "";

    String iv() default "";

    /**
     * 请求参数一定要是加密内容
     */
    boolean required() default false;

    /**
     * 请求数据时间戳校验时间差
     * 超过(当前时间-指定时间)的数据认定为伪造
     * 注意应用程序需要捕获 {@link com.xunyat.encrpyt.ParamsCryptoException} 异常
     */
    long timeout() default 3000;

    Class<? extends IEncryptor> encryptor() default IEncryptor.class;
}
