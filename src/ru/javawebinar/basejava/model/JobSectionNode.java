package ru.javawebinar.basejava.model;

import java.util.List;

public class JobSectionNode {
    private String companyName;
    List<JobTimeSectionSubNode> subNodes;

    public JobSectionNode(String companyName, List<JobTimeSectionSubNode> subNodes) {
        this.companyName = companyName;
        this.subNodes = subNodes;
    }

    @Override
    public String toString() {
        return "JobSectionNode{" +
                "companyName='" + companyName + '\'' +
                ", subNodes=" + subNodes +
                '}';
    }
}
