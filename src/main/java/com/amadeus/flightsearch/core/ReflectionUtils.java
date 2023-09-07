package com.amadeus.flightsearch.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.amadeus.flightsearch.exceptions.NoFoundNoArgumentConstructor;

public class ReflectionUtils {
 
    public static Constructor<?> findNoArgumentConstructor(Class<?> clazz) {
        Constructor<?> noArgumentConstructor;
        try {
            noArgumentConstructor = clazz.getDeclaredConstructor((Class<?>[]) null);
        } catch (NoSuchMethodException e) {
            throw new NoFoundNoArgumentConstructor(clazz.getSimpleName() + ".class için parametresiz yapıcı metot bulunamadı");
        }
        return noArgumentConstructor;
    }

    public static <FROM, TO> TO cast(FROM from, Class<TO> to) {
        try {
            TO pwm = (TO) findNoArgumentConstructor(to).newInstance();
            List<Field> toFieldList = new ArrayList<>(Arrays.asList(pwm.getClass().getDeclaredFields()));
            Class<?> superClassOfTo = pwm.getClass().getSuperclass();
            if(superClassOfTo != Object.class && superClassOfTo != null)
                toFieldList.addAll(Arrays.asList(superClassOfTo.getDeclaredFields()));
            for (Field toField : toFieldList) {
                for (Field fromField : from.getClass().getDeclaredFields()) {
                    if (!toField.getName().equals(fromField.getName())) continue;
                    toField.trySetAccessible();
                    fromField.trySetAccessible();
                    toField.set(pwm, fromField.get(from));
                    break;
                }
            }
            return pwm;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
