package com.github.rusakovichma.tictaac.guesser;

import com.github.rusakovichma.tictaac.model.threatmodel.element.ElementType;

public class WebserverGuesser extends ElementGuesser {

    private final static String[] SIGNS = new String[]{
            "web-server", "webserver", "api", "back-end", "backend",
            "tomcat", "glassfish", "weblogic", "kestrel", "jetty"};

    @Override
    ElementType getGuessedType() {
        return ElementType.webServer;
    }

    @Override
    String[] getDesignatingString() {
        return SIGNS;
    }

}
