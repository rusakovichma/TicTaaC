package com.github.rusakovichma.tictaac.guesser;

import com.github.rusakovichma.tictaac.model.threatmodel.element.ElementType;

public class InteractorGuesser extends ElementGuesser {

    private final static String[] SIGNS = new String[]{
            "user", "human", "employee", "actor", "attacker",
            "threat-agent", "threatagent", "browser", "hacker"};

    @Override
    ElementType getGuessedType() {
        return ElementType.interactor;
    }

    @Override
    String[] getDesignatingString() {
        return SIGNS;
    }

}
