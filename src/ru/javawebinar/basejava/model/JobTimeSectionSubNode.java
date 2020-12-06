package ru.javawebinar.basejava.model;

public class JobTimeSectionSubNode extends AbstractTimeSectionSubNode{
    private String title;
    private String description;

    public JobTimeSectionSubNode(String from, String to, String title, String description) {
        super(from, to);
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "JobTimeSectionSubNode{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
