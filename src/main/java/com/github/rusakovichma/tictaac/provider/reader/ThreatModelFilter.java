package com.github.rusakovichma.tictaac.provider.reader;

import com.github.rusakovichma.tictaac.util.FileUtil;

import java.io.File;
import java.io.FileFilter;

public class ThreatModelFilter implements FileFilter {

    @Override
    public boolean accept(File modelFile) {
        if (!modelFile.getName().toLowerCase().endsWith(".yml")) {
            return false;
        }
        return FileUtil.findString(modelFile, "elements:")
                && FileUtil.findString(modelFile, "data-flows:");
    }
}
