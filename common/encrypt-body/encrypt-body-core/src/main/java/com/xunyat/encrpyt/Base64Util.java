package com.xunyat.encrpyt;

import cn.hutool.core.codec.Base64;

/**
 * Base64
 *
 * @author Li Xu
 * @version 1.0
 * @date 16/11/2023
 */
public class Base64Util {
    /**
     * Decoding to binary
     * @param base64 base64
     * @return byte
     * @throws Exception Exception
     */
    public static byte[] decode(String base64) throws Exception {
        return Base64.decode(base64);
    }

    /**
     * Binary encoding as a string
     * @param bytes byte
     * @return String
     * @throws Exception Exception
     */
    public static String encode(byte[] bytes) throws Exception {
        return new String(Base64.encode(bytes));
    }

}
