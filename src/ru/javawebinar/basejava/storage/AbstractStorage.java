package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10_000;

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            updateByIndex(index, resume);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (!isOverflow()) {
            if (index < 0) {
                addElement(index, resume);
            } else {
                throw new ExistStorageException(resume.getUuid());
            }
        } else {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            removeElement(index);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            return getByIndex(index);
        }

        throw new NotExistStorageException(uuid);
    }

    abstract int getIndex(String uuid);

    abstract void updateByIndex(int index, Resume resume);

    abstract boolean isOverflow();

    abstract void addElement(int index, Resume resume);

    abstract void removeElement(int index);

    abstract Resume getByIndex(int index);
}
