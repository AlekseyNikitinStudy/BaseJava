package ru.javawebinar.basejava.model;

import java.util.List;

public class BulletedListSection extends AbstractSection {
    private List<String> values;

    public BulletedListSection(List<String> values) {
        this.values = values;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "ListStringSection{" +
                "values=" + values +
                '}' + "\n";
    }
}
