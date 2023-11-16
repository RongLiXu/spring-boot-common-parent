package com.xunyat.encrpyt.encryptor;

import com.xunyat.encrpyt.IEncryptor;
import org.jasypt.util.text.TextEncryptor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Li Xu
 * @version 1.0
 * @date 2022/4/7
 */
public abstract class CachedTextEncryptor implements IEncryptor {

    private final Map<String, TextEncryptor> cache = new ConcurrentHashMap<>();

    @Override
    public String encrypt(Object val2bEncrypted, String key) throws Exception {
        if (val2bEncrypted == null) {
            return null;
        }
        return get(key).encrypt(val2bEncrypted.toString());
    }

    @Override
    public String decrypt(Object val2bDecrypted, String key) throws Exception {
        if (val2bDecrypted == null) {
            return null;
        }
        return get(key).decrypt(val2bDecrypted.toString());
    }

    @Override
    public String encrypt(Object val2bEncrypted, String key, String iv) throws Exception {
        return encrypt(val2bEncrypted, key);
    }

    @Override
    public String decrypt(Object val2bDecrypted, String key, String iv) throws Exception {
        return decrypt(val2bDecrypted, key);
    }

    protected TextEncryptor get(String key) {
        return cache.computeIfAbsent(key, this::createTextEncryptor);
    }

    protected abstract TextEncryptor createTextEncryptor(String key);
}
