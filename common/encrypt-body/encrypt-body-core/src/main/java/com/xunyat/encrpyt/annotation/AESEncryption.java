package com.xunyat.encrpyt.annotation;

import java.lang.annotation.*;

/**
 *
 * @author Li Xu
 * @version 1.0
 * @date 2022/4/7
 */
@Documented
@Inherited
@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AESEncryption {
}