package com.xunyat.encrpyt.encryptor;

import org.jasypt.util.text.TextEncryptor;

/**
 *
 * @author Li Xu
 * @version 1.0
 * @date 2022/4/7
 */
public class BasicTextEncryptor extends CachedTextEncryptor{
    @Override
    protected TextEncryptor createTextEncryptor(String key) {
        org.jasypt.util.text.BasicTextEncryptor encryptor = new org.jasypt.util.text.BasicTextEncryptor();
        encryptor.setPassword(key);

        return encryptor;
    }
}
