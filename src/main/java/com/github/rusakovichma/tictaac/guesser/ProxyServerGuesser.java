package com.github.rusakovichma.tictaac.guesser;

import com.github.rusakovichma.tictaac.model.threatmodel.element.ElementType;

public class ProxyServerGuesser extends ElementGuesser {

    private final static String[] SIGNS = new String[]{
            "proxyserver", "proxy-server", "load-balancer", "balancer",
            "nginx", "gateway", "apache-http"};

    @Override
    ElementType getGuessedType() {
        return ElementType.proxyServer;
    }

    @Override
    String[] getDesignatingString() {
        return SIGNS;
    }
}
