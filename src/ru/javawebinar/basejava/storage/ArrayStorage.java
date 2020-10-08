package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    void addElement(int index, Resume resume){
        storage[size] = resume;
    }

    void removeElement(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index + 1);
    }

    int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
