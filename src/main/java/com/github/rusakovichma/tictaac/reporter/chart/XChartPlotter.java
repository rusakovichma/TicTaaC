package com.github.rusakovichma.tictaac.reporter.chart;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.style.PieStyler;
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
