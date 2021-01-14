package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.strategy.StreamSerializeStrategy;

import java.io.File;
import java.nio.file.Files;

public class FileStorageTest extends AbstractStorageTest {
    public FileStorageTest() {
        super(new FileStorage(FILE_STORAGE_DIR, new StreamSerializeStrategy()));
    }
}