package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage {
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

    @Override
    protected List<Resume> getAll() {
        return new ArrayList<>(storage.values());
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
