package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    void addElementArray(int index, Resume resume) {
        int indexAdd = - index - 1;
        System.arraycopy(storage, indexAdd, storage, indexAdd + 1, size - indexAdd);
        storage[indexAdd] = resume;
    }

    void removeElementArray(int index) {
        int elementsMoved = size - index - 1;
        if (elementsMoved > 0) {
            System.arraycopy(storage, index + 1, storage, index, elementsMoved);
        }
    }

    int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
