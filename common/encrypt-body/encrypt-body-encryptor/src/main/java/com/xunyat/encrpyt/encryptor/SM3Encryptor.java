package com.xunyat.encrpyt.encryptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.SM3;
import com.xunyat.encrpyt.IEncryptor;
import com.xunyat.encrpyt.annotation.AESEncryption;
import org.jasypt.util.text.TextEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * SM2 加密器
 *
 * @author Li Xu
 * @version 1.0
 * @date 2022/4/7
 */
@AESEncryption
public class SM3Encryptor implements IEncryptor {
    private Logger logger = LoggerFactory.getLogger(SM3Encryptor.class);

    @Override
    public String encrypt(Object val2bEncrypted, String key) throws Exception {
        return encrypt(val2bEncrypted, key, null);
    }

    @Override
    public String encrypt(Object val2bEncrypted, String key, String iv) throws Exception {
        if (val2bEncrypted == null) {
            return null;
        }

        SM3 sm3 = new SM3(StrUtil.utf8Bytes(key));
        byte[] bytes = sm3.digest(val2bEncrypted.toString());
        return new String(bytes, StandardCharsets.UTF_8);
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
        return null;
    }

}
