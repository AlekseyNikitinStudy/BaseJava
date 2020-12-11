package ru.javawebinar.basejava.model;

import java.time.LocalDate;
import java.util.logging.Logger;

public class ExperiencePeriod {
    private LocalDate start;
    private LocalDate end;
    private String position;
    private String experience;

    public ExperiencePeriod(LocalDate start, LocalDate end, String position, String experience) {
        this.start = start;
        this.end = end;
        this.position = position;
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "\nExperiencePeriod{" +
                "start=" + start +
                ", end=" + end +
                ", position='" + position + '\'' +
                ", experience='" + experience + '\'' +
                '}';
    }
}
