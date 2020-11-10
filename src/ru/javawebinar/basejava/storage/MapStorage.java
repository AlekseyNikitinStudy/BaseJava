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

    void updateByIndex(Object searchKey, Resume resume) {
        storage.put((String)searchKey, resume);
    }

    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    protected void addElement(Object searchKey, Resume resume) {
        storage.put((String)searchKey, resume);
    }

    protected void removeElement(Object searchKey) {
        storage.remove(searchKey);
    }

    protected Resume getByIndex(Object searchKey) {
        return storage.get(searchKey);
    }

    protected boolean isSearchKeyExists(Object searchKey) {
        return storage.containsKey(searchKey);
    }
}
