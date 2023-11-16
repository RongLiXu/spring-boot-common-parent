package com.xunyat.encrypt.starter.annotation;

import com.xunyat.encrypt.starter.ParamsCryptoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 *
 * @author Li Xu
 * @version 1.0
 * @date 16/11/2023
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Import({
        ParamsCryptoConfiguration.class
})
public @interface EnableParamsSecurity {

}
