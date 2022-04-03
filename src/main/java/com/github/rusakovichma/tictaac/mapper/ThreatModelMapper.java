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
