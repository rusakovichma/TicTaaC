package com.github.rusakovichma.tictaac.parser.impl;

import com.github.rusakovichma.tictaac.parser.model.NodeTree;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ParserImplTest {

    @Test
    void getNodeTreeTest() throws IOException {
        NodeTree tree = new ParserImpl().getNodeTree("src/test/resources/taac-model.yml");
        assertTrue(tree.size() == 71);
    }
}