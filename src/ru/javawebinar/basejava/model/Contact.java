package ru.javawebinar.basejava.model;

public class Contact {
    String type;
    String values;

    public Contact(String type, String values) {
        this.type = type;
        this.values = values;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "type='" + type + '\'' +
                ", values='" + values + '\'' +
                '}';
    }
}
