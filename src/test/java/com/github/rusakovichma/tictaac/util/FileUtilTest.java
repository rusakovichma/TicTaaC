package com.github.rusakovichma.tictaac.util;

import com.github.rusakovichma.tictaac.provider.reader.ThreatModelFilter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilTest {

    @Test
    void readLineByLine() throws IOException {
        Consumer<String> lineReader = string -> assertTrue(!string.isEmpty());
        FileUtil.readLineByLine("src/test/resources/threat-model-test.yml", lineReader);
    }

    @Test
    void extractFiles() throws IOException {
        List<String> paths = new ArrayList<>();

        paths.add("src/test/resources/threat-model-test.yml");
        paths.add("src/test/resources/");

        List<String> threatModels = FileUtil.extractFiles(paths, new ThreatModelFilter());
        assertEquals(7, threatModels.size());
    }

    @Test
    void getFilenameWithoutExtensionFromPath() {
        String filename = FileUtil.getFilenameWithoutExtensionFromPath("src/test/resources/threat-model-test.yml");
        assertEquals("threat-model-test", filename);
    }

    @Test
    void getParentFolderFromFilePath() {
        String parent = FileUtil.getParentFolderFromFilePath("src/test/resources/threat-model-test.yml");
        assertEquals("src/test/resources/", parent);
    }
}