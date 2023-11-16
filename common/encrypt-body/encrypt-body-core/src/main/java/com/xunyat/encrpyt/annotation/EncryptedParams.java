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
public @interface EncryptedParams {
    String key() default "";

    String iv() default "";

    Class<? extends IEncryptor> encryptor() default IEncryptor.class;
}
