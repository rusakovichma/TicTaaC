package com.github.rusakovichma.tictaac.model.threatmodel.element;

public enum ElementType {
    interactor,
    proxyServer,
    webServer,
    database,
    externalService,
    internalService,
    process;

    @Override
    public String toString() {
        return this.name();
    }
}
