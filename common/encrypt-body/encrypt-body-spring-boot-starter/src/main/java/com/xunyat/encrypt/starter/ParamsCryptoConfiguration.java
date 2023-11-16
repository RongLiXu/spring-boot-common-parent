package com.xunyat.encrypt.starter;

import com.xunyat.encrpyt.config.SecretKeyConfig;
import com.xunyat.encrypt.starter.advice.EncryptRequestBodyAdvice;
import com.xunyat.encrypt.starter.advice.EncryptResponseBodyAdvice;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 *
 * @author Li Xu
 * @version 1.0
 * @date 16/11/2023
 */
@Configuration
@EnableConfigurationProperties(SecretKeyConfig.class)
@PropertySource(value = "classpath:secret.yml", encoding = "UTF-8", factory = YamlPropertySourceFactory.class)
public class ParamsCryptoConfiguration implements WebMvcConfigurer {


    @Bean
    public EncryptRequestBodyAdvice encryptRequestBodyAdvice(SecretKeyConfig secretKeyConfig){
        return new EncryptRequestBodyAdvice(secretKeyConfig);
    }

    @Bean
    public EncryptResponseBodyAdvice encryptResponseBodyAdvice(SecretKeyConfig secretKeyConfig){
        return new EncryptResponseBodyAdvice(secretKeyConfig);
    }

}
