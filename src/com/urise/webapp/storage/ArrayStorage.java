package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size < storage.length) {
            if (get(r.getUuid()) == null) {
                storage[size] = r;
                size++;
            } else {
                System.out.println("Резюме с таким UUID есть в ArrayStorage.");
            }
        } else {
            System.out.println("Переполнение ArrayStorage.");
        }
    }

    public void update(Resume r) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(r.getUuid())) {
                storage[i] = r;
                return;
            }
        }
        System.out.println("Нет Резюме с таким UUID в ArrayStorage.");
    }

    public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        System.out.println("Нет Резюме с таким UUID в ArrayStorage.");
        return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                if (size - i + 1 >= 0) {
                    System.arraycopy(storage, i + 1, storage, i, size - i + 1);
                    size--;
                    return;
                }
            }
        }
        System.out.println("Нет Резюме с таким UUID в ArrayStorage.");
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
