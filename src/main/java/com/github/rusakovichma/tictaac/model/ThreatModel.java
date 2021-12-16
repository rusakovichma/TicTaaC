package com.github.rusakovichma.tictaac.model;

import com.github.rusakovichma.tictaac.model.threatmodel.Asset;
import com.github.rusakovichma.tictaac.model.threatmodel.Boundary;
import com.github.rusakovichma.tictaac.model.threatmodel.DataFlow;
import com.github.rusakovichma.tictaac.model.threatmodel.Element;

import java.util.List;

public class ThreatModel {

    private List<Asset> assets;
    private List<Element> elements;
    private List<Boundary> boundaries;
    private List<DataFlow> dataFlows;

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    public List<Boundary> getBoundaries() {
        return boundaries;
    }

    public void setBoundaries(List<Boundary> boundaries) {
        this.boundaries = boundaries;
    }

    public List<DataFlow> getDataFlows() {
        return dataFlows;
    }

    public void setDataFlows(List<DataFlow> dataFlows) {
        this.dataFlows = dataFlows;
    }
}
