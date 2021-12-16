package com.github.rusakovichma.tictaac.parser.model;

import java.util.LinkedList;

public class Node {

    private int indentLength;
    private int nodeLevel;
    private Node parent;
    private Node nextSibling;
    private LinkedList<Node> descendants;
    private String content;
    private NodeType nodeType;

    public int getIndentLength() {
        return indentLength;
    }

    public void setIndentLength(int indentLength) {
        this.indentLength = indentLength;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getNextSibling() {
        return nextSibling;
    }

    public void setNextSibling(Node nextSibling) {
        this.nextSibling = nextSibling;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNodeLevel() {
        return nodeLevel;
    }

    public void setNodeLevel(int nodeLevel) {
        this.nodeLevel = nodeLevel;
    }

    public LinkedList<Node> getDescendants() {
        return descendants;
    }

    public void setDescendants(LinkedList<Node> descendants) {
        this.descendants = descendants;
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }
}
