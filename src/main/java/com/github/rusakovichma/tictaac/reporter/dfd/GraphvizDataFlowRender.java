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
package com.github.rusakovichma.tictaac.reporter.dfd;

import com.github.rusakovichma.tictaac.model.ThreatModel;
import com.github.rusakovichma.tictaac.model.threatmodel.Boundary;
import com.github.rusakovichma.tictaac.model.threatmodel.DataFlow;
import com.github.rusakovichma.tictaac.model.threatmodel.Element;
import com.github.rusakovichma.tictaac.model.threatmodel.boundary.BoundaryCategory;
import com.github.rusakovichma.tictaac.util.ImageUtils;
import guru.nidi.graphviz.attribute.*;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.LinkSource;
import guru.nidi.graphviz.model.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static guru.nidi.graphviz.attribute.Rank.RankDir.LEFT_TO_RIGHT;
import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;

public class GraphvizDataFlowRender implements DataFlowRender {

    private LinkSource getNodeFromElement(Element element) {
        switch (element.getType()) {
            case interactor:
            case externalService:
            case internalService:
                return node(element.getId()).with(Label.of(element.getName()))
                        .with(Shape.BOX);
            case proxyServer:
            case webServer:
            case process:
                return node(element.getId()).with(Shape.M_RECORD,
                        Label.of(String.format("{<f0> |<f1> %s\n\n\n}", element.getName())));
            case database:
                return node(element.getId()).with(Shape.M_RECORD,
                        Label.of(String.format("<f0> |<f1> %s\n\n\n", element.getName()))
                );
            default:
                throw new RuntimeException("Unsupported entity");
        }
    }

    private Graph getGraphFromBoundary(Boundary boundary) {
        Graph graph = graph(boundary.getId()).cluster();

        if (boundary.getCategory() == BoundaryCategory.globalNetwork) {
            graph = graph.graphAttr().with(Color.WHITE, Rank.newRank().noCluster());
        } else {
            graph = graph.graphAttr().with(Color.BLUE, Label.of(boundary.getId()));
        }

        LinkSource[] boundaryLinkSources = new LinkSource[boundary.getElements().size()];
        for (int i = 0; i < boundary.getElements().size(); i++) {
            boundaryLinkSources[i] = getNodeFromElement(boundary.getElements().get(i));
        }

        graph = graph.with(boundaryLinkSources);

        return graph;
    }

    @Override
    public byte[] createDataFlow(ThreatModel threatModel) throws IOException {
        if (threatModel == null) {
            return new byte[0];
        }

        Graph dataFlowGraph = graph(threatModel.getName()).directed()
                .graphAttr().with(Rank.dir(LEFT_TO_RIGHT));

        List<LinkSource> dataFlowObjects = new ArrayList<>();
        for (Boundary boundary : threatModel.getBoundaries()) {
            dataFlowObjects.add(getGraphFromBoundary(boundary));
        }

        //if some elements are not within any boundary
        for (Element element : threatModel.getElements()) {
            if (!threatModel.getBoundaries().stream()
                    .anyMatch(boundary -> boundary.getElements().contains(element))) {
                dataFlowObjects.add(getNodeFromElement(element));
            }
        }

        for (DataFlow dataFlow : threatModel.getDataFlows()) {
            Node node = node(dataFlow.getSource().getId())
                    .link(dataFlow.getTarget().getId());
            if (dataFlow.getTitle() != null) {
                node = node.with(Label.of(dataFlow.getTitle()));
            }

            dataFlowObjects.add(node);
        }

        dataFlowGraph = dataFlowGraph.with(dataFlowObjects);

        return ImageUtils.toByteArray(
                Graphviz.fromGraph(dataFlowGraph).render(Format.PNG).toImage(),
                "png");
    }
}
