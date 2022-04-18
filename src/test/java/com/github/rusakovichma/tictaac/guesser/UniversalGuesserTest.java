package com.github.rusakovichma.tictaac.guesser;

import com.github.rusakovichma.tictaac.model.threatmodel.Element;
import com.github.rusakovichma.tictaac.model.threatmodel.element.ElementType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UniversalGuesserTest {

    @Test
    void guess() {
        Element element = new Element();
        element.setId("some-gateway-to-guess");

        UniversalGuesser guesser = new UniversalGuesser();
        guesser.guess(element);

        assertTrue(element.getType() == ElementType.proxyServer);
    }

    @Test
    void guessNull() {
        Element element = new Element();

        UniversalGuesser guesser = new UniversalGuesser();
        guesser.guess(element);

        assertTrue(element.getType() == null);
    }
}