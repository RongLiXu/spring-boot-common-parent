package com.xunyat.encrpyt.encryptor;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.SM4;
import com.xunyat.encrpyt.IEncryptor;
import com.xunyat.encrpyt.annotation.AESEncryption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * SM4 cbc 加密器
 *
 * @author Li Xu
 * @version 1.0
 * @date 2022/4/7
 */
@AESEncryption
public class SM4CBCEncryptor implements IEncryptor {
    private Logger logger = LoggerFactory.getLogger(SM4CBCEncryptor.class);

    @Override
    public String encrypt(Object val2bEncrypted, String key) throws Exception {
        return encrypt(val2bEncrypted, key, null);
    }

    @Override
    public String encrypt(Object val2bEncrypted, String key, String iv) throws Exception {
        if (val2bEncrypted == null) {
            return null;
        }
        SM4 sm4 = new SM4(Mode.CBC, Padding.PKCS5Padding, key.getBytes(StandardCharsets.UTF_8));
        if (null != iv && 0 != iv.length()) {
            sm4.setIv(iv.getBytes(StandardCharsets.UTF_8));
        }
        byte[] bytes = sm4.encrypt(val2bEncrypted.toString());
        return Base64.getEncoder().encodeToString(bytes);
    }

    @Override
    public String decrypt(Object val2bDecrypted, String key) throws Exception {
        return decrypt(val2bDecrypted, key, null);
    }

    @Override
    public String decrypt(Object val2bDecrypted, String key, String iv) throws Exception {
        if (val2bDecrypted == null) {
            return null;
        }
        SM4 sm4 = new SM4(Mode.CBC, Padding.PKCS5Padding, key.getBytes(StandardCharsets.UTF_8));
        if (null != iv && 0 != iv.length()) {
            sm4.setIv(iv.getBytes(StandardCharsets.UTF_8));
        }

        byte[] bytes = sm4.decrypt(val2bDecrypted.toString());
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
