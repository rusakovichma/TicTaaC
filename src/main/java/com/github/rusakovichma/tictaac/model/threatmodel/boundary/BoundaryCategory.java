package com.github.rusakovichma.tictaac.model.threatmodel.boundary;

public enum BoundaryCategory {
    globalNetwork(3),
    demilitarizedZone(2),
    corporateNetwork(1),
    closedPerimeter(0),
    undefined(-1);

    private final int order;

    BoundaryCategory(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
