package ru.javawebinar.basejava.storage;

import java.io.File;

public class AbstractFileStorageTest extends AbstractStorageTest {
    public AbstractFileStorageTest() {
        super(new AbstractFileStorage(new File("./resumes")));
    }
}
