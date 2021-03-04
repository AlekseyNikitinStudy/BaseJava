package ru.javawebinar.basejava.model;

import java.util.Objects;

public class SingleLineSection extends AbstractSection {
    private String value;

    public SingleLineSection(String value) {
        Objects.requireNonNull(value, "value must not be null");
        this.value = value;
    }

    public SingleLineSection() {
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
