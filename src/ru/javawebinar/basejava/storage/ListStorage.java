package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> storage = new ArrayList<>();

    public int size() {
        return storage.size();
    }

    public void clear() {
        storage.clear();
    }

    void updateByIndex(int index, Resume resume) {
        storage.set(index, resume);
    }

    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    int getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    Resume getByIndex(int index) {
        return storage.get(index);
    }

    void removeElement(int index) {
        storage.remove(index);
    }

    void addElement(int index, Resume resume) {
        storage.add(resume);
    }
}
