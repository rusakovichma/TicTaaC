package com.github.rusakovichma.tictaac.guesser;

import com.github.rusakovichma.tictaac.model.threatmodel.element.ElementType;

public class ProcessGuesser extends ElementGuesser {

    private final static String[] SIGNS = new String[]{
            "process", "desktop-application", "scheduler"};

    @Override
    ElementType getGuessedType() {
        return ElementType.process;
    }

    @Override
    String[] getDesignatingString() {
        return SIGNS;
    }
}
