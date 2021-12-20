package com.github.rusakovichma.tictaac.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ReflectionUtil {

    public static Field findFieldWithAnnotation(Class<?> aClass, Class<? extends Annotation> ann) {
        Class<?> c = aClass;
        while (c != null) {
            for (Field field : c.getDeclaredFields()) {
                if (field.isAnnotationPresent(ann)) {
                    return field;
                }
            }
            c = c.getSuperclass();
        }
        return null;
    }

    public static Set<Field> findFieldsWithAnnotation(Class<?> aClass, Class<? extends Annotation> ann) {
        Set<Field> set = new HashSet<>();
        Class<?> c = aClass;
        while (c != null) {
            for (Field field : c.getDeclaredFields()) {
                if (field.isAnnotationPresent(ann)) {
                    set.add(field);
                }
            }
            c = c.getSuperclass();
        }
        return set;
    }

    public static <T extends Enum<T>> T createEnumInstance(String name, Type type) {
        return Enum.valueOf((Class<T>) type, name);
    }

    public static Type getCollectionParameterType(Field collectionField) {
        if (Collection.class.isAssignableFrom(collectionField.getType())) {

            Type collectionGenericType = collectionField.getGenericType();
            if (collectionGenericType instanceof ParameterizedType) {
                ParameterizedType pt = (ParameterizedType) collectionGenericType;
                return pt.getActualTypeArguments()[0];
            }
        }

        throw new IllegalStateException("Cannot get collection parameter type");
    }


    public static Method getCollectionAddMethod(Object collectionObject)
            throws NoSuchMethodException {
        return collectionObject.getClass().getDeclaredMethod("add", Object.class);
    }
}
