package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    private List<Resume> storage = new ArrayList<>();

    public int size() {
        return storage.size();
    }

    public void clear() {
        storage.clear();
    }

    @Override
    protected List<Resume> getAll() {
        return storage;
    }

    @Override
    protected void updateBySearchKey(Integer searchKey, Resume resume) {
        storage.set(searchKey, resume);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected Resume getBySearchKey(Integer searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected void removeElement(Integer searchKey) {
        storage.remove((int) searchKey);
    }

    @Override
    protected void addElement(Integer searchKey, Resume resume) {
        storage.add(resume);
    }

    @Override
    protected boolean isSearchKeyExists(Integer searchKey) {
        return searchKey >= 0;
    }
}
