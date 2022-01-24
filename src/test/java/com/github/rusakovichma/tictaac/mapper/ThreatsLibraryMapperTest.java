package com.github.rusakovichma.tictaac.mapper;

import com.github.rusakovichma.tictaac.model.ThreatCategory;
import com.github.rusakovichma.tictaac.model.ThreatSeverity;
import com.github.rusakovichma.tictaac.model.ThreatsLibrary;
import com.github.rusakovichma.tictaac.parser.impl.NodeTreeParser;
import com.github.rusakovichma.tictaac.parser.model.NodeTree;
import com.github.rusakovichma.tictaac.util.FileUtil;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class ThreatsLibraryMapperTest {

    @Test
    void getModel() throws Exception {
        InputStream fileInputStream = FileUtil.fileToInputStream("src/test/resources/threats-provider-test.yml");

        NodeTree tree = new NodeTreeParser().getNodeTree(fileInputStream);
        ThreatsLibraryMapper mapper = new ThreatsLibraryMapper(tree);

        ThreatsLibrary threatsLibrary = mapper.getModel();
        assertTrue(threatsLibrary.getName().equals("Threats rules set for test"));
        assertTrue(threatsLibrary.getVersion().equals("0.1.1"));

        assertTrue(threatsLibrary.getRules().size() == 2);
        assertTrue(threatsLibrary.getRules().get(0).getSeverity() == ThreatSeverity.High);
        assertTrue(threatsLibrary.getRules().get(1).getCategories().toArray()[0] == ThreatCategory.informationDisclosure);

    }

    @Test
    void checkOmitComments() throws Exception {
        InputStream fileInputStream = FileUtil.fileToInputStream("src/test/resources/threats-provider-test.yml");

        NodeTree tree = new NodeTreeParser().getNodeTree(fileInputStream);
        ThreatsLibraryMapper mapper = new ThreatsLibraryMapper(tree);

        ThreatsLibrary threatsLibrary = mapper.getModel();
        assertTrue(!threatsLibrary.getRules().get(0).getTitle().contains("# some comment 1"));
    }


}