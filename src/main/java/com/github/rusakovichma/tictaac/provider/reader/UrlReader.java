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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import java.util.Map;

class UrlReader implements Reader {

    private String username;
    private char[] password;

    public UrlReader(Map<String, String> params) {
        if (params != null) {
            this.username = params.get("username");
            String passParam = params.get("password");
            if (passParam != null) {
                this.password = passParam.toCharArray();
            }
        }
    }

    public UrlReader() {
    }

    @Override
    public NodeTree read(String path) {
        try {
            URL fileURL = new URL(path);

            InputStream inputStream = null;
            if (username != null && password != null) {
                final String auth = String.format("%s:%s", username, String.valueOf(password));

                URLConnection uc = fileURL.openConnection();
                uc.setRequestProperty("Authorization", "Basic " + Base64.getEncoder().encodeToString(auth.getBytes()));

                inputStream = uc.getInputStream();
            } else {
                inputStream = fileURL.openStream();
            }

            return new NodeTreeParser().getNodeTree(inputStream);
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Cannot init URL file [" + path + "]", ex);
        } finally {
            //clear the password after usage
            clearPasswordIfNeeded();
        }
    }

    private void clearPasswordIfNeeded() {
        if (password != null) {
            for (int i = 0; i < password.length; i++) {
                password[i] = '\0';
            }
        }
    }

}
