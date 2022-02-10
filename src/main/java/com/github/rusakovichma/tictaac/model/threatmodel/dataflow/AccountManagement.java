package com.github.rusakovichma.tictaac.model.threatmodel.dataflow;

public enum AccountManagement {
    externalSso,
    localAccount,
    activeDirectory;

    @Override
    public String toString() {
        return this.name();
    }
}
