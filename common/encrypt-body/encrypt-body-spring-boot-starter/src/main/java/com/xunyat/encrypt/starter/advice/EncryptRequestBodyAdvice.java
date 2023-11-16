package com.xunyat.encrypt.starter.advice;

import com.xunyat.encrypt.starter.exception.ParamsCryptoException;
import com.xunyat.encrpyt.annotation.DecryptedParams;
import com.xunyat.encrpyt.config.SecretKeyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * 加密参数请求Body处理
 *
 * @author Li Xu
 * @version 1.0
 * @date 16/11/2023
 */
public class EncryptRequestBodyAdvice implements RequestBodyAdvice {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private boolean encrypt;
    private DecryptedParams decryptAnnotation;

    private SecretKeyConfig secretKeyConfig;

    public EncryptRequestBodyAdvice(SecretKeyConfig secretKeyConfig){
        this.secretKeyConfig = secretKeyConfig;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        Method method = methodParameter.getMethod();
        if (Objects.isNull(method)) {
            encrypt = false;
            return false;
        }
        if (method.isAnnotationPresent(DecryptedParams.class) && secretKeyConfig.isOpen()) {
            encrypt = true;
            decryptAnnotation = methodParameter.getMethodAnnotation(DecryptedParams.class);
            return true;
        }
        // 此处如果按照原逻辑直接返回encrypt, 会造成一次修改为true之后, 后续请求都会变成true, 在不支持时, 需要做修正
        encrypt = false;
        return false;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
                                           Class<? extends HttpMessageConverter<?>> converterType){
        if (encrypt) {
            try {
                return new DecryptHttpInputMessage(inputMessage, parameter, targetType, secretKeyConfig, decryptAnnotation);
            } catch (ParamsCryptoException e) {
                throw e;
            } catch (Exception e) {
                log.error("Decryption failed", e);
            }
        }
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
                                Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

}
