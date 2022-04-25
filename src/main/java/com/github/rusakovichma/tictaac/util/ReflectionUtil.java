/*
 * This file is part of TicTaaC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) 2022 Mikhail Rusakovich. All Rights Reserved.
 */
package com.github.rusakovichma.tictaac.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

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

    public static Field findFieldWithName(Class<?> aClass, String name) {
        Class<?> c = aClass;
        while (c != null) {
            for (Field field : c.getDeclaredFields()) {
                if (field.getName().equalsIgnoreCase(name)) {
                    return field;
                }
            }
            c = c.getSuperclass();
        }
        return null;
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

    public static Map<String, Object> getFields(Object object, String prefix) {
        try {
            Class<?> c = object.getClass();
            Map<String, Object> fieldsWithContext = new HashMap<>();
            Collection<Field> fields = Arrays.asList(c.getDeclaredFields());
            for (Field field : fields) {
                field.setAccessible(true);
                fieldsWithContext.put(
                        String.format("%s.%s", prefix, field.getName()),
                        field.get(object)
                );
            }
            return fieldsWithContext;
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }
}
