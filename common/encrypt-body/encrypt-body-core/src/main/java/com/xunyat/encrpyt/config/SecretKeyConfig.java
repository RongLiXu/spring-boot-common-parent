package com.xunyat.encrpyt.config;

import com.xunyat.encrpyt.IEncryptor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 *
 * @author Li Xu
 * @version 1.0
 * @date 16/11/2023
 */
@ConfigurationProperties("platform.params-secret")
public class SecretKeyConfig {
    private List<String> mappedKeyPrefixes;
    private Class<? extends IEncryptor> defaultEncryptor;
    private boolean failFast;
    private String defaultKey;
    private String defaultIv;
    private String privateKey;
    private String publicKey;
    private String charset = "UTF-8";
    private boolean open = true;
    private boolean showLog = false;

    /**
     * 请求数据时间戳校验时间差
     * 超过指定时间的数据认定为伪造
     */
    private boolean timestampCheck = false;

    public SecretKeyConfig(List<String> mappedKeyPrefixes, Class<? extends IEncryptor> defaultEncryptor, boolean failFast, String defaultKey, String defaultIv, String privateKey, String publicKey, String charset, boolean open, boolean showLog, boolean timestampCheck) {
        this.mappedKeyPrefixes = mappedKeyPrefixes;
        this.defaultEncryptor = defaultEncryptor;
        this.failFast = failFast;
        this.defaultKey = defaultKey;
        this.defaultIv = defaultIv;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.charset = charset;
        this.open = open;
        this.showLog = showLog;
        this.timestampCheck = timestampCheck;

        afterPropertiesSet();
    }


    private void afterPropertiesSet() {
        if (defaultEncryptor == null || defaultEncryptor == IEncryptor.class) {
            throw new IllegalArgumentException("defaultEncryptor must be not null or IEncryptor");
        }
    }


    public List<String> getMappedKeyPrefixes() {
        return mappedKeyPrefixes;
    }

    public void setMappedKeyPrefixes(List<String> mappedKeyPrefixes) {
        this.mappedKeyPrefixes = mappedKeyPrefixes;
    }

    public Class<? extends IEncryptor> getDefaultEncryptor() {
        return defaultEncryptor;
    }

    public void setDefaultEncryptor(Class<? extends IEncryptor> defaultEncryptor) {
        this.defaultEncryptor = defaultEncryptor;
    }

    public boolean isFailFast() {
        return failFast;
    }

    public void setFailFast(boolean failFast) {
        this.failFast = failFast;
    }

    public String getDefaultKey() {
        return defaultKey;
    }

    public void setDefaultKey(String defaultKey) {
        this.defaultKey = defaultKey;
    }

    public String getDefaultIv() {
        return defaultIv;
    }

    public void setDefaultIv(String defaultIv) {
        this.defaultIv = defaultIv;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isShowLog() {
        return showLog;
    }

    public void setShowLog(boolean showLog) {
        this.showLog = showLog;
    }

    public boolean isTimestampCheck() {
        return timestampCheck;
    }

    public void setTimestampCheck(boolean timestampCheck) {
        this.timestampCheck = timestampCheck;
    }
}
