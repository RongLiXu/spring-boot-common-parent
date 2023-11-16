package com.xunyat.encrypt.starter.annotation;

import java.lang.annotation.*;

/**
 * 统一返回包装标识注解
 * @author Li Xu
 * @version 1.0
 * @date 2021/9/29
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreParamsSecretAdvice {

    /**
     * 是否进行全局异常处理封装
     * @return true:进行处理;  false:不进行异常处理
     */
    boolean errorDispose() default true;

}
