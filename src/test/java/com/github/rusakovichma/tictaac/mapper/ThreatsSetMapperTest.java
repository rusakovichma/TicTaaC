package com.github.rusakovichma.tictaac.mapper;

import com.github.rusakovichma.tictaac.model.ThreatCategory;
import com.github.rusakovichma.tictaac.model.ThreatSeverity;
import com.github.rusakovichma.tictaac.model.ThreatsSet;
import com.github.rusakovichma.tictaac.parser.impl.NodeTreeParser;
import com.github.rusakovichma.tictaac.parser.model.NodeTree;
import com.github.rusakovichma.tictaac.util.FileUtil;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class ThreatsSetMapperTest {

    @Test
    void getModel() throws Exception {
        InputStream fileInputStream = FileUtil.fileToInputStream("src/test/resources/threats-provider-test.yml");

        NodeTree tree = new NodeTreeParser().getNodeTree(fileInputStream);
        ThreatsSetMapper mapper = new ThreatsSetMapper(tree);

        ThreatsSet threatsSet = mapper.getModel();
        assertTrue(threatsSet.getThreats().size() == 2);
        assertTrue(threatsSet.getThreats().get(0).getSeverity() == ThreatSeverity.High);
        assertTrue(threatsSet.getThreats().get(1).getCategories().toArray()[0] == ThreatCategory.informationDisclosure);

    }


}