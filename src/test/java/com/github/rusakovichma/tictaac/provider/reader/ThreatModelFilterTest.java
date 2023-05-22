package com.github.rusakovichma.tictaac.provider.reader;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ThreatModelFilterTest {

    @Test
    void accept() {
        File dir = new File("src/test/resources");
        File[] modelFiles = dir.listFiles(new ThreatModelFilter());

        assertTrue(modelFiles.length == 6);
    }
}