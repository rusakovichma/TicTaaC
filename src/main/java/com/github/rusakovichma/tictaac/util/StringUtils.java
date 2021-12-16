package com.github.rusakovichma.tictaac.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class StringUtils {

    private static final int TAB_EQ_SPACES_NUMBER = 4;

    private StringUtils() {
    }

    public static class CounterResults {
        private int tabs = 0;
        private int spaces = 0;

        public void incTabCount() {
            ++tabs;
        }

        public void incSpaceCount() {
            ++spaces;
        }

        public int getTabCount() {
            return tabs;
        }

        public int getSpaceCount() {
            return spaces;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("tabs: ");
            sb.append(tabs);
            sb.append("\nspaces: ");
            sb.append(spaces);

            return sb.toString();
        }
    }

    public static int getStartingIndentLength(String string) throws IOException {
        CounterResults counter = new CounterResults();

        InputStream is = new ByteArrayInputStream(string.getBytes());
        try {
            byte[] c = new byte[1024];

            int readChars = 0;
            while ((readChars = is.read(c)) != -1) {
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\t') {
                        counter.incTabCount();
                        continue;
                    }

                    // see if we have a space
                    if (c[i] == ' ') {
                        counter.incSpaceCount();
                        continue;
                    }

                    break;
                }
            }
        } finally {
            is.close();
        }

        return counter.getTabCount() * TAB_EQ_SPACES_NUMBER + counter.getSpaceCount();
    }


}
