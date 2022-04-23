package com.github.rusakovichma.tictaac.correction;

import com.github.rusakovichma.tictaac.model.threatmodel.element.ElementType;

import java.util.Arrays;
import java.util.List;

public class DatabaseGuesser extends ElementGuesser {

    private final static List<String> SIGNS = Arrays.asList(new String[]{
            "storage", "database", "data-base", "postgre",
            "sql", "oracle", "mongo", "redis", "memcached",});

    @Override
    ElementType getGuessedType() {
        return ElementType.database;
    }

    @Override
    List<String> getDesignatingString() {
        return SIGNS;
    }
}
