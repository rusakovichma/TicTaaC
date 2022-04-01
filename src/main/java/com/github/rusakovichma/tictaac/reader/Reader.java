package com.github.rusakovichma.tictaac.reader;

import com.github.rusakovichma.tictaac.parser.model.NodeTree;

import java.util.Map;

public interface Reader {

    public NodeTree read(String path);

}
