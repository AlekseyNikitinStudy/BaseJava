package ru.javawebinar.basejava.model;

import java.util.List;

public class Company {
    private String name;

    private List<Period> periods;

    public Company(String name, List<Period> periods) {
        this.name = name;
        this.periods = periods;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", periods=" + periods +
                '}' + "\n";
    }
}
