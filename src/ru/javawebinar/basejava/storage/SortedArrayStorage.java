package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void addElementArray(int searchKey, Resume resume) {
        int indexAdd = - searchKey - 1;
        System.arraycopy(storage, indexAdd, storage, indexAdd + 1, size - indexAdd);
        storage[indexAdd] = resume;
    }

    @Override
    protected void removeElementArray(int searchKey) {
        int elementsMoved = size - searchKey - 1;
        if (elementsMoved > 0) {
            System.arraycopy(storage, searchKey + 1, storage, searchKey, elementsMoved);
        }
    }

    @Override
    protected Object getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
