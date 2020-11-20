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

    public List<Resume> getAllSorted() {
        storage.sort(RESUME_COMPARATOR_BY_NAME);
        return storage;
    }

    @Override
    protected void updateByIndex(Object searchKey, Resume resume) {
        storage.set((int) searchKey, resume);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected Resume getByIndex(Object searchKey) {
        return storage.get((int) searchKey);
    }

    @Override
    protected void removeElement(Object searchKey) {
        storage.remove((int) searchKey);
    }

    @Override
    protected void addElement(Object searchKey, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected boolean isSearchKeyExists(Object searchKey) {
        return (int) searchKey >= 0;
    }
}
