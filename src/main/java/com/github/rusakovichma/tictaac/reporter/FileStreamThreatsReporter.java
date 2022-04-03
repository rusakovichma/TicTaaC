package com.github.rusakovichma.tictaac.reporter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class FileStreamThreatsReporter extends StreamThreatsReporter {

    private static final String REPORT_NAME = "threat-model-report";

    public FileStreamThreatsReporter(String reportOut, ReportFormat reportFormat)
            throws FileNotFoundException {
        super(new FileOutputStream(new File(
                        new StringBuilder(reportOut)
                                .append(File.separator)
                                .append(REPORT_NAME)
                                .append(".")
                                .append(reportFormat.name())
                                .toString())),
                reportFormat);
    }
}
