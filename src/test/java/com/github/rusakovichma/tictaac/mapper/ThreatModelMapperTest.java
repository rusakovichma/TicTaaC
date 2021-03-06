package com.github.rusakovichma.tictaac.mapper;

import com.github.rusakovichma.tictaac.model.ThreatModel;
import com.github.rusakovichma.tictaac.parser.impl.NodeTreeParser;
import com.github.rusakovichma.tictaac.parser.model.NodeTree;
import com.github.rusakovichma.tictaac.util.FileUtil;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class ThreatModelMapperTest {

    @Test
    void getModel() throws Exception {
        InputStream fileInputStream = FileUtil.fileToInputStream("src/test/resources/threat-model-test.yml");

        NodeTree tree = new NodeTreeParser().getNodeTree(fileInputStream);
        ThreatModelMapper mapper = new ThreatModelMapper(tree);

        ThreatModel threatModel = mapper.getModel();

        assertTrue("Threat Model Name".equals(threatModel.getName()));
        assertTrue("1.0.1".equals(threatModel.getVersion()));

        assertTrue(threatModel.getAssets().size() == 3);
        assertTrue(threatModel.getElements().size() == 4);
        assertTrue(threatModel.getBoundaries().size() == 3);
        assertTrue(threatModel.getDataFlows().size() == 3);
    }

    @Test
    void getModelWithComments() throws Exception {
        InputStream fileInputStream = FileUtil.fileToInputStream("src/test/resources/threat-model-test-with-comments.yml");

        NodeTree tree = new NodeTreeParser().getNodeTree(fileInputStream);
        ThreatModelMapper mapper = new ThreatModelMapper(tree);

        ThreatModel threatModel = mapper.getModel();

        assertTrue("Threat Model Name".equals(threatModel.getName()));
        assertTrue("1.0.1".equals(threatModel.getVersion()));

        assertTrue(threatModel.getAssets().size() == 3);
        assertTrue(threatModel.getElements().size() == 4);
        assertFalse(threatModel.getElements().get(2).getInScope());

        assertTrue(threatModel.getBoundaries().size() == 3);
        assertTrue(threatModel.getDataFlows().size() == 3);
        assertFalse(threatModel.getDataFlows().get(2).getInScope());

        assertTrue(!threatModel.getElements().get(1).getName().contains("# comment 2"));
    }
}