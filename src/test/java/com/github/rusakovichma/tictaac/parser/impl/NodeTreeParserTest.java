package com.github.rusakovichma.tictaac.parser.impl;

import com.github.rusakovichma.tictaac.parser.model.NodeTree;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class NodeTreeParserTest {

    @Test
    void getNodeTreeTest() throws IOException {
        NodeTree tree = new NodeTreeParser().getNodeTree("src/test/resources/taac-model.yml");
        assertTrue(tree.size() == 71);
    }
}