package ru.javawebinar.basejava.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class BulletedListSection extends AbstractSection {
    private List<String> values;

    public BulletedListSection(List<String> values) {
        Objects.requireNonNull(values, "values must not be null");
        this.values = values;
    }

    public BulletedListSection() {
    }

    public static BulletedListSection createEmpty() {
        return new BulletedListSection(Collections.singletonList(""));
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BulletedListSection)) return false;
        BulletedListSection that = (BulletedListSection) o;
        return Objects.equals(values, that.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }
}
