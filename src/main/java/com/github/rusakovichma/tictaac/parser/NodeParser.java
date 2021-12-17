package com.github.rusakovichma.tictaac.parser;

import com.github.rusakovichma.tictaac.parser.model.NodeTree;

import java.io.IOException;
import java.io.InputStream;

public interface NodeParser {

    public NodeTree getNodeTree(InputStream inputStream) throws IOException;

}
