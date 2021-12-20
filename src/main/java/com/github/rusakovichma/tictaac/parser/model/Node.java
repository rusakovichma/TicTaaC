package com.github.rusakovichma.tictaac.parser.model;

public class Node {

    private int indentLength;
    private int nodeLevel;
    private Node parent;
    private Node nextSibling;
    private NodeTree descendants;
    private String content;
    private NodeType nodeType;
    private boolean collection;

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

    public NodeTree getDescendants() {
        return descendants;
    }

    public void setDescendants(NodeTree descendants) {
        this.descendants = descendants;
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    public String getConventionalName() {
        return this.getNodeType().getConventionalName(this.getContent());
    }

    public String getNodeValue() {
        return this.getNodeType().getValue(content);
    }

    public String getConventionalNodeValue() {
        NodeType type = this.getNodeType();
        return type.getConventionalName(type.getValue(content));
    }

    public boolean isCollection() {
        return collection;
    }

    public void setCollection(boolean collection) {
        this.collection = collection;
    }
}
