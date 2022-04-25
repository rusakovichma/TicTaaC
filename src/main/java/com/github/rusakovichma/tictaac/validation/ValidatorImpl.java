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
package com.github.rusakovichma.tictaac.validation;

import com.github.rusakovichma.tictaac.model.threatmodel.annotation.Id;
import com.github.rusakovichma.tictaac.model.threatmodel.annotation.Ref;
import com.github.rusakovichma.tictaac.util.ClassUtil;
import com.github.rusakovichma.tictaac.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collection;

public class ValidatorImpl implements Validator {

    private String getObjectId(Object object)
            throws IllegalArgumentException, IllegalAccessException {
        if (object == null) {
            return "null";
        }

        Field idField = ReflectionUtil.findFieldWithAnnotation(object.getClass(), Id.class);
        if (idField == null) {
            idField = ReflectionUtil.findFieldWithName(object.getClass(), "title");
        }

        if (idField != null) {
            idField.setAccessible(true);
            Object idFieldValue = idField.get(object);
            if (idFieldValue != null) {
                return String.valueOf(idFieldValue);
            }
        }

        return object.toString();
    }

    private void validateObject(Object object, ValidationErrors errors)
            throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
        if (object == null) {
            return;
        }

        String objectId = getObjectId(object);

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            Object fieldOfObject = field.get(object);

            if (Collection.class.isAssignableFrom(field.getType())) {
                validateCollection(field, object, errors, objectId);
            } else {
                if (fieldOfObject == null) {
                    if (field.isAnnotationPresent(Required.class)) {
                        errors.getErrors().add(String.format("Field '%s' of the element '%s' should be defined",
                                field.getName(), objectId));
                    }
                }
            }
        }
    }


    private void validateCollection(Field collectionField, Object object, ValidationErrors errors, String context)
            throws IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
        Object collectionObject = collectionField.get(object);
        if (collectionObject == null) {
            if (collectionField.isAnnotationPresent(Required.class)
                    || collectionField.isAnnotationPresent(RequiresAtLeast.class)) {
                if (context == null) {
                    errors.getErrors().add(String.format("Collection '%s' should not be empty",
                            collectionField.getName()));
                } else {
                    errors.getErrors().add(String.format("Collection '%s' of the element '%s' should not be empty",
                            collectionField.getName(), context));
                }
            }
            return;
        }

        Collection collection = (Collection) collectionObject;
        if (collectionField.isAnnotationPresent(RequiresAtLeast.class)) {
            RequiresAtLeast annotation = collectionField.getAnnotation(RequiresAtLeast.class);
            if (annotation.elements() != -1) {
                if (collection.size() < annotation.elements()) {
                    if (context == null) {
                        errors.getErrors().add(String.format("Collection '%s' requires at least '%d' elements",
                                collectionField.getName(), annotation.elements()));
                    } else {
                        errors.getErrors().add(String.format("Collection '%s' of the element '%s' requires at least '%d' elements",
                                collectionField.getName(), annotation.elements(), context));
                    }
                }
            }
        }

        if (collectionField.isAnnotationPresent(Ref.class)) {
            return;
        }

        Type parameterType = ReflectionUtil.getCollectionParameterType(collectionField);
        if (!ClassUtil.isPlainType(Class.forName(parameterType.getTypeName()))) {
            for (Object objInCollection : collection) {
                validateObject(objInCollection, errors);
            }
        }
    }


    @Override
    public void validate(Object object) {
        if (object == null) {
            return;
        }
        ValidationErrors errors = new ValidationErrors();

        Class<?> objectClass = object.getClass();
        try {
            for (Field field : objectClass.getDeclaredFields()) {
                field.setAccessible(true);
                if (Collection.class.isAssignableFrom(field.getType())) {
                    validateCollection(field, object, errors, null);
                }
            }
        } catch (IllegalAccessException | IllegalArgumentException | ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }

        if (!errors.getErrors().isEmpty()) {
            throw new ValidationException(errors);
        }
    }

}
