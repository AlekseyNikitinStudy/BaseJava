package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size;
    private int index;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        if (size < storage.length) {
            if (getIndex(resume.getUuid()) < 0) {
                storage[size] = resume;
                size++;
            } else {
                System.out.println("Резюме с UUID = " + resume.getUuid() + " есть в ArrayStorage.");
            }
        } else {
            System.out.println("Переполнение ArrayStorage.");
        }
    }

    public void update(Resume resume) {
        if ((index = getIndex(resume.getUuid())) >= 0) {
            storage[index] = resume;
        }
    }

    public Resume get(String uuid) {
        if ((index = getIndex(uuid)) >= 0) {
            return storage[index];
        } else {
            return null;
        }
    }

    public void delete(String uuid) {
        if ((index = getIndex(uuid)) >= 0) {
            if (size - index + 1 >= 0) {
                System.arraycopy(storage, index + 1, storage, index, size - index + 1);
                size--;
            }
        }
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        System.out.println("Нет Резюме с UUID = " + uuid + " в ArrayStorage.");
        return -1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
