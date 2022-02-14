package com.github.rusakovichma.tictaac.model.threatmodel;

import com.github.rusakovichma.tictaac.model.threatmodel.annotation.DefaultValue;
import com.github.rusakovichma.tictaac.model.threatmodel.annotation.Id;
import com.github.rusakovichma.tictaac.model.threatmodel.annotation.Ref;
import com.github.rusakovichma.tictaac.model.threatmodel.element.ElementType;

import java.util.LinkedList;
import java.util.Objects;

public class Element {

    @Id
    private String id;
    private String name;
    private ElementType type;
    @DefaultValue("true")
    private Boolean inScope;

    @Ref(rootCollection = "assets")
    private LinkedList<Asset> producedAssets;
    @Ref(rootCollection = "assets")
    private LinkedList<Asset> consumedAssets;
    @Ref(rootCollection = "assets")
    private LinkedList<Asset> processedAssets;
    @Ref(rootCollection = "assets")
    private LinkedList<Asset> storedAssets;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ElementType getType() {
        return type;
    }

    public void setType(ElementType type) {
        this.type = type;
    }

    public LinkedList<Asset> getProducedAssets() {
        return producedAssets;
    }

    public void setProducedAssets(LinkedList<Asset> producedAssets) {
        this.producedAssets = producedAssets;
    }

    public LinkedList<Asset> getConsumedAssets() {
        return consumedAssets;
    }

    public void setConsumedAssets(LinkedList<Asset> consumedAssets) {
        this.consumedAssets = consumedAssets;
    }

    public LinkedList<Asset> getProcessedAssets() {
        return processedAssets;
    }

    public void setProcessedAssets(LinkedList<Asset> processedAssets) {
        this.processedAssets = processedAssets;
    }

    public LinkedList<Asset> getStoredAssets() {
        return storedAssets;
    }

    public void setStoredAssets(LinkedList<Asset> storedAssets) {
        this.storedAssets = storedAssets;
    }

    public Boolean getInScope() {
        return inScope;
    }

    public void setInScope(Boolean inScope) {
        this.inScope = inScope;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return Objects.equals(id, element.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
