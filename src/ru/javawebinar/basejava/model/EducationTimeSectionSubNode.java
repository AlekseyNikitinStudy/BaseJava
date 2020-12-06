package ru.javawebinar.basejava.model;

public class EducationTimeSectionSubNode extends AbstractTimeSectionSubNode {
    private String courseName;

    public EducationTimeSectionSubNode(String from, String to, String courseName) {
        super(from, to);
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "EducationTimeSectionSubNode{" +
                "courseName='" + courseName + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
