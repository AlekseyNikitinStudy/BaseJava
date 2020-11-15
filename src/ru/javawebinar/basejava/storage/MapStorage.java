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

    @Override
    void updateByIndex(Object searchKey, Resume resume) {
        storage.put((String) searchKey, resume);
    }

    public Resume[] getAll() {
        return storage.values().toArray(new Resume[0]);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void addElement(Object searchKey, Resume resume) {
        storage.put((String) searchKey, resume);
    }

    @Override
    protected void removeElement(Object searchKey) {
        storage.remove(searchKey);
    }

    @Override
    protected Resume getByIndex(Object searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected boolean isSearchKeyExists(Object searchKey) {
        return storage.containsKey(searchKey);
    }
}
