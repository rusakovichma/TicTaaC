package com.github.rusakovichma.tictaac.provider.reader;

import com.github.rusakovichma.tictaac.parser.model.NodeTree;

public interface Reader {

    public NodeTree read(String path);

}
