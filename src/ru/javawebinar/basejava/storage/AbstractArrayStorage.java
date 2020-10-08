package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    private static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        int index;
        if (size < storage.length) {
            if ((index = getIndex(resume.getUuid())) < 0) {
                addElement(index, resume);
                size++;
            } else {
                System.out.println("Резюме с UUID = " + resume.getUuid() + " есть в storage.");
            }
        } else {
            System.out.println("Переполнение storage.");
        }
    }

    public void update(Resume resume) {
        int index;
        if ((index = getIndex(resume.getUuid())) >= 0) {
            storage[index] = resume;
        } else {
            System.out.println("Нет Резюме с UUID = " + resume.getUuid() + " в storage.");
        }
    }

    public void delete(String uuid) {
        int index;
        if ((index = getIndex(uuid)) >= 0) {
            removeElement(index);
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Нет Резюме с UUID = " + uuid + " в storage.");
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public Resume get(String uuid) {
        int index;
        if ((index = getIndex(uuid)) >= 0) {
            return storage[index];
        }

        System.out.println("Нет Резюме с UUID = " + uuid + " в storage.");
        return null;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void addElement(int index, Resume resume);

    protected abstract void removeElement(int index);
}
