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
