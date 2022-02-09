package com.github.rusakovichma.tictaac.model.threatmodel.boundary;

public enum Category {
    globalNetwork,
    demilitarizedZone,
    corporateNetwork,
    closedPerimeter;

    @Override
    public String toString() {
        return this.name();
    }
}
