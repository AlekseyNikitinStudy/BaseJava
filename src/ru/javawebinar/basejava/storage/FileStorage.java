package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.strategy.StreamSerializeStrategy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileStorage extends AbstractStorage<File> {
    private final File storage;
    StreamSerializeStrategy streamSerializeStrategy;

    public FileStorage(File storage, StreamSerializeStrategy streamSerializeStrategy) {

        this.streamSerializeStrategy = streamSerializeStrategy;
        if (storage == null) {
            throw new StorageException("Storage is null.", null);
        }

        storage.mkdir();

        if (!storage.isDirectory()) {
            throw new StorageException("Storage " + storage.getAbsolutePath() + " is not directory.", null);
        }

        this.storage = storage;
    }

    public void clear() {
        File[] files = storage.listFiles();
        if (files != null) {
            for (File file : files) {
                removeElement(file);
            }
        }
    }

    public int size() {
        String[] list = storage.list();
        if (list == null) {
            throw new StorageException("Storage read error.", null);
        }
        return list.length;
    }

    @Override
    protected boolean isSearchKeyExists(File searchKey) {
        return searchKey.exists();
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(storage, uuid);
    }

    @Override
    protected List<Resume> getAll() {
        File[] files = storage.listFiles();
        if (files == null) {
            throw new StorageException("Storage read error.", null);
        }
        List<Resume> list = new ArrayList<>(files.length);
        for (File file : files) {
            list.add(getBySearchKey(file));
        }
        return list;
    }

    @Override
    void updateBySearchKey(File searchKey, Resume resume) {
        try {
            streamSerializeStrategy.doWrite(resume, new BufferedOutputStream(new FileOutputStream(searchKey)));
        } catch (IOException e) {
            throw new StorageException("File write error", resume.getUuid());
        }
    }

    @Override
    void removeElement(File searchKey) {
        if (!searchKey.delete()) {
            throw new StorageException("File delete error.", searchKey.getName());
        }
    }

    @Override
    void addElement(File searchKey, Resume resume) {
        try {
            searchKey.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Couldn't create file ", searchKey.getAbsolutePath());
        }
        updateBySearchKey(searchKey, resume);
    }

    @Override
    Resume getBySearchKey(File searchKey) {
        try {
            return streamSerializeStrategy.doRead(new BufferedInputStream(new FileInputStream(searchKey)));
        } catch (IOException e) {
            throw new StorageException("File read error", searchKey.getName());
        }
    }
}
