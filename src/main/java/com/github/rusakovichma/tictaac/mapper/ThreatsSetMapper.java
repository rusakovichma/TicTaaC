package com.github.rusakovichma.tictaac.mapper;

import com.github.rusakovichma.tictaac.model.ThreatsSet;
import com.github.rusakovichma.tictaac.parser.model.NodeTree;

public class ThreatsSetMapper extends AbstractModelMapper<ThreatsSet> {

    public ThreatsSetMapper(NodeTree nodeTree) {
        super(nodeTree);
    }

    @Override
    public ThreatsSet getModel() {
        ThreatsSet threatsSet = new ThreatsSet();
        mapModel(threatsSet);
        return threatsSet;
    }
}
