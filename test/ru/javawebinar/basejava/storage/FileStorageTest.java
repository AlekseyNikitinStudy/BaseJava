package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.strategy.StreamSerializeStrategy;

import java.io.File;
import java.nio.file.Files;

public class FileStorageTest extends AbstractStorageTest {
    public FileStorageTest() {
        super(new FileStorage(new File("./resumes"), new StreamSerializeStrategy()));
    }
}