package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    protected void addElement(int index, Resume resume){
        int indexAdd = - index - 1;
        System.arraycopy(storage, indexAdd, storage, indexAdd + 1, size - indexAdd);
        storage[indexAdd] = resume;
    }

    protected void removeElement(int index) {
        int elementsMoved = size - index - 1;
        if (elementsMoved > 0) {
            System.arraycopy(storage, index + 1, storage, index, elementsMoved);
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}