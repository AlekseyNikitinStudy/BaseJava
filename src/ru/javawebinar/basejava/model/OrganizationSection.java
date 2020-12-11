package ru.javawebinar.basejava.model;

import java.util.List;

public class OrganizationSection {
    private String name;
    private List<ExperiencePeriod> periods;

    public OrganizationSection(String name, List<ExperiencePeriod> periods) {
        this.name = name;
        this.periods = periods;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ExperiencePeriod> getPeriods() {
        return periods;
    }

    public void setPeriods(List<ExperiencePeriod> periods) {
        this.periods = periods;
    }

    @Override
    public String toString() {
        return "\nOrganizationSection{" +
                "name='" + name + '\'' +
                ", periods=" + periods +
                '}';
    }
}
