package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

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

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
