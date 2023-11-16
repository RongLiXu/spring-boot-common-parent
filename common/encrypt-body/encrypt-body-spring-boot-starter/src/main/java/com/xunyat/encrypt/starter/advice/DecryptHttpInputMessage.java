package com.xunyat.encrypt.starter.advice;

import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.json.JSONUtil;
import com.xunyat.encrpyt.Base64Util;
import com.xunyat.encrpyt.JsonUtil;
import com.xunyat.encrypt.starter.exception.ParamsCryptoException;
import com.xunyat.encrpyt.annotation.DecryptedParams;
import com.xunyat.encrpyt.config.SecretKeyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.util.StringUtils;

import java.io.*;
import java.lang.reflect.Type;
import java.util.stream.Collectors;

/**
 *
 *
 * @author Li Xu
 * @version 1.0
 * @date 16/11/2023
 */
public class DecryptHttpInputMessage implements HttpInputMessage {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final HttpHeaders headers;
    private final InputStream body;


    public DecryptHttpInputMessage(HttpInputMessage inputMessage, MethodParameter methodParameter, Type targetType, SecretKeyConfig secretKeyConfig, DecryptedParams decrypt) throws Exception {
        String key = secretKeyConfig.getDefaultKey();
        String iv = secretKeyConfig.getDefaultIv();
        String privateKey =  secretKeyConfig.getPrivateKey();
        String publicKey = secretKeyConfig.getPublicKey();

        if (StringUtils.hasText(decrypt.key())) {
            key = decrypt.key();
        }
        if (StringUtils.hasText(decrypt.iv())) {
            iv = decrypt.iv();
        }

        String charset = secretKeyConfig.getCharset();
        boolean showLog = secretKeyConfig.isShowLog();
        boolean timestampCheck = secretKeyConfig.isTimestampCheck();

        if (StringUtils.hasText(privateKey)) {
            throw new IllegalArgumentException("privateKey is null");
        }

        this.headers = inputMessage.getHeaders();
        String content = new BufferedReader(new InputStreamReader(inputMessage.getBody()))
                .lines().collect(Collectors.joining(System.lineSeparator()));
        String decryptBody;
        // 未加密内容
        if (content.startsWith("{")) {
            // 必须加密
            if (decrypt.required()) {
                log.error("not support unencrypted content:{}", content);
                throw new ParamsCryptoException("not support unencrypted content");
            }
            log.info("Unencrypted without decryption:{}", content);
            decryptBody = content;
        } else {
            StringBuilder json = new StringBuilder();
            content = content.replaceAll(" ", "+");

            if (!StringUtils.isEmpty(content)) {
                String[] contents = content.split("\\|");
                for (String value : contents) {
                    value = new String(Base64Util.decode(value), charset); // TODO new String(RSAUtil.decrypt(Base64Util.decode(value), privateKey), charset);
                    json.append(value);
                }
            }
            decryptBody = json.toString();
            if(showLog) {
                log.info("Encrypted data received：{},After decryption：{}", content, decryptBody);
            }
        }

        // 开启时间戳检查
        if (timestampCheck) {
            // 容忍最小请求时间
            long toleranceTime = System.currentTimeMillis() - decrypt.timeout();
            long requestTime = JsonUtil.getNode(decryptBody, "timestamp").asLong();
            // 如果请求时间小于最小容忍请求时间, 判定为超时
            if (requestTime < toleranceTime) {
                log.error("Encryption request has timed out, toleranceTime:{}, requestTime:{}, After decryption：{}", toleranceTime, requestTime, decryptBody);
                throw new ParamsCryptoException("request timeout");
            }
        }

        this.body = new ByteArrayInputStream(decryptBody.getBytes());
    }

    @Override
    public InputStream getBody() throws IOException {
        return body;
    }

    @Override
    public HttpHeaders getHeaders() {
        return headers;
    }


}
