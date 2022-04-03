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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
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

}
