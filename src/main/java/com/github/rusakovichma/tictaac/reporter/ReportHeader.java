package com.github.rusakovichma.tictaac.reporter;

import java.util.Date;

public class ReportHeader {

    private final String name;
    private final String version;
    private final Date date;

    public ReportHeader(String name, String version, Date date) {
        this.name = name;
        this.version = version;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public Date getDate() {
        return date;
    }
}
