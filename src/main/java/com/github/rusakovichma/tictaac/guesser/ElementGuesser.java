package com.github.rusakovichma.tictaac.guesser;

import com.github.rusakovichma.tictaac.model.threatmodel.Element;
import com.github.rusakovichma.tictaac.model.threatmodel.element.ElementType;

import java.util.Arrays;
import java.util.List;

abstract class ElementGuesser implements Guesser<Element> {

    public boolean guess(Element element) {
        if (element.getType() != null || element.getId() == null) {
            return false;
        }

        List<String> elementSigns = Arrays.asList(getDesignatingString());
        for (String elementSign : elementSigns) {
            if (element.getId().toLowerCase().contains(elementSign.toLowerCase())) {
                element.setType(getGuessedType());
                return true;
            }
        }

        return false;
    }

    abstract ElementType getGuessedType();

    abstract String[] getDesignatingString();

}
