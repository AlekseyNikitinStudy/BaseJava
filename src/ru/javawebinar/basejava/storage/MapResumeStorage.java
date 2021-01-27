package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class MapResumeStorage extends AbstractMapStorage<Resume> {

    @Override
    void updateBySearchKey(Resume searchKey, Resume resume) {
        storage.put((searchKey).getUuid(), resume);
    }

    @Override
    protected Resume getSearchKey(String uuid) {
        return storage.get(uuid);
    }

    @Override
    protected void addElement(Resume searchKey, Resume resume) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void removeElement(Resume searchKey) {
        storage.remove((searchKey).getUuid());
    }

    @Override
    protected Resume getBySearchKey(Resume searchKey) {
        return searchKey;
    }

    @Override
    protected boolean isSearchKeyExists(Resume searchKey) {
        return searchKey != null;
    }

}
