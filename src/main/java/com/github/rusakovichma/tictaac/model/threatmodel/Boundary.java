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
package com.github.rusakovichma.tictaac.model.threatmodel;

import com.github.rusakovichma.tictaac.model.threatmodel.annotation.Id;
import com.github.rusakovichma.tictaac.model.threatmodel.annotation.Ref;
import com.github.rusakovichma.tictaac.model.threatmodel.boundary.BoundaryCategory;

import java.util.LinkedList;
import java.util.Objects;

public class Boundary {

    @Id
    private String id;
    private BoundaryCategory category;
    @Ref(rootCollection = "elements")
    private LinkedList<Element> elements;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BoundaryCategory getCategory() {
        return category;
    }

    public int getCategoryOrder() {
        return category.getOrder();
    }

    public void setCategory(BoundaryCategory category) {
        this.category = category;
    }

    public LinkedList<Element> getElements() {
        return elements;
    }

    public void setElements(LinkedList<Element> elements) {
        this.elements = elements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Boundary boundary = (Boundary) o;
        return Objects.equals(id, boundary.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
