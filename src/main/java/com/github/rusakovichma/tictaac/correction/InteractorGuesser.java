package com.github.rusakovichma.tictaac.correction;

import com.github.rusakovichma.tictaac.model.threatmodel.element.ElementType;

import java.util.Arrays;
import java.util.List;

public class InteractorGuesser extends ElementGuesser {

    private final static List<String> SIGNS = Arrays.asList(new String[]{
            "user", "human", "employee", "actor", "attacker",
            "threat-agent", "threatagent", "browser", "hacker"});

    @Override
    ElementType getGuessedType() {
        return ElementType.interactor;
    }

    @Override
    List<String> getDesignatingString() {
        return SIGNS;
    }

}
