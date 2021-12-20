package com.github.rusakovichma.tictaac.mapper;

import com.github.rusakovichma.tictaac.model.ThreatModel;
import com.github.rusakovichma.tictaac.parser.model.NodeTree;

public class ThreatModelMapper extends AbstractModelMapper<ThreatModel> {

    public ThreatModelMapper(NodeTree nodeTree) {
        super(nodeTree);
    }

    @Override
    public ThreatModel getModel() {
        ThreatModel threatModel = new ThreatModel();
        mapModel(threatModel);
        return threatModel;
    }
}
