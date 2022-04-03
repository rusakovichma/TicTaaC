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

import com.github.rusakovichma.tictaac.mapper.MitigationsLibraryMapper;
import com.github.rusakovichma.tictaac.model.mitigation.MitigationsLibrary;
import com.github.rusakovichma.tictaac.provider.reader.Reader;
import com.github.rusakovichma.tictaac.provider.reader.UnifiedReader;

import java.util.HashMap;
import java.util.Map;

public class StandardMitigationProvider implements MitigationLibraryProvider {

    private final String mitigationsLibraryPath;
    private final Reader reader;

    private Map<String, String> getAccessParams(Map<String, String> params) {
        Map<String, String> accessParams = new HashMap<>();

        String accessUsername = accessParams.get("mitigationsAccessUsername");
        if (accessUsername != null && !accessUsername.trim().isEmpty()) {
            accessParams.put("username", accessUsername);
        }

        String accessPassword = accessParams.get("mitigationsAccessPassword");
        if (accessPassword != null && !accessPassword.trim().isEmpty()) {
            accessParams.put("password", accessPassword);
        }

        return accessParams;
    }

    public StandardMitigationProvider(String mitigationsLibraryPath, Map<String, String> params) {
        this.mitigationsLibraryPath = mitigationsLibraryPath;
        this.reader = new UnifiedReader(getAccessParams(params));
    }

    public StandardMitigationProvider(String mitigationsLibraryPath) {
        this.mitigationsLibraryPath = mitigationsLibraryPath;
        this.reader = new UnifiedReader();
    }

    @Override
    public MitigationsLibrary getMitigations() {
        return new MitigationsLibraryMapper(
                reader.read(mitigationsLibraryPath))
                .getModel();
    }
}
