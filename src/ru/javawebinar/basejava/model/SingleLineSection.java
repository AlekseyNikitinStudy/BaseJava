package ru.javawebinar.basejava.model;

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
}
