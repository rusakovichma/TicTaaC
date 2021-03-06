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
package com.github.rusakovichma.tictaac.parser.impl;

import com.github.rusakovichma.tictaac.parser.NodeParser;
import com.github.rusakovichma.tictaac.parser.model.Node;
import com.github.rusakovichma.tictaac.parser.model.NodeHelper;
import com.github.rusakovichma.tictaac.parser.model.NodeTree;
import com.github.rusakovichma.tictaac.parser.model.NodeType;
import com.github.rusakovichma.tictaac.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.function.Consumer;

import static com.github.rusakovichma.tictaac.util.InputStreamUtil.readLineByLine;

public class NodeTreeParser implements NodeParser {

    private Node findClosestSibling(NodeTree tree, Node newNode) {
        if (tree.isEmpty()) {
            return null;
        }

        Iterator<Node> reversedIterator = tree.descendingIterator();

        while (reversedIterator.hasNext()) {
            Node sibling = reversedIterator.next();

            //already parent or more mature node
            if (newNode.getIndentLength() > sibling.getIndentLength()) {
                break;
            }

            if (sibling.getIndentLength() == newNode.getIndentLength() &&
                    sibling.getNodeType() == newNode.getNodeType()) {
                return sibling;
            }
        }

        return null;
    }

    private void addSibling(Node prevNode, Node newNode) {
        newNode.setNodeLevel(prevNode.getNodeLevel());
        prevNode.setNextSibling(newNode);

        addDescendant(prevNode.getParent(), newNode);
    }

    private void addDescendant(Node parent, Node desc) {
        if (parent != null) {
            desc.setNodeLevel(parent.getNodeLevel() + 1);
            if (parent.getDescendants() == null) {
                parent.setDescendants(new NodeTree());
            }
            parent.getDescendants().add(desc);
        }
        desc.setParent(parent);
    }

    private String omitComments(String line) {
        return line.replaceAll("#.*$", "");
    }

    @Override
    public NodeTree getNodeTree(InputStream inputStream) throws IOException {
        final NodeTree tree = new NodeTree();

        Consumer<String> fileLineConsumer = line -> {
            if (line == null || line.trim().isEmpty()) {
                return;
            }

            line = omitComments(line);

            int indentLength;
            try {
                indentLength = StringUtils.getStartingIndentLength(line);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }

            NodeType nodeType = NodeType.getType(line);
            if (nodeType == NodeType.unknown) {
                return;
            }

            Node newNode = new Node();
            newNode.setNodeType(nodeType);
            newNode.setContent(line.trim());
            newNode.setIndentLength(indentLength);

            if (nodeType == NodeType.property) {
                newNode.setCollection(NodeHelper.isCollectionProperty(line));
            }

            if (indentLength == 0) {
                newNode.setNodeLevel(0);

                Node sibling = findClosestSibling(tree, newNode);
                if (sibling != null) {
                    //sibling
                    addSibling(sibling, newNode);
                }
            } else {
                if (tree.isEmpty()) {
                    newNode.setNodeLevel(0);
                }

                Node prevNode = tree.getLast();
                if (prevNode.getIndentLength() == indentLength) {
                    //sibling
                    addSibling(prevNode, newNode);
                } else if (indentLength > prevNode.getIndentLength()) {
                    //parent
                    addDescendant(prevNode, newNode);
                } else if (indentLength < prevNode.getIndentLength()) {
                    Node closestSibling = findClosestSibling(tree, newNode);
                    if (closestSibling != null) {
                        addSibling(closestSibling, newNode);
                    }
                }
            }

            tree.add(newNode);
        };

        readLineByLine(inputStream, fileLineConsumer);

        return tree;
    }
}
