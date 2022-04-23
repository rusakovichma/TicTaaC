package com.github.rusakovichma.tictaac.correction;

import com.github.rusakovichma.tictaac.model.threatmodel.element.ElementType;

import java.util.Arrays;
import java.util.List;

public class WebserverGuesser extends ElementGuesser {

    private final static List<String> SIGNS = Arrays.asList(new String[]{
            "web-server", "webserver", "api", "back-end", "backend",
            "tomcat", "glassfish", "weblogic", "kestrel", "jetty"});

    @Override
    ElementType getGuessedType() {
        return ElementType.webServer;
    }

    @Override
    List<String> getDesignatingString() {
        return SIGNS;
    }

}
