package ru.javawebinar.basejava.model;

import java.util.*;

public class Resume {

    // Unique identifier
    private String uuid;

    private String fullName;

    private List<Contact> contacts = new LinkedList<>();

    private Map<SectionType, Section> sections = new HashMap<>();

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

    public List<Contact> getContacts() {
        return contacts;
    }

    public Map<SectionType, Section> getSections() {
        return sections;
    }

    public void addSection(SectionType type, Object node) {
        Section section = sections.get(type);
        if (section == null) {
            section = new Section(type);
        }
        section.add(node);
        sections.put(type, section);
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
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
        return "Resume{" +
                "uuid='" + uuid + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
