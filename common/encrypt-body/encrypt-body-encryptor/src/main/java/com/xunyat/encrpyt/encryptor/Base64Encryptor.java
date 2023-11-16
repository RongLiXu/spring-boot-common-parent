package com.xunyat.encrpyt.encryptor;

import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.crypto.SecureUtil;
import com.xunyat.encrpyt.IEncryptor;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 *
 * @author Li Xu
 * @version 1.0
 * @date 2022/4/7
 */
public class Base64Encryptor implements IEncryptor {
    @Override
    public String encrypt(Object val2bEncrypted, String key) throws Exception {
        return Base64.getEncoder().encodeToString(val2bEncrypted.toString().getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String encrypt(Object val2bEncrypted, String key, String iv) throws Exception {
        return encrypt(val2bEncrypted,key);
    }

    @Override
    public String decrypt(Object val2bDecrypted, String key) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(val2bDecrypted.toString());
        return new String(bytes, StandardCharsets.UTF_8);
    }

    @Override
    public String decrypt(Object val2bDecrypted, String key, String iv) throws Exception {
        return decrypt(val2bDecrypted,key);
    }
}
