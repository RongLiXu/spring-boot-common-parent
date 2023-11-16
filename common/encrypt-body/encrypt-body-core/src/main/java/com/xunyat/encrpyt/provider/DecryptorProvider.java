package com.xunyat.encrpyt.provider;

import com.xunyat.encrpyt.IEncryptor;
import com.xunyat.encrpyt.annotation.DecryptedParams;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Li Xu
 * @version 1.0
 * @date 2022/4/7
 */
public class DecryptorProvider {
    private static final ConcurrentHashMap<DecryptedParams, IEncryptor> CACHE = new ConcurrentHashMap<>();

    public static IEncryptor get(DecryptedParams encryptedField, Class<? extends IEncryptor> globalDefaultEncryptor) {
        // globalDefaultEncryptor is always the same object
        return CACHE.computeIfAbsent(encryptedField, ignored -> {
            try {
                Class<? extends IEncryptor> specifiedEncryptor = encryptedField.encryptor();
                Class<? extends IEncryptor> encryptor = specifiedEncryptor == IEncryptor.class ? globalDefaultEncryptor : specifiedEncryptor;
                return encryptor.getDeclaredConstructor().newInstance();
                //return encryptor.newInstance();
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
