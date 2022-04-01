package com.github.rusakovichma.tictaac.mapper;

import com.github.rusakovichma.tictaac.model.mitigation.MitigationsLibrary;
import com.github.rusakovichma.tictaac.parser.model.NodeTree;

public class MitigationsLibraryMapper extends AbstractModelMapper<MitigationsLibrary> {

    public MitigationsLibraryMapper(NodeTree nodeTree) {
        super(nodeTree);
    }

    @Override
    public MitigationsLibrary getModel() {
        MitigationsLibrary mitigationsLibrary = new MitigationsLibrary();
        mapModel(mitigationsLibrary);
        return mitigationsLibrary;
    }

}
