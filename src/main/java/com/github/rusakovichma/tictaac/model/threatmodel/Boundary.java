package com.github.rusakovichma.tictaac.model.threatmodel;

import com.github.rusakovichma.tictaac.model.threatmodel.annotation.Id;
import com.github.rusakovichma.tictaac.model.threatmodel.annotation.Ref;

import java.util.LinkedList;
import java.util.Objects;

public class Boundary {

    @Id
    private String id;
    @Ref(rootCollection = "elements")
    private LinkedList<Element> elements;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
