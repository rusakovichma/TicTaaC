package com.github.rusakovichma.tictaac.correction;

import com.github.rusakovichma.tictaac.model.threatmodel.Element;
import com.github.rusakovichma.tictaac.model.threatmodel.element.ElementType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UniversalElementGuesserTest {

    @Test
    void guess() {
        Element element = new Element();
        element.setId("some-gateway-to-guess");

        UniversalElementGuesser guesser = new UniversalElementGuesser();
        guesser.tryToCorrect(element);

        assertTrue(element.getType() == ElementType.proxyServer);
    }

    @Test
    void guessNull() {
        Element element = new Element();

        UniversalElementGuesser guesser = new UniversalElementGuesser();
        guesser.tryToCorrect(element);

        assertTrue(element.getType() == null);
    }
}