package com.github.rusakovichma.tictaac.model;

import com.github.rusakovichma.tictaac.model.threatmodel.Asset;
import com.github.rusakovichma.tictaac.model.threatmodel.Boundary;
import com.github.rusakovichma.tictaac.model.threatmodel.DataFlow;
import com.github.rusakovichma.tictaac.model.threatmodel.Element;
import com.github.rusakovichma.tictaac.model.threatmodel.annotation.RootCollection;

import java.util.LinkedList;
import java.util.List;

public class ThreatModel {

    @RootCollection
    private LinkedList<Asset> assets;
    @RootCollection
    private LinkedList<Element> elements;
    @RootCollection
    private LinkedList<Boundary> boundaries;
    @RootCollection
    private LinkedList<DataFlow> dataFlows;

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
