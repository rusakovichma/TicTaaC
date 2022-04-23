package com.github.rusakovichma.tictaac.correction;

import com.github.rusakovichma.tictaac.model.threatmodel.Element;

public class ElementNameCorrector implements Corrector<Element> {

    @Override
    public boolean tryToCorrect(Element element) {
        if (element.getName() == null || element.getName().trim().isEmpty()) {
            element.setName(element.getId());
            return true;
        }
        return false;
    }
}
