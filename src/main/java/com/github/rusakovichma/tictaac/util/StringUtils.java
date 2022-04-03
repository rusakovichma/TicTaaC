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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    public static String removeFirstAndLastChar(String string, char ch) {
        if (string == null || string.length() < 3) {
            return string;
        }
        if (string.charAt(0) == ch && string.charAt(string.length() - 1) == ch) {
            return string.substring(1, string.length() - 1);
        }
        return string;
    }

    public static byte[] digest(byte[] input) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        byte[] result = md.digest(input);
        return result;
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static boolean isDigest(String str) {
        if (str == null) {
            return false;
        }
        return str.matches("^[a-f0-9]{32}$");
    }

    public static String sha1Hash(String string) {
        String sha1 = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(string.getBytes("UTF-8"));
            sha1 = bytesToHex(crypt.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sha1;
    }

}
