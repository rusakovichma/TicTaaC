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
package com.github.rusakovichma.tictaac.provider.rules;

import com.github.rusakovichma.tictaac.mapper.ThreatsLibraryMapper;
import com.github.rusakovichma.tictaac.model.ThreatsLibrary;
import com.github.rusakovichma.tictaac.provider.reader.Reader;
import com.github.rusakovichma.tictaac.provider.reader.UnifiedReader;

import java.util.HashMap;
import java.util.Map;

public class StandardThreatRulesProvider implements ThreatRulesProvider {

    private final String threatRulesPath;
    private final Reader reader;

    private Map<String, String> getAccessParams(Map<String, String> params) {
        Map<String, String> accessParams = new HashMap<>();

        String accessUsername = accessParams.get("threatsLibraryAccessUsername");
        if (accessUsername != null && !accessUsername.trim().isEmpty()) {
            accessParams.put("username", accessUsername);
        }

        String accessPassword = accessParams.get("threatsLibraryAccessPassword");
        if (accessPassword != null && !accessPassword.trim().isEmpty()) {
            accessParams.put("password", accessPassword);
        }

        return accessParams;
    }

    public StandardThreatRulesProvider(String threatRulesPath, Map<String, String> params) {
        this.threatRulesPath = threatRulesPath;
        this.reader = new UnifiedReader(params);
    }

    @Override
    public ThreatsLibrary getThreatsLibrary() {
        return new ThreatsLibraryMapper(
                reader.read(threatRulesPath))
                .getModel();
    }
}
