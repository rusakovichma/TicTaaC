package com.github.rusakovichma.tictaac.provider.reader;

import com.github.rusakovichma.tictaac.util.FileUtil;

import java.io.File;
import java.io.FileFilter;

public class MitigationsFilter implements FileFilter {

    @Override
    public boolean accept(File mitigationsFile) {
        if (!mitigationsFile.getName().toLowerCase().endsWith(".yml")) {
            return false;
        }
        return FileUtil.findString(mitigationsFile, "mitigated:");
    }

}
