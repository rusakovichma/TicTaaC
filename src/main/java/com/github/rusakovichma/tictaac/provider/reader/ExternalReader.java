package com.github.rusakovichma.tictaac.provider.reader;

import com.github.rusakovichma.tictaac.parser.impl.NodeTreeParser;
import com.github.rusakovichma.tictaac.parser.model.NodeTree;
import com.github.rusakovichma.tictaac.util.FileUtil;

import java.io.IOException;

class ExternalReader implements Reader {

    @Override
    public NodeTree read(String path) {
        try {
            return new NodeTreeParser().getNodeTree(
                    FileUtil.fileToInputStream(path));
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Cannot init external file provider [" + path + "]", ex);
        }
    }
}
