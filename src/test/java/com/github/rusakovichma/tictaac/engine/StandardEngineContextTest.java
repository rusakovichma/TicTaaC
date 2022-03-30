package com.github.rusakovichma.tictaac.engine;

import com.github.rusakovichma.tictaac.mapper.ThreatModelMapper;
import com.github.rusakovichma.tictaac.mapper.ThreatsLibraryMapper;
import com.github.rusakovichma.tictaac.model.*;
import com.github.rusakovichma.tictaac.parser.impl.NodeTreeParser;
import com.github.rusakovichma.tictaac.parser.model.NodeTree;
import com.github.rusakovichma.tictaac.util.FileUtil;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
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

        List<Threat> threats = (List<Threat>) engineContext.eval(model, rules);
        assertTrue(threats.size() == 2);

        assertTrue(threats.get(0).getId().equals("7eefda5b73808a488729397937fbaf552f380887"));
        assertTrue(threats.get(0).getRisk() == ThreatRisk.High);
        assertTrue(threats.get(0).getDescription().equals("threat description 2 for Users Request with source of User and target - Proxy Server"));

        assertTrue(threats.get(1).getId().equals("54d019c662a3baa2e9803cc0e1c05147bc565286"));
        assertTrue(threats.get(1).getRisk() == ThreatRisk.Low);
        assertTrue(threats.get(1).getDescription().equals("threat description 1 for PostgreSQL"));
    }
}