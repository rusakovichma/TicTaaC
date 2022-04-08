package com.github.rusakovichma.tictaac.engine;

import com.github.rusakovichma.tictaac.mapper.ThreatModelMapper;
import com.github.rusakovichma.tictaac.mapper.ThreatsLibraryMapper;
import com.github.rusakovichma.tictaac.model.*;
import com.github.rusakovichma.tictaac.parser.impl.NodeTreeParser;
import com.github.rusakovichma.tictaac.parser.model.NodeTree;
import com.github.rusakovichma.tictaac.util.FileUtil;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StandardEngineContextTest {

    private ThreatModel getModel() throws Exception {
        InputStream fileInputStream = FileUtil.fileToInputStream(
                "src/test/resources/threat-model-engine-context-test.yml");

        NodeTree tree = new NodeTreeParser().getNodeTree(fileInputStream);
        ThreatModelMapper mapper = new ThreatModelMapper(tree);

        return mapper.getModel();
    }

    private LinkedList<ThreatRule> getRules() throws Exception {
        InputStream fileInputStream = FileUtil.fileToInputStream(
                "src/test/resources/threats-provider-engine-context-test.yml");

        NodeTree tree = new NodeTreeParser().getNodeTree(fileInputStream);
        ThreatsLibraryMapper mapper = new ThreatsLibraryMapper(tree);

        return mapper.getModel().getRules();
    }

    @Test
    void eval() throws Exception {
        ThreatModel model = getModel();
        LinkedList<ThreatRule> rules = getRules();

        StandardEngineContext engineContext = new StandardEngineContext();

        ArrayList<Threat> threats = new ArrayList<>(engineContext.eval(model, rules));
        assertTrue(threats.size() == 2);

        assertTrue(threats.get(0).getId().equals("e1a127e52365395e8c13388ef9ec3c89b5605280"));
        assertTrue(threats.get(0).getRisk() == ThreatRisk.High);
        assertTrue(threats.get(0).getDescription().equals("threat description 2 for Users Request with source of User and target - Proxy Server"));

        assertTrue(threats.get(1).getId().equals("bb83b0d3eb7b4cc8a344e4d552b017c59703930d"));
        assertTrue(threats.get(1).getRisk() == ThreatRisk.Low);
        assertTrue(threats.get(1).getDescription().equals("threat description 1 for PostgreSQL"));
    }
}