package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected void addElement(Object searchKey, Resume resume) {
        if (size >= storage.length) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        addElementArray(searchKey, resume);
        size++;
    }

    protected void updateByIndex(Object searchKey, Resume resume) {
        storage[(int)searchKey] = resume;
    }

    protected void removeElement(Object searchKey) {
        removeElementArray(searchKey);
        storage[size - 1] = null;
        size--;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected Resume getByIndex(Object searchKey) {
        return storage[(int)searchKey];
    }

    protected boolean isSearchKeyExists(Object searchKey) {
        return (int) searchKey >= 0;
    }

    abstract void addElementArray(Object searchKey, Resume resume);

    abstract void removeElementArray(Object searchKey);
}
