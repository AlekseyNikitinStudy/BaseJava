package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class MapUuidStorage extends AbstractMapStorage<String> {

    @Override
    void updateBySearchKey(String searchKey, Resume resume) {
        storage.put(searchKey, resume);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected void addElement(String searchKey, Resume resume) {
        storage.put(searchKey, resume);
    }

    @Override
    protected void removeElement(String searchKey) {
        storage.remove(searchKey);
    }

    @Override
    protected Resume getBySearchKey(String searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected boolean isSearchKeyExists(String searchKey) {
        return storage.containsKey(searchKey);
    }
}
