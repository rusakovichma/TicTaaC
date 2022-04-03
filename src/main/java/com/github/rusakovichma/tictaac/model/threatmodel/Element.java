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
