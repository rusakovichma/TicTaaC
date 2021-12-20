package com.github.rusakovichma.tictaac.parser.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeHelperTest {

    @Test
    void getFlowSource() {
        String source = NodeHelper.getFlowEntity("- interactor -> proxy-server", true);
        assertTrue(source.equals("interactor"));
    }

    @Test
    void getFlowTarget() {
        String source = NodeHelper.getFlowEntity("- interactor -> proxy-server", false);
        assertTrue(source.equals("proxy-server"));
    }
}