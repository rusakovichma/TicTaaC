package com.github.rusakovichma.tictaac.correction;

import com.github.rusakovichma.tictaac.model.threatmodel.element.ElementType;

import java.util.Arrays;
import java.util.List;

public class ProxyServerGuesser extends ElementGuesser {

    private final static List<String> SIGNS = Arrays.asList(new String[]{
            "proxyserver", "proxy-server", "load-balancer", "balancer",
            "nginx", "gateway", "apache-http"});

    @Override
    ElementType getGuessedType() {
        return ElementType.proxyServer;
    }

    @Override
    List<String> getDesignatingString() {
        return SIGNS;
    }
}
