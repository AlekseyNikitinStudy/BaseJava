package ru.javawebinar.basejava.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
