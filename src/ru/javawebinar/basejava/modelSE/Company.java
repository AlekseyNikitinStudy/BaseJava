package ru.javawebinar.basejava.modelSE;

import java.util.List;

public class Company {
    private String name;

    private List<Period> periods;

    public Company(String name, List<Period> periods) {
        this.name = name;
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
