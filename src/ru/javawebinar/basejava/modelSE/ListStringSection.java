package ru.javawebinar.basejava.modelSE;

import java.util.List;

public class ListStringSection extends Section {
    private List<String> values;

    public ListStringSection(List<String> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "ListStringSection{" +
                "values=" + values +
                '}' + "\n";
    }
}
