package com.github.rusakovichma.tictaac.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


class StringUtilsTest {

    @Test
    void getStartingIndentLengthTest() throws Exception {
        int startingIndent = StringUtils.getStartingIndentLength("\t\tsensitivity: non-sensitive");
        assertTrue(startingIndent == 8);

        startingIndent = StringUtils.getStartingIndentLength(" \tsensitivity: non-sensitive");
        assertTrue(startingIndent == 5);

        startingIndent = StringUtils.getStartingIndentLength("elements:");
        assertTrue(startingIndent == 0);
    }
}