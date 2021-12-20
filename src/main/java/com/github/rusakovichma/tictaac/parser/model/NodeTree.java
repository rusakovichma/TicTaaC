package com.github.rusakovichma.tictaac.parser.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class NodeTree extends LinkedList<Node> {

    public NodeTree filter(List<Predicate<Node>> predicates) {
        return this.stream()
                .filter(predicates.stream().reduce(x -> true, Predicate::and))
                .collect(Collectors.toCollection(NodeTree::new));
    }

    public Optional<Node> filterNode(List<Predicate<Node>> predicates) {
        return filter(predicates)
                .stream()
                .findFirst();
    }

    public Optional<Node> filter(Predicate<Node> predicate) {
        return this.stream()
                .filter(predicate)
                .findFirst();
    }

}
