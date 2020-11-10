package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    protected void addElementArray(Object searchKey, Resume resume) {
        storage[size] = resume;
    }

    protected void removeElementArray(Object searchKey) {
        System.arraycopy(storage, (int)searchKey + 1, storage, (int)searchKey,
                size - (int)searchKey + 1);
    }

    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
