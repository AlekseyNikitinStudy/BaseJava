package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void addElementArray(int searchKey, Resume resume) {
        storage[size] = resume;
    }

    @Override
    protected void removeElementArray(int searchKey) {
        System.arraycopy(storage, searchKey + 1, storage, searchKey,
                size - searchKey + 1);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
