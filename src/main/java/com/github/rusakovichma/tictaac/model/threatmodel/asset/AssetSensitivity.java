package com.github.rusakovichma.tictaac.model.threatmodel.asset;

public enum AssetSensitivity {
    sensitive,
    nonSensitive;

    @Override
    public String toString() {
        return this.name();
    }
}
