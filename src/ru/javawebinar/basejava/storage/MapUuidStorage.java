package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class MapUuidStorage extends AbstractMapStorage {

    @Override
    void updateBySearchKey(Object searchKey, Resume resume) {
        storage.put((String) searchKey, resume);
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
    protected Resume getBySearchKey(Object searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected boolean isSearchKeyExists(Object searchKey) {
        return storage.containsKey(searchKey);
    }
}
