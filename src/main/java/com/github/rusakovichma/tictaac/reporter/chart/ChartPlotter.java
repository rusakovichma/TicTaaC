package com.github.rusakovichma.tictaac.reporter.chart;

public interface ChartPlotter {

    public void addSeries(String name, int value);

    public byte[] getImageBytes();

}
