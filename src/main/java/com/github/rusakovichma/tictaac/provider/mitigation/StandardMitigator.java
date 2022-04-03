/*
 * This file is part of TicTaaC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Copyright (c) 2022 Mikhail Rusakovich. All Rights Reserved.
 */
package com.github.rusakovichma.tictaac.provider.mitigation;

import com.github.rusakovichma.tictaac.model.Threat;
import com.github.rusakovichma.tictaac.model.mitigation.MitigationStatus;
import com.github.rusakovichma.tictaac.model.mitigation.ThreatRef;
import com.github.rusakovichma.tictaac.model.mitigation.MitigationsLibrary;

import java.util.Collection;

public class StandardMitigator implements Mitigator {

    private final MitigationsLibrary mitigationsLibrary;

    public StandardMitigator(MitigationsLibrary mitigationsLibrary) {
        this.mitigationsLibrary = mitigationsLibrary;
    }

    private void setStrategy(Collection<Threat> threats, Collection<ThreatRef> refs, MitigationStatus strategy) {
        for (ThreatRef ref : refs) {
            threats.stream()
                    .filter(threat -> threat.getId().equalsIgnoreCase(ref.getId()))
                    .findFirst()
                    .ifPresent(threat -> threat.setMitigationStatus(strategy));
        }
    }

    @Override
    public void setMitigationStrategy(Collection<Threat> threats) {
        if (mitigationsLibrary.getMitigated() != null) {
            setStrategy(threats, mitigationsLibrary.getMitigated(), MitigationStatus.Mitigated);
        }

        if (mitigationsLibrary.getAccepted() != null) {
            setStrategy(threats, mitigationsLibrary.getAccepted(), MitigationStatus.Accepted);
        }

        if (mitigationsLibrary.getAvoided() != null) {
            setStrategy(threats, mitigationsLibrary.getAvoided(), MitigationStatus.Avoided);
        }

        if (mitigationsLibrary.getTransferred() != null) {
            setStrategy(threats, mitigationsLibrary.getTransferred(), MitigationStatus.Transferred);
        }
    }
}
