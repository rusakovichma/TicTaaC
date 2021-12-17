package com.github.rusakovichma.tictaac.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class InputStreamUtil {

    private InputStreamUtil() {
    }

    public static void readLineByLine(InputStream inputStream, Consumer<String> lineStringConsumer) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try (Stream<String> stream = reader.lines()) {
            stream.forEach(lineStringConsumer);
        }
    }

}
