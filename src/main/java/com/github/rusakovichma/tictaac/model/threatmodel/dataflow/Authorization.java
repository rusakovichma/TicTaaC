package com.github.rusakovichma.tictaac.model.threatmodel.dataflow;

public enum Authorization {
    read,
    readWrite,
    admin;

    @Override
    public String toString() {
        return this.name();
    }
}
