package com.github.rusakovichma.tictaac.util;

import java.util.Collection;

public class ClassUtil {

    private ClassUtil() {
    }

    public static boolean isCollection(Class cls) {
        return Collection.class.isAssignableFrom(cls);
    }

}
