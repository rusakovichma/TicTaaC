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
package com.github.rusakovichma.tictaac.provider.reader;

import com.github.rusakovichma.tictaac.parser.model.NodeTree;

import java.util.Collections;
import java.util.Map;

public class UnifiedReader implements Reader {

    private final Map<String, String> parameters;

    public UnifiedReader(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    public UnifiedReader() {
        this.parameters = Collections.EMPTY_MAP;
    }


    @Override
    public NodeTree read(String path) {
        if (path == null) {
            throw new IllegalArgumentException("File path parameter cannot be null");
        }

        if (path.toLowerCase().startsWith("http:") || path.toLowerCase().startsWith("https:")) {
            return new UrlReader(parameters).read(path);
        }

        if (path.toLowerCase().startsWith("classpath:")) {
            return new ClassPathReader().read(path);
        }

        return new ExternalReader().read(path);
    }
}
