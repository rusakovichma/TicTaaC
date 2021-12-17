package com.github.rusakovichma.tictaac.parser.impl;

import com.github.rusakovichma.tictaac.parser.NodeParser;
import com.github.rusakovichma.tictaac.parser.model.Node;
import com.github.rusakovichma.tictaac.parser.model.NodeTree;
import com.github.rusakovichma.tictaac.parser.model.NodeType;
import com.github.rusakovichma.tictaac.util.StringUtils;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.function.Consumer;

import static com.github.rusakovichma.tictaac.util.FileUtil.readLineByLine;

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
                parent.setDescendants(new LinkedList<>());
            }
            parent.getDescendants().add(desc);
        }
        desc.setParent(parent);
    }

    @Override
    public NodeTree getNodeTree(String file) throws IOException {
        final NodeTree tree = new NodeTree();

        Consumer<String> fileLineConsumer = line -> {
            if (line == null || line.isEmpty()) {
                return;
            }

            int indentLength;
            try {
                indentLength = StringUtils.getStartingIndentLength(line);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            NodeType nodeType = NodeType.getType(line);
            if (nodeType == NodeType.unknown) {
                return;
            }

            Node newNode = new Node();
            newNode.setNodeType(nodeType);
            newNode.setContent(line.trim());
            newNode.setIndentLength(indentLength);

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

        readLineByLine(file, fileLineConsumer);

        return tree;
    }
}
