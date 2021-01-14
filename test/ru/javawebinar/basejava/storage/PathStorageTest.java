package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.strategy.StreamSerializeStrategy;

public class PathStorageTest extends AbstractStorageTest {
    public PathStorageTest() {
        super(new PathStorage(PATH_STORAGE_DIR_NAME, new StreamSerializeStrategy()));
    }
}