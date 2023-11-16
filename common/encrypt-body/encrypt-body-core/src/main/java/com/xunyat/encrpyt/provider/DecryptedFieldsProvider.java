package com.xunyat.encrpyt.provider;

import com.xunyat.encrpyt.annotation.DecryptedParams;
import com.xunyat.encrpyt.annotation.EncryptedParams;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 *
 * @author Li Xu
 * @version 1.0
 * @date 2022/4/7
 */
public class DecryptedFieldsProvider {
    private static final Map<Class<?>, Set<Field>> encryptedFieldCache = new ConcurrentHashMap<>();

    public static Set<Field> get(Class<?> parameterClass) {
        return encryptedFieldCache.computeIfAbsent(parameterClass, aClass -> {
            Field[] declaredFields = aClass.getDeclaredFields();
            return Arrays.stream(declaredFields).filter(field ->
                            field.isAnnotationPresent(DecryptedParams.class) && field.getType() == String.class)
                    .collect(Collectors.toSet());
        });
    }
}
