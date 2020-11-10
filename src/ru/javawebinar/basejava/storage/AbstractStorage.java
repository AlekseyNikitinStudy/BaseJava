package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    public void update(Resume resume) {
        updateByIndex(getSearchKeyIfExists(resume.getUuid()), resume);
    }

    public void save(Resume resume) {
        addElement(getSearchKeyIfNotExists(resume.getUuid()), resume);
    }

    public void delete(String uuid) {
        removeElement(getSearchKeyIfExists(uuid));
    }

    public Resume get(String uuid) {
        return getByIndex(getSearchKeyIfExists(uuid));
    }

    private Object getSearchKeyIfExists(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isSearchKeyExists(searchKey)) {
            return searchKey;
        }
        throw new NotExistStorageException(uuid);
    }

    private Object getSearchKeyIfNotExists(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isSearchKeyExists(searchKey)) {
            return searchKey;
        }
        throw new ExistStorageException(uuid);
    }

    abstract Object getSearchKey(String uuid);

    abstract void updateByIndex(Object searchKey, Resume resume);

    abstract void addElement(Object searchKey, Resume resume);

    abstract void removeElement(Object searchKey);

    abstract Resume getByIndex(Object searchKey);

    abstract boolean isSearchKeyExists(Object searchKey);
}
