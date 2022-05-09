package com.github.rusakovichma.tictaac.reporter.chart;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.style.PieStyler;
import org.knowm.xchart.style.Styler;

import java.io.IOException;

public class XChartPlotter implements ChartPlotter {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 375;

    private final PieChart chart;

    private PieChart init(String title, int width, int height) {
        PieChart chart = new PieChartBuilder()
                .width(width)
                .height(height)
                .title(title)
                .theme(Styler.ChartTheme.Matlab)
                .build();

        chart.getStyler().setLabelType(PieStyler.LabelType.Percentage);
        chart.getStyler().setSliceBorderWidth(10);
        return chart;
    }

    public XChartPlotter(String title) {
        this.chart = init(title, WIDTH, HEIGHT);
    }

    @Override
    public void addSeries(String name, int value) {
        chart.addSeries(name, value);
    }

    @Override
    public byte[] getImageBytes() {
        try {
            return BitmapEncoder.getBitmapBytes(chart, BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
