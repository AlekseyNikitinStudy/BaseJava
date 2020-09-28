package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage implements Storage {
    private static final int STORAGE_LIMIT = 10_000;

    private Resume[] storage = new Resume[STORAGE_LIMIT];
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
                System.out.println("Резюме с UUID = " + resume.getUuid() + " есть в storage.");
            }
        } else {
            System.out.println("Переполнение storage.");
        }
    }

    public void update(Resume resume) {
        if ((index = getIndex(resume.getUuid())) >= 0) {
            storage[index] = resume;
        } else {
            System.out.println("Нет Резюме с UUID = " + resume.getUuid() + " в storage.");
        }
    }

    public Resume get(String uuid) {
        if ((index = getIndex(uuid)) >= 0) {
            return storage[index];
        } else {
            System.out.println("Нет Резюме с UUID = " + uuid + " в storage.");
            return null;
        }
    }

    public void delete(String uuid) {
        if ((index = getIndex(uuid)) >= 0) {
            if (size - index + 1 >= 0) {
                System.arraycopy(storage, index + 1, storage, index, size - index + 1);
                size--;
            }
        } else {
            System.out.println("Нет Резюме с UUID = " + uuid + " в storage.");
        }
    }

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
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
