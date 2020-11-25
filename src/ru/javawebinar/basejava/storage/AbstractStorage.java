package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {
    protected static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName).
            thenComparing(Resume::getUuid);

    public void update(Resume resume) {
        updateBySearchKey(getSearchKeyIfExists(resume.getUuid()), resume);
    }

    public void save(Resume resume) {
        addElement(getSearchKeyIfNotExists(resume.getUuid()), resume);
    }

    public void delete(String uuid) {
        removeElement(getSearchKeyIfExists(uuid));
    }

    public Resume get(String uuid) {
        return getBySearchKey(getSearchKeyIfExists(uuid));
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

    public List<Resume> getAllSorted() {
        List<Resume> sortedStorage = getAll();
        sortedStorage.sort(RESUME_COMPARATOR);
        return sortedStorage;
    }

    abstract List<Resume> getAll();

    abstract Object getSearchKey(String uuid);

    abstract void updateBySearchKey(Object searchKey, Resume resume);

    abstract void addElement(Object searchKey, Resume resume);

    abstract void removeElement(Object searchKey);

    abstract Resume getBySearchKey(Object searchKey);

    abstract boolean isSearchKeyExists(Object searchKey);
}
