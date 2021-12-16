package com.github.rusakovichma.tictaac.util;

import java.io.IOException;
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

}
