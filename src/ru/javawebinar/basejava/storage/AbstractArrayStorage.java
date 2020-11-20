package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

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

    public List<Resume> getAllSorted() {
        Arrays.sort(storage, 0, size, RESUME_COMPARATOR_BY_NAME);
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    @Override
    protected Resume getByIndex(Object searchKey) {
        return storage[(int) searchKey];
    }

    @Override
    protected void updateByIndex(Object searchKey, Resume resume) {
        storage[(int) searchKey] = resume;
    }

    @Override
    protected void addElement(Object searchKey, Resume resume) {
        if (size >= storage.length) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        addElementArray((int) searchKey, resume);
        size++;
    }

    @Override
    protected void removeElement(Object searchKey) {
        removeElementArray((int) searchKey);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected boolean isSearchKeyExists(Object searchKey) {
        return (int) searchKey >= 0;
    }

    abstract void addElementArray(int searchKey, Resume resume);

    abstract void removeElementArray(int searchKey);
}
