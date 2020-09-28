package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;
    protected int index;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        if ((index = getIndex(uuid)) >= 0) {
            return storage[index];
        } else {
            System.out.println("Нет Резюме с UUID = " + uuid + " в storage.");
            return null;
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract int getIndex(String uuid);
}
