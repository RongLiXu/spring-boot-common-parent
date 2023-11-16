package com.xunyat.encrpyt.encryptor;

import org.jasypt.util.text.TextEncryptor;

/**
 *
 * @author Li Xu
 * @version 1.0
 * @date 2022/4/7
 */
public class StrongTextEncryptor extends CachedTextEncryptor{

    @Override
    protected TextEncryptor createTextEncryptor(String key) {
        org.jasypt.util.text.StrongTextEncryptor encryptor = new org.jasypt.util.text.StrongTextEncryptor();
        encryptor.setPassword(key);
        return encryptor;
    }
}
