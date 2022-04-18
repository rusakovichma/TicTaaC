package com.github.rusakovichma.tictaac.guesser;

import com.github.rusakovichma.tictaac.model.threatmodel.Element;

import java.util.ArrayList;
import java.util.Collection;

public class UniversalGuesser implements Guesser<Element> {

    private Collection<Guesser<Element>> guessers = new ArrayList<>();

    private void init() {
        guessers.add(new WebserverGuesser());
        guessers.add(new DatabaseGuesser());
        guessers.add(new ProxyServerGuesser());
        guessers.add(new InteractorGuesser());
        guessers.add(new ExternalServiceGuesser());
        guessers.add(new InternalServiceGuesser());
        guessers.add(new ProcessGuesser());
    }

    public UniversalGuesser() {
        init();
    }

    @Override
    public boolean guess(Element element) {
        for (Guesser<Element> guesser : guessers) {
            if (guesser.guess(element)) {
                return true;
            }
        }
        return false;
    }
}
