package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class MapResumeStorage extends AbstractMapStorage {

    @Override
    void updateBySearchKey(Object searchKey, Resume resume) {
        storage.put(((Resume) searchKey).getUuid(), resume);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void addElement(Object searchKey, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void removeElement(Object searchKey) {
        storage.remove(((Resume) searchKey).getUuid());
    }

    @Override
    protected Resume getBySearchKey(Object searchKey) {
        return (Resume) searchKey;
    }

    @Override
    protected boolean isSearchKeyExists(Object searchKey) {
        return searchKey != null;
    }

}
