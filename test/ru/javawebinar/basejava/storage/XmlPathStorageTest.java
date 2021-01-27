package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.strategy.XmlSerializeStrategy;

public class XmlPathStorageTest extends AbstractStorageTest {

    public XmlPathStorageTest() {
        super(new PathStorage(STORAGE_DIR.getAbsolutePath(), new XmlSerializeStrategy()));
    }
}