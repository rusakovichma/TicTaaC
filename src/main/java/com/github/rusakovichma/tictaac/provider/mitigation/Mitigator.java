package com.github.rusakovichma.tictaac.provider.mitigation;

import com.github.rusakovichma.tictaac.model.Threat;

import java.util.Collection;

public interface Mitigator {

    public void setMitigationStrategy(Collection<Threat> threats);

}
