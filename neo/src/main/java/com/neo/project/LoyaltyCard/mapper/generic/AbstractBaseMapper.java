package com.neo.project.LoyaltyCard.mapper.generic;

import java.lang.reflect.Field;
import java.util.Arrays;

public abstract class AbstractBaseMapper<T,K> {

    public void insert(T t){
        Class<?> clazz = t.getClass();
        String[] fields = Arrays.stream(clazz.getDeclaredFields())
         .filter(f -> {
            f.setAccessible(true);
            try {
                return f.getBoolean(clazz);
            } catch (IllegalAccessException e) {
                return false;
            }
        })
                .map(Field::getName)
                .toArray(String[]::new);
    }
}
