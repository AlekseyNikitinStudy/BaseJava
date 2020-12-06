package ru.javawebinar.basejava.model;

import java.util.LinkedList;
import java.util.List;

public class Section<SN> {
    private SectionType type;
    private List<SN> values = new LinkedList<>();

    public Section(SectionType type) {
        this.type = type;
    }

    public void add(SN node) {
        values.add(node);
    }

    @Override
    public String toString() {
        return "Section{" +
                "type=" + type +
                ", values=" + values +
                '}';
    }
}
