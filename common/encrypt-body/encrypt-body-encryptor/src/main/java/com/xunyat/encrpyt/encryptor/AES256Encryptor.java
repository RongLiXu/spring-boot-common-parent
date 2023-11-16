package com.xunyat.encrpyt.encryptor;

import org.jasypt.util.text.AES256TextEncryptor;
import org.jasypt.util.text.TextEncryptor;

/**
 *
 * @author Li Xu
 * @version 1.0
 * @date 2022/4/7
 */
public class AES256Encryptor extends CachedTextEncryptor {

    @Override
    protected TextEncryptor createTextEncryptor(String key) {
        AES256TextEncryptor encryptor = new AES256TextEncryptor();
        encryptor.setPassword(key);
        return encryptor;
    }
}
