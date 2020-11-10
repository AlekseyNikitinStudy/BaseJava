package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    void addElementArray(Object searchKey, Resume resume) {
        int indexAdd = - (int)searchKey - 1;
        System.arraycopy(storage, indexAdd, storage, indexAdd + 1, size - indexAdd);
        storage[indexAdd] = resume;
    }

    void removeElementArray(Object searchKey) {
        int elementsMoved = size - (int)searchKey - 1;
        if (elementsMoved > 0) {
            System.arraycopy(storage, (int)searchKey + 1, storage, (int)searchKey, elementsMoved);
        }
    }

    Object getSearchKey(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
