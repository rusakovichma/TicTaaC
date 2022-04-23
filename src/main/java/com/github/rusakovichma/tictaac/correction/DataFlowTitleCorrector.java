package com.github.rusakovichma.tictaac.correction;

import com.github.rusakovichma.tictaac.model.threatmodel.DataFlow;

public class DataFlowTitleCorrector implements Corrector<DataFlow> {

    @Override
    public boolean tryToCorrect(DataFlow flow) {
        if (flow.getTitle() == null || flow.getTitle().trim().isEmpty()) {
            String source = flow.getSource().getName() != null ?
                    flow.getSource().getName() : flow.getSource().getId();
            String target = flow.getTarget().getName() != null ?
                    flow.getTarget().getName() : flow.getTarget().getId();

            flow.setTitle(String.format("from \"%s\" to \"%s\"", source, target));
            return true;
        }
        return false;
    }
}
