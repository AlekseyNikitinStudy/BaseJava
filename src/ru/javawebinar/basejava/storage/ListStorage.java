package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> storage = new ArrayList<>();

    public int size() {
        return storage.size();
    }

    public void clear() {
        storage.clear();
    }

    protected void updateByIndex(Object searchKey, Resume resume) {
        storage.set((int)searchKey, resume);
    }

    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    protected Resume getByIndex(Object searchKey) {
        return storage.get((int)searchKey);
    }

    protected void removeElement(Object searchKey) {
        storage.remove((int)searchKey);
    }

    protected void addElement(Object searchKey, Resume resume) {
        storage.add(resume);
    }

    protected boolean isSearchKeyExists(Object searchKey) {
        return (int) searchKey >= 0;
    }
}
