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
package com.github.rusakovichma.tictaac.model;

import com.github.rusakovichma.tictaac.model.threatmodel.Asset;
import com.github.rusakovichma.tictaac.model.threatmodel.Boundary;
import com.github.rusakovichma.tictaac.model.threatmodel.DataFlow;
import com.github.rusakovichma.tictaac.model.threatmodel.Element;
import com.github.rusakovichma.tictaac.model.threatmodel.annotation.RootCollection;
import com.github.rusakovichma.tictaac.validation.RequiresAtLeast;

import java.util.LinkedList;
import java.util.List;

public class ThreatModel {

    private String name;
    private String version;

    @RootCollection
    private LinkedList<Asset> assets;
    @RootCollection
    @RequiresAtLeast(elements = 2)
    private LinkedList<Element> elements;
    @RootCollection
    private LinkedList<Boundary> boundaries;
    @RootCollection
    @RequiresAtLeast(elements = 1)
    private LinkedList<DataFlow> dataFlows;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(LinkedList<Asset> assets) {
        this.assets = assets;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(LinkedList<Element> elements) {
        this.elements = elements;
    }

    public List<Boundary> getBoundaries() {
        return boundaries;
    }

    public void setBoundaries(LinkedList<Boundary> boundaries) {
        this.boundaries = boundaries;
    }

    public List<DataFlow> getDataFlows() {
        return dataFlows;
    }

    public void setDataFlows(LinkedList<DataFlow> dataFlows) {
        this.dataFlows = dataFlows;
    }
}
