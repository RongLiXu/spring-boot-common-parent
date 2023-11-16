package com.xunyat.encrpyt.encryptor;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import com.xunyat.encrpyt.IEncryptor;
import com.xunyat.encrpyt.annotation.AESEncryption;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Aes 加密器
 *  - Mode.CBC
 *  - Padding.PKCS5Padding
 * @author Li Xu
 * @version 1.0
 * @date 2022/4/7
 */
@AESEncryption
public class AESECBEncryptor implements IEncryptor {

    @Override
    public String encrypt(Object val2bEncrypted, String key) throws Exception {
        return encrypt(val2bEncrypted, key, null);
    }

    @Override
    public String encrypt(Object val2bEncrypted, String key, String iv) throws Exception {
        if (val2bEncrypted == null) {
            return null;
        }
        AES aes = new AES(Mode.ECB, Padding.PKCS5Padding, key.getBytes(StandardCharsets.UTF_8));
        if (null != iv && 0 != iv.length()) {
            aes.setIv(iv.getBytes(StandardCharsets.UTF_8));
        }
        byte[] bytes = aes.encrypt(val2bEncrypted.toString());
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
        AES aes = new AES(Mode.ECB, Padding.PKCS5Padding, key.getBytes(StandardCharsets.UTF_8));
        if (null != iv && 0 != iv.length()) {
            aes.setIv(iv.getBytes(StandardCharsets.UTF_8));
        }
        byte[] bytes = aes.decrypt(val2bDecrypted.toString());
        return new String(bytes,StandardCharsets.UTF_8);
    }
}
