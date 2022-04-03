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
package com.github.rusakovichma.tictaac.model.mitigation;

import com.github.rusakovichma.tictaac.model.threatmodel.annotation.RootCollection;

import java.util.LinkedList;

public class MitigationsLibrary {

    private String name;
    private String version;

    @RootCollection
    private LinkedList<ThreatRef> mitigated;

    @RootCollection
    private LinkedList<ThreatRef> accepted;

    @RootCollection
    private LinkedList<ThreatRef> avoided;

    @RootCollection
    private LinkedList<ThreatRef> transferred;

    public LinkedList<ThreatRef> getMitigated() {
        return mitigated;
    }

    public void setMitigated(LinkedList<ThreatRef> mitigated) {
        this.mitigated = mitigated;
    }

    public LinkedList<ThreatRef> getAccepted() {
        return accepted;
    }

    public void setAccepted(LinkedList<ThreatRef> accepted) {
        this.accepted = accepted;
    }

    public LinkedList<ThreatRef> getAvoided() {
        return avoided;
    }

    public void setAvoided(LinkedList<ThreatRef> avoided) {
        this.avoided = avoided;
    }

    public LinkedList<ThreatRef> getTransferred() {
        return transferred;
    }

    public void setTransferred(LinkedList<ThreatRef> transferred) {
        this.transferred = transferred;
    }
}
