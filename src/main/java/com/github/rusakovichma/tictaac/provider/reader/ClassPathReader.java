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

import com.github.rusakovichma.tictaac.parser.impl.NodeTreeParser;
import com.github.rusakovichma.tictaac.parser.model.NodeTree;
import com.github.rusakovichma.tictaac.util.ResourceUtil;

import java.io.IOException;
import java.net.URL;

class ClassPathReader implements Reader {

    @Override
    public NodeTree read(String path) {
        try {
            if (path.startsWith("classpath:")) {
                path = path.replaceFirst("classpath:", "");
            }

            URL resource = ResourceUtil.class.getResource(path);
            return new NodeTreeParser().getNodeTree(resource.openStream());
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Cannot init classpath file[" + path + "]", ex);
        }
    }
}
