package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
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

    @Override
    protected List<Resume> getAll() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    @Override
    protected Resume getBySearchKey(Integer searchKey) {
        return storage[searchKey];
    }

    @Override
    protected void updateBySearchKey(Integer searchKey, Resume resume) {
        storage[searchKey] = resume;
    }

    @Override
    protected void addElement(Integer searchKey, Resume resume) {
        if (size >= storage.length) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        addElementArray((int) searchKey, resume);
        size++;
    }

    @Override
    protected void removeElement(Integer searchKey) {
        removeElementArray(searchKey);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected boolean isSearchKeyExists(Integer searchKey) {
        return searchKey >= 0;
    }

    abstract void addElementArray(int searchKey, Resume resume);

    abstract void removeElementArray(int searchKey);
}
