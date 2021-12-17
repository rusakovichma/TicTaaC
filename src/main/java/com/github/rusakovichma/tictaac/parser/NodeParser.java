package com.github.rusakovichma.tictaac.parser;

import com.github.rusakovichma.tictaac.parser.model.NodeTree;

import java.io.IOException;

public interface NodeParser {

    public NodeTree getNodeTree(String file) throws IOException;

}
