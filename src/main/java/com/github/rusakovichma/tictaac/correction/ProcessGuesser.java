package com.github.rusakovichma.tictaac.correction;

import com.github.rusakovichma.tictaac.model.threatmodel.element.ElementType;

import java.util.Arrays;
import java.util.List;

public class ProcessGuesser extends ElementGuesser {

    private final static List<String> SIGNS = Arrays.asList(new String[]{
            "process", "desktop-application", "scheduler"});

    @Override
    ElementType getGuessedType() {
        return ElementType.process;
    }

    @Override
    List<String> getDesignatingString() {
        return SIGNS;
    }
}
