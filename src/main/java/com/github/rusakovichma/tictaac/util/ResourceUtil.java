package com.github.rusakovichma.tictaac.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Collectors;


public class ResourceUtil {

    private ResourceUtil() {
    }

    public static String readResource(String url) {
        URL resource = ResourceUtil.class.getResource(url);
        return readResource(resource);
    }

    public static String readResource(URL resource) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.openStream()))) {
            return reader.lines().collect(Collectors.joining("\n"));
        } catch (Exception e) {
            throw new IllegalStateException("Failed to read: " + resource, e);
        }
    }

}

