package com.github.rusakovichma.tictaac.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ClassUtil {

    private static final Set<Class<?>> WRAPPER_TYPES = getWrapperTypes();

    private ClassUtil() {
    }

    public static boolean isCollection(Class cls) {
        return Collection.class.isAssignableFrom(cls);
    }

    public static boolean isWrapperType(Class<?> clazz) {
        return WRAPPER_TYPES.contains(clazz);
    }

    private static Set<Class<?>> getWrapperTypes() {
        Set<Class<?>> ret = new HashSet<Class<?>>();
        ret.add(Boolean.class);
        ret.add(Character.class);
        ret.add(Byte.class);
        ret.add(Short.class);
        ret.add(Integer.class);
        ret.add(Long.class);
        ret.add(Float.class);
        ret.add(Double.class);
        ret.add(Void.class);
        return ret;
    }

    public static boolean isPlainType(Class cls) {
        return cls.isPrimitive() || isWrapperType(cls) || cls.isAssignableFrom(String.class);
    }

}
