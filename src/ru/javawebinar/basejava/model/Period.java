package ru.javawebinar.basejava.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Period implements Serializable {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Period)) return false;

        Period period = (Period) o;

        if (start != null ? !start.equals(period.start) : period.start != null) return false;
        if (end != null ? !end.equals(period.end) : period.end != null) return false;
        if (name != null ? !name.equals(period.name) : period.name != null) return false;
        return description != null ? description.equals(period.description) : period.description == null;
    }

    @Override
    public int hashCode() {
        int result = start != null ? start.hashCode() : 0;
        result = 31 * result + (end != null ? end.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
