package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.strategy.StreamSerializeStrategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PathStorage extends AbstractStorage<Path> {
    private final Path storage;
    StreamSerializeStrategy streamSerializeStrategy;

    public PathStorage(String path, StreamSerializeStrategy streamSerializeStrategy) {
        this.streamSerializeStrategy = streamSerializeStrategy;
        Path storage = Paths.get(path);
        System.out.println(path);
        Objects.requireNonNull(storage, "directory must not be null");
        if (!Files.isDirectory(storage) || !Files.isWritable(storage)) {
            throw new IllegalArgumentException(storage + " is not directory or is not writable");
        }

        this.storage = storage;
        clear();
    }

    public void clear() {
        try {
            Files.list(storage).forEach(this::removeElement);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }

    public int size() {
        try {
            return (int) Files.list(storage).count();
        } catch (IOException e) {
            throw new StorageException("Storage read error.", null);
        }
    }

    @Override
    protected boolean isSearchKeyExists(Path searchKey) {
        return Files.exists(searchKey);
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return Paths.get(storage.getFileName().toString(), uuid);
        //return new File(storage, uuid);
    }

    @Override
    protected List<Resume> getAll() {
        try {
            return Files.list(storage).map(this::getBySearchKey).collect(Collectors.toList());
        } catch (IOException e) {
            throw new StorageException("Storage read error.", null);
        }
    }

    @Override
    void updateBySearchKey(Path searchKey, Resume resume) {
        try {
            streamSerializeStrategy.doWrite(resume, new BufferedOutputStream(new FileOutputStream(searchKey.toFile())));
        } catch (IOException e) {
            throw new StorageException("File write error", resume.getUuid());
        }
    }

    @Override
    void removeElement(Path searchKey) {
        try {
            Files.delete(searchKey);
        } catch (IOException exception) {
            throw new StorageException("File delete error.", searchKey.toFile().getName());
        }
    }

    @Override
    void addElement(Path searchKey, Resume resume) {
        try {
            Files.createFile(searchKey);
        } catch (IOException e) {
            throw new StorageException("Couldn't create file ", searchKey.toFile().getName());
        }
        updateBySearchKey(searchKey, resume);
    }

    @Override
    Resume getBySearchKey(Path searchKey) {
        try {
            return streamSerializeStrategy.doRead(new BufferedInputStream(new FileInputStream(searchKey.toFile())));
        } catch (IOException e) {
            throw new StorageException("File not found ", searchKey.toFile().getName());
        }
    }
}
