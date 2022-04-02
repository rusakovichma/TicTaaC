package com.github.rusakovichma.tictaac.reporter;

import com.github.rusakovichma.tictaac.model.Threat;

import java.io.IOException;
import java.util.Collection;

public interface ThreatsReporter {

    public void publish(ReportHeader header, Collection<Threat> threats)
            throws IOException;

}
