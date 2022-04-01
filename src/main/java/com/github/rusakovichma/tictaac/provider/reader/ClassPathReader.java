package com.github.rusakovichma.tictaac.provider.reader;

import com.github.rusakovichma.tictaac.parser.impl.NodeTreeParser;
import com.github.rusakovichma.tictaac.parser.model.NodeTree;

import java.io.IOException;
import java.io.InputStream;

class ClassPathReader implements Reader {

    @Override
    public NodeTree read(String path) {
        try {
            if (path.startsWith("classpath:")) {
                path = path.replaceFirst("classpath:", "");
            }

            InputStream inputStream = ClassLoader.class.getResourceAsStream(path);
            return new NodeTreeParser().getNodeTree(inputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Cannot init classpath file[" + path + "]", ex);
        }
    }
}
