package com.github.rusakovichma.tictaac.util;

import java.util.*;

public class ConsoleUtil {

    private ConsoleUtil() {
    }

    private static class ConsoleParam {
        private String key;
        private String value;

        public ConsoleParam(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ConsoleParam that = (ConsoleParam) o;
            return Objects.equals(key, that.key) && Objects.equals(value, that.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }
    }

    public static Map<String, String> getParamsMap(String[] args) {
        Stack<ConsoleParam> params = new Stack<>();
        if (args != null && args.length != 0) {
            for (int i = 0; i < args.length; i++) {
                if (args[i].trim().startsWith("--")) {
                    params.push(new ConsoleParam(args[i].trim()
                            .replaceFirst("--", "")));
                } else {
                    if (!params.empty()) {
                        params.peek().setValue(
                                args[i].trim().replaceAll("\"", "")
                                        .replaceAll("'", ""));
                    }
                }
            }
        }

        Map<String, String> paramsMap = new HashMap<>();
        while (!params.empty()) {
            ConsoleParam param = params.pop();
            paramsMap.put(param.getKey(), param.getValue());
        }

        return paramsMap;
    }

}
