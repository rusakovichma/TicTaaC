package com.github.rusakovichma.tictaac.reporter;

import com.github.rusakovichma.tictaac.model.Threat;

import java.util.Collection;

public interface ThreatsReporter {

    public void publish(Collection<Threat> threats);

}
