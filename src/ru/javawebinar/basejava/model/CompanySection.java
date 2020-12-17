package ru.javawebinar.basejava.model;

import java.util.List;

public class CompanySection extends AbstractSection {
    private List<Company> companies;

    public CompanySection(List<Company> companies) {
        this.companies = companies;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    @Override
    public String toString() {
        return "CompanySection{" +
                "companies=" + companies +
                '}' + "\n";
    }
}
