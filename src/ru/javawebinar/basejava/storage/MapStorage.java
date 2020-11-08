package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> storage = new HashMap<>();

    public int size() {
        return storage.size();
    }

    public void clear() {
        storage.clear();
    }

    void updateByIndex(int index, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    int getIndex(String uuid) {
        if (!storage.containsKey(uuid)) {
            return -1;
        }
        return 0;
    }

    void addElement(int index, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    void removeElement(int index, String uuid) {
        storage.remove(uuid);
    }

    Resume getByIndex(int index, String uuid) {
        return storage.get(uuid);
    }
}
