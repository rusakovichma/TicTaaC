package com.github.rusakovichma.tictaac.provider;

import com.github.rusakovichma.tictaac.mapper.ThreatsLibraryMapper;
import com.github.rusakovichma.tictaac.model.ThreatsLibrary;
import com.github.rusakovichma.tictaac.parser.impl.NodeTreeParser;
import com.github.rusakovichma.tictaac.parser.model.NodeTree;

import java.io.IOException;
import java.io.InputStream;

public class ClassPathFileThreatProvider implements ThreatProvider {

    private final String classPathFilePath;

    public ClassPathFileThreatProvider(String classPathFilePath) {
        this.classPathFilePath = classPathFilePath;
    }

    @Override
    public ThreatsLibrary getThreatsLibrary() {
        try {
            InputStream inputStream = ClassLoader.class.getResourceAsStream(classPathFilePath);
            NodeTree tree = new NodeTreeParser().getNodeTree(inputStream);
            return new ThreatsLibraryMapper(tree).getModel();
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Cannot init classpath file provider [" + classPathFilePath + "]", ex);
        }
    }
}
