package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class MapResumeStorage extends AbstractMapStorage {

    @Override
    void updateByIndex(Object searchKey, Resume resume) {
        storage.put(((Resume) searchKey).getUuid(), resume);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void addElement(Object searchKey, Resume resume) {
        storage.put(((searchKey == null) ? resume : (Resume) searchKey).getUuid(), resume);
    }

    @Override
    protected void removeElement(Object searchKey) {
        storage.remove(((Resume) searchKey).getUuid());
    }

    @Override
    protected Resume getByIndex(Object searchKey) {
        return storage.get(((Resume) searchKey).getUuid());
    }

    @Override
    protected boolean isSearchKeyExists(Object searchKey) {
        return searchKey != null && storage.containsKey(((Resume) searchKey).getUuid());
    }

}
