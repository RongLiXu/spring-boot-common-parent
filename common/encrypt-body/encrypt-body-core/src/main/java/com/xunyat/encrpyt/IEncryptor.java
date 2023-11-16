package com.xunyat.encrpyt;

/**
 *
 * @author Li Xu
 * @version 1.0
 * @date 2022/4/7
 */
public interface IEncryptor {

    String encrypt(Object val2bEncrypted, String key) throws Exception;

    String encrypt(Object val2bEncrypted, String key, String iv) throws Exception;

    String decrypt(Object val2bDecrypted, String key) throws Exception;

    String decrypt(Object val2bDecrypted, String key, String iv) throws Exception;

}
