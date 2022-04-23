package com.github.rusakovichma.tictaac.correction;

import com.github.rusakovichma.tictaac.model.threatmodel.Element;
import com.github.rusakovichma.tictaac.model.threatmodel.element.ElementType;

import java.util.List;

abstract class ElementGuesser implements Guesser<Element> {

    public boolean tryToCorrect(Element element) {
        if (element.getType() != null || element.getId() == null) {
            return false;
        }
        for (String elementSign : getDesignatingString()) {
            if (element.getId().toLowerCase().contains(elementSign.toLowerCase())) {
                element.setType(getGuessedType());
                return true;
            }
        }

        return false;
    }

    abstract ElementType getGuessedType();

    abstract List<String> getDesignatingString();

}
