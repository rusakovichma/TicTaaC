package com.github.rusakovichma.tictaac.reporter.dfd;

import com.github.rusakovichma.tictaac.mapper.ThreatModelMapper;
import com.github.rusakovichma.tictaac.model.ThreatModel;
import com.github.rusakovichma.tictaac.parser.impl.NodeTreeParser;
import com.github.rusakovichma.tictaac.parser.model.NodeTree;
import com.github.rusakovichma.tictaac.util.FileUtil;
import com.github.rusakovichma.tictaac.util.ImageUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class GraphvizDataFlowRenderTest {

    @Test
    void createDataFlow() throws IOException {
        InputStream fileInputStream = FileUtil.fileToInputStream("src/test/resources/data-flow-render-test.yml");

        NodeTree tree = new NodeTreeParser().getNodeTree(fileInputStream);
        ThreatModelMapper mapper = new ThreatModelMapper(tree);

        ThreatModel threatModel = mapper.getModel();

        GraphvizDataFlowRender render = new GraphvizDataFlowRender();

        assertTrue(render.createDataFlow(threatModel).length > 0);
    }
}