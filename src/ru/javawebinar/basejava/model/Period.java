package ru.javawebinar.basejava.model;

import java.time.LocalDate;

public class Period {
    private LocalDate start;
    private LocalDate end;
    private String name;
    private String description;

    public Period(LocalDate start, LocalDate end, String name, String description) {
        this.start = start;
        this.end = end;
        this.name = name;
        this.description = description;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "\nExperiencePeriod{" +
                "start=" + start +
                ", end=" + end +
                ", position='" + name + '\'' +
                ", experience='" + description + '\'' +
                '}';
    }
}
