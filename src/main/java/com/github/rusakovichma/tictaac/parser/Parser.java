package com.github.rusakovichma.tictaac.parser;

import com.github.rusakovichma.tictaac.parser.model.NodeTree;

import java.io.IOException;

public interface Parser {

    public NodeTree getNodeTree(String file) throws IOException;

}
