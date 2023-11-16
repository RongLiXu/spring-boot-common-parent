package com.xunyat.encrpyt;

import com.xunyat.encrpyt.annotation.DecryptedParams;
import com.xunyat.encrpyt.annotation.EncryptedParams;

/**
 *
 * @author Li Xu
 * @version 1.0
 * @date 2022/4/7
 */
public class Util {

    public static String getEncryptedParamsKey(EncryptedParams encryptedField, String defaultKey) {
        String key = encryptedField.key();
        if (key == null || key.equals("")) {
            return defaultKey == null ? "" : defaultKey;
        }
        return key;
    }

    public static String getEncryptedParamsIv(EncryptedParams encryptedField, String defaultIv) {
        String iv = encryptedField.iv();
        if (iv == null || iv.equals("")) {
            return defaultIv == null ? "" : defaultIv;
        }
        return iv;
    }

    public static String getDecryptedParamsKey(DecryptedParams encryptedField, String defaultKey) {
        String key = encryptedField.key();
        if (key == null || key.equals("")) {
            return defaultKey == null ? "" : defaultKey;
        }
        return key;
    }

    public static String getDecryptedParamsIv(DecryptedParams encryptedField, String defaultIv) {
        String iv = encryptedField.iv();
        if (iv == null || iv.equals("")) {
            return defaultIv == null ? "" : defaultIv;
        }
        return iv;
    }

    public static boolean encryptionRequired(Object parameter) {
        return decryptionRequired(parameter);
    }

    public static boolean decryptionRequired(Object parameter) {
        return !(parameter == null || parameter instanceof Double || parameter instanceof Integer
                || parameter instanceof Long || parameter instanceof Short || parameter instanceof Float
                || parameter instanceof Boolean || parameter instanceof Character
                || parameter instanceof Byte);
    }


}