package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int STORAGE_LIMIT = 10_000;
    Resume[] storage = new Resume[STORAGE_LIMIT];
    int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    void addElement(int index, Resume resume) {
        if (size >= storage.length) {
            throw new StorageException("Storage overflow", resume.getUuid());
        }
        addElementArray(index, resume);
        size++;
    }

    void updateByIndex(int index, Resume resume) {
        storage[index] = resume;
    }

    void removeElement(int index, String uuid) {
        removeElementArray(index);
        storage[size - 1] = null;
        size--;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    Resume getByIndex(int index, String uuid) {
        return storage[index];
    }

    abstract void addElementArray(int index, Resume resume);

    abstract void removeElementArray(int index);
}
