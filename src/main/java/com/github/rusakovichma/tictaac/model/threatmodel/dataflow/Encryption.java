package com.github.rusakovichma.tictaac.model.threatmodel.dataflow;

public enum Encryption {
    no,
    yes;

    @Override
    public String toString() {
        return this.name();
    }
}
