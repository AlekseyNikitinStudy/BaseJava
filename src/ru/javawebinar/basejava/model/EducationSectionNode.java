package ru.javawebinar.basejava.model;

import java.util.List;

public class EducationSectionNode {
    private String instituteName;
    List<EducationTimeSectionSubNode> subNodes;

    public EducationSectionNode(String instituteName, List<EducationTimeSectionSubNode> subNodes) {
        this.instituteName = instituteName;
        this.subNodes = subNodes;
    }

    @Override
    public String toString() {
        return "EducationSectionNode{" +
                "instituteName='" + instituteName + '\'' +
                ", subNodes=" + subNodes +
                '}';
    }
}
