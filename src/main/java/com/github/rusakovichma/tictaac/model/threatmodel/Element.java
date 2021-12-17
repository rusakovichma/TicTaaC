package com.github.rusakovichma.tictaac.model.threatmodel;

import com.github.rusakovichma.tictaac.model.threatmodel.annotation.Id;
import com.github.rusakovichma.tictaac.model.threatmodel.annotation.Ref;
import com.github.rusakovichma.tictaac.model.threatmodel.element.ElementType;

import java.util.Objects;
import java.util.Set;

public class Element {

    @Id
    private String id;
    private String name;
    private ElementType type;

    @Ref(rootCollection = "assets")
    private Set<Asset> producedAssets;
    @Ref(rootCollection = "assets")
    private Set<Asset> consumedAssets;
    @Ref(rootCollection = "assets")
    private Set<Asset> processedAssets;
    @Ref(rootCollection = "assets")
    private Set<Asset> storedAssets;

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

    public Set<Asset> getProducedAssets() {
        return producedAssets;
    }

    public void setProducedAssets(Set<Asset> producedAssets) {
        this.producedAssets = producedAssets;
    }

    public Set<Asset> getConsumedAssets() {
        return consumedAssets;
    }

    public void setConsumedAssets(Set<Asset> consumedAssets) {
        this.consumedAssets = consumedAssets;
    }

    public Set<Asset> getProcessedAssets() {
        return processedAssets;
    }

    public void setProcessedAssets(Set<Asset> processedAssets) {
        this.processedAssets = processedAssets;
    }

    public Set<Asset> getStoredAssets() {
        return storedAssets;
    }

    public void setStoredAssets(Set<Asset> storedAssets) {
        this.storedAssets = storedAssets;
    }

    public ElementType getType() {
        return type;
    }

    public void setType(ElementType type) {
        this.type = type;
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
