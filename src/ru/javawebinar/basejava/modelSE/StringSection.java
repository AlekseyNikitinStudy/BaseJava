package ru.javawebinar.basejava.modelSE;

public class StringSection extends Section {
    private String value;

    public StringSection(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "StringSection{" +
                "value='" + value + '\'' +
                '}' + "\n";
    }
}
