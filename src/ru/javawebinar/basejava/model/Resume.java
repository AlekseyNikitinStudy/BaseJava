package ru.javawebinar.basejava.model;

import java.util.*;

public class Resume {

    // Unique identifier
    private String uuid;

    private String fullName;

    private Map<ContactType, String> contacts;

    private Map<SectionType, Section<?>> sections;

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public void setContacts(Map<ContactType, String> contacts) {
        this.contacts = contacts;
    }

    public Map<SectionType, Section<?>> getSections() {
        return sections;
    }

    public void setSections(Map<SectionType, Section<?>> sections) {
        this.sections = sections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resume)) return false;

        Resume resume = (Resume) o;

        if (!uuid.equals(resume.uuid)) return false;
        return fullName != null ? fullName.equals(resume.fullName) : resume.fullName == null;
    }

    @Override
    public int hashCode() {
        int result = uuid.hashCode();
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return fullName + "\n"
                + contacts.toString() + "\n"
                + sections.toString() + "\n";
    }
}
