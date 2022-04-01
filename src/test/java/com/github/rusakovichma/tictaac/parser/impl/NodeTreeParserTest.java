package com.github.rusakovichma.tictaac.parser.impl;

import com.github.rusakovichma.tictaac.parser.model.NodeTree;
import com.github.rusakovichma.tictaac.util.FileUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class NodeTreeParserTest {

    @Test
    void getNodeTreeTest() throws IOException {
        InputStream fileInputStream = FileUtil.fileToInputStream("src/test/resources/threat-model-test.yml");

        NodeTree tree = new NodeTreeParser().getNodeTree(fileInputStream);
        assertTrue(tree.size() == 83);
    }

    @Test
    void getNodeTreeWithCommentsTest() throws IOException {
        InputStream fileInputStream = FileUtil.fileToInputStream("src/test/resources/threat-model-test-with-comments.yml");

        NodeTree tree = new NodeTreeParser().getNodeTree(fileInputStream);
        assertTrue(tree.size() == 85);
    }
}