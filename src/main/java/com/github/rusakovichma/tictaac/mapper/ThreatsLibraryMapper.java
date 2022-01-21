package com.github.rusakovichma.tictaac.mapper;

import com.github.rusakovichma.tictaac.model.ThreatsLibrary;
import com.github.rusakovichma.tictaac.parser.model.NodeTree;

public class ThreatsLibraryMapper extends AbstractModelMapper<ThreatsLibrary> {

    public ThreatsLibraryMapper(NodeTree nodeTree) {
        super(nodeTree);
    }

    @Override
    public ThreatsLibrary getModel() {
        ThreatsLibrary threatsLibrary = new ThreatsLibrary();
        mapModel(threatsLibrary);
        return threatsLibrary;
    }
}
