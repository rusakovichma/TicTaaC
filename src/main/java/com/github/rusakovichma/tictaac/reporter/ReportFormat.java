package com.github.rusakovichma.tictaac.reporter;

public enum ReportFormat {
    json,
    html;

    public static ReportFormat fromString(String str) {
        for (ReportFormat format : ReportFormat.values()) {
            if (format.name().equalsIgnoreCase(str)) {
                return format;
            }
        }
        return null;
    }
}
