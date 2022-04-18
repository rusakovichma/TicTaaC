package com.github.rusakovichma.tictaac.guesser;

import com.github.rusakovichma.tictaac.model.threatmodel.element.ElementType;

public class DatabaseGuesser extends ElementGuesser {

    private final static String[] SIGNS = new String[]{
            "storage", "database", "data-base", "postgre",
            "sql", "oracle", "mongo", "redis", "memcached",};

    @Override
    ElementType getGuessedType() {
        return ElementType.database;
    }

    @Override
    String[] getDesignatingString() {
        return SIGNS;
    }
}
