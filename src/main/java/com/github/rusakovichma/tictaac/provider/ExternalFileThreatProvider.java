package com.github.rusakovichma.tictaac.provider;

import com.github.rusakovichma.tictaac.mapper.ThreatsLibraryMapper;
import com.github.rusakovichma.tictaac.model.ThreatsLibrary;
import com.github.rusakovichma.tictaac.parser.impl.NodeTreeParser;
import com.github.rusakovichma.tictaac.parser.model.NodeTree;
import com.github.rusakovichma.tictaac.util.FileUtil;

import java.io.IOException;

public class ExternalFileThreatProvider implements ThreatProvider {

    private final String externalFilePath;

    public ExternalFileThreatProvider(String externalFilePath) {
        this.externalFilePath = externalFilePath;
    }

    @Override
    public ThreatsLibrary getThreatsLibrary() {
        try {
            NodeTree tree = new NodeTreeParser().getNodeTree(
                    FileUtil.fileToInputStream(externalFilePath));
            return new ThreatsLibraryMapper(tree).getModel();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Cannot init external file provider [" + externalFilePath + "]", ex);
        }
    }
}
