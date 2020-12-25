package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;

public class ObjectStreamPathStorage extends AbstractPathStorage {

    public ObjectStreamPathStorage(String storageFilename) {
        super(storageFilename);
    }

    @Override
    public void doWrite(Resume resume, OutputStream os) throws  IOException {
        try (ObjectOutputStream stream = new ObjectOutputStream(os)) {
            stream.writeObject(resume);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (ObjectInputStream stream = new ObjectInputStream(is)) {
            return (Resume)stream.readObject();
        } catch (ClassNotFoundException e) {
            throw new StorageException("File read error.", null);
        }
    }
}
