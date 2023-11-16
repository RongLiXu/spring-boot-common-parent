package com.xunyat.encrpyt.encryptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import cn.hutool.crypto.symmetric.SM4;
import com.xunyat.encrpyt.IEncryptor;
import com.xunyat.encrpyt.annotation.AESEncryption;
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
public class SM2Encryptor implements IEncryptor {
    private Logger logger = LoggerFactory.getLogger(SM2Encryptor.class);

    @Override
    public String encrypt(Object val2bEncrypted, String key) throws Exception {
        return encrypt(val2bEncrypted, key, null);
    }

    /**
     * 加密方法
     *
     * @param val2bEncrypted
     * @param key 私钥，可以使用PKCS#8、D值或PKCS#1规范
     * @param iv 公钥，可以使用X509、Q值或PKCS#1规范
     * */
    @Override
    public String encrypt(Object val2bEncrypted, String key, String iv) throws Exception {
        if (val2bEncrypted == null) {
            return null;
        }
        if (null == iv && 0 == iv.length()) {
            return null;
        }

        SM2 sm2 = new SM2(key.getBytes(StandardCharsets.UTF_8), iv.getBytes(StandardCharsets.UTF_8));

        byte[] bytes = sm2.encrypt(val2bEncrypted.toString(), KeyType.PrivateKey);
        return Base64.getEncoder().encodeToString(bytes);
    }

    @Override
    public String decrypt(Object val2bDecrypted, String key) throws Exception {
        return decrypt(val2bDecrypted, key, null);
    }

    /**
     * 解密方法
     *
     * @param val2bDecrypted
     * @param key 私钥，可以使用PKCS#8、D值或PKCS#1规范
     * @param iv 公钥，可以使用X509、Q值或PKCS#1规范
     * */
    @Override
    public String decrypt(Object val2bDecrypted, String key, String iv) throws Exception {
        if (val2bDecrypted == null) {
            return null;
        }
        if (null == iv && 0 == iv.length()) {
            return null;
        }

        SM2 sm2 = new SM2(key.getBytes(StandardCharsets.UTF_8), iv.getBytes(StandardCharsets.UTF_8));

        byte[] bytes = sm2.decrypt(val2bDecrypted.toString(), KeyType.PrivateKey);
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
