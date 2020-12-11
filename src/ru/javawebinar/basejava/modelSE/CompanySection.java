package ru.javawebinar.basejava.modelSE;

import java.util.List;

public class CompanySection extends Section {
    private List<Company> companies;

    public CompanySection(List<Company> companies) {
        this.companies = companies;
    }

    @Override
    public String toString() {
        return "CompanySection{" +
                "companies=" + companies +
                '}' + "\n";
    }
}
