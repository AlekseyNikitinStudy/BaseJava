package ru.javawebinar.basejava.modelSE;

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
