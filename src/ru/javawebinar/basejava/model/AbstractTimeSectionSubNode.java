package ru.javawebinar.basejava.model;

public abstract class AbstractTimeSectionSubNode {
    protected String from;
    protected String to;

    public AbstractTimeSectionSubNode(String from, String to) {
        this.from = from;
        this.to = to;
    }
}
