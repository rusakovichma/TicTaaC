package com.github.rusakovichma.tictaac.util;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilTest {

    @Test
    void readLineByLine() throws IOException {
        Consumer<String> lineReader = string -> assertTrue(!string.isEmpty());
        FileUtil.readLineByLine("src/test/resources/taac-model.yml", lineReader);
    }
}