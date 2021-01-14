package ru.javawebinar.basejava.model;

import java.util.Objects;

public class SingleLineSection extends AbstractSection {
    private String value;

    public SingleLineSection(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "StringSection{" +
                "value='" + value + '\'' +
                '}' + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SingleLineSection)) return false;
        SingleLineSection that = (SingleLineSection) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
