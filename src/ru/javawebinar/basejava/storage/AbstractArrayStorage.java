package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    Resume[] storage = new Resume[STORAGE_LIMIT];
    int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    boolean isOverflow() {
        return size >= storage.length;
    }

    void updateByIndex(int index, Resume resume) {
        storage[index] = resume;
    }

    protected void fillDeleted() {
        storage[size - 1] = null;
        size--;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    Resume getByIndex(int index) {
        return storage[index];
    }

}
