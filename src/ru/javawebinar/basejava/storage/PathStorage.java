package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.strategy.SerializeStrategy;
import ru.javawebinar.basejava.storage.strategy.StreamSerializeStrategy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PathStorage extends AbstractStorage<Path> {
    private final Path storage;
    SerializeStrategy serializeStrategy;

    public PathStorage(String path, StreamSerializeStrategy serializeStrategy) {
        this.serializeStrategy = serializeStrategy;
        Path storage = Paths.get(path);
        storage.toFile().mkdir();
        System.out.println(path);
        Objects.requireNonNull(storage, "directory must not be null");
        if (!Files.isDirectory(storage) || !Files.isWritable(storage)) {
            throw new IllegalArgumentException(storage + " is not directory or is not writable");
        }

        this.storage = storage;
    }

    public void clear() {
        getResumeList("Path delete error").forEach(this::removeElement);
    }

    public int size() {
        return (int) getResumeList("Storage read error.").count();
    }

    @Override
    protected List<Resume> getAll() {
        return getResumeList("Storage read error.").map(this::getBySearchKey).collect(Collectors.toList());
    }

    private Stream<Path> getResumeList(String exceptionMessage) {
        try {
            return Files.list(storage);
        } catch (IOException e) {
            throw new StorageException(exceptionMessage, null);
        }
    }

    @Override
    protected boolean isSearchKeyExists(Path searchKey) {
        return Files.exists(searchKey);
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return storage.resolve(uuid);
    }

    @Override
    void updateBySearchKey(Path searchKey, Resume resume) {
        try {
            serializeStrategy.doWrite(resume, new BufferedOutputStream(Files.newOutputStream(searchKey)));
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
            return serializeStrategy.doRead(new BufferedInputStream(Files.newInputStream(searchKey)));
        } catch (IOException e) {
            throw new StorageException("File not found ", searchKey.toFile().getName());
        }
    }
}
