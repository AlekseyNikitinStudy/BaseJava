package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.Comparator;

public class SortedArrayStorage extends AbstractArrayStorage {
    /*
        private static class ResumeComparator implements Comparator<Resume> {
            @Override
            public int compare(Resume o1, Resume o2) {
                return o1.getUuid().compareTo(o2.getUuid());
            }
        }
    */

    private static final Comparator<Resume> RESUME_COMPARATOR = (o1, o2) -> o1.getUuid().compareTo(o2.getUuid());

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
