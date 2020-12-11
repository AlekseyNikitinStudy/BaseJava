package ru.javawebinar.basejava.model;

import java.util.List;

public class Section<N> {
    private List<N> nodes;

    public List<N> getNodes() {
        return nodes;
    }

    public void setNodes(List<N> nodes) {
        this.nodes = nodes;
    }

    @Override
    public String toString() {
        return "\nSection{" +
                "nodes=" + nodes +
                '}';
    }
}
