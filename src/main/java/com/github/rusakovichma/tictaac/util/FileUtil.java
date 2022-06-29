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
package com.github.rusakovichma.tictaac.util;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class FileUtil {

    private FileUtil() {
    }

    public static void readLineByLine(String file, Consumer<String> lineStringConsumer)
            throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(file))) {
            stream.forEach(lineStringConsumer);
        }
    }

    public static InputStream fileToInputStream(String filePath)
            throws IOException {
        File file = new File(filePath);
        return new FileInputStream(file);
    }

    public static boolean findString(File file, String string) {
        try {
            try (Stream<String> lines = Files.lines(file.toPath(), Charset.defaultCharset())) {
                return (lines.filter(line -> line.contains(string))
                        .findFirst()
                        .get()) != null;
            }
        } catch (Exception ex) {
            return false;
        }
    }

    public static List<String> extractFiles(List<String> paths, FileFilter filter) {
        if (paths == null || paths.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        List<String> extractedFiles = new ArrayList<>();

        for (String path : paths) {
            File file = new File(path);
            if (file.exists()) {
                if (file.isFile()) {
                    extractedFiles.add(path);
                } else {
                    File[] filesInFolder = (filter != null)
                            ? file.listFiles(filter) : file.listFiles();
                    if (filesInFolder != null && filesInFolder.length != 0) {
                        for (File fileInFolder : filesInFolder) {
                            extractedFiles.add(fileInFolder.getAbsolutePath());
                        }
                    }
                }
            }
        }

        return extractedFiles;
    }


}
