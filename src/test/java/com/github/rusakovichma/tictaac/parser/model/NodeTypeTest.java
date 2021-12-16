package com.github.rusakovichma.tictaac.parser.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTypeTest {

    @Test
    void getTypeTest() {
        assertTrue(NodeType.getType("\t- personal-data") == NodeType.element);
        assertTrue(NodeType.getType("\t-personal-data") == NodeType.element);

        assertTrue(NodeType.getType("\t- proxy-server -> web-server") == NodeType.flow);
        assertTrue(NodeType.getType("\t-proxy-server->web-server") == NodeType.flow);

        assertTrue(NodeType.getType("\t\taccount-management: external-sso") == NodeType.property);
        assertTrue(NodeType.getType("\t\taccount-management:external-sso") == NodeType.property);

        assertTrue(NodeType.getType("  dfgsd s dfgs dfgs") == NodeType.unknown);
        assertTrue(NodeType.getType(null) == NodeType.unknown);
    }
}