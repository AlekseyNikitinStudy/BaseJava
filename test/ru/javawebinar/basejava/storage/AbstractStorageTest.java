package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.ResumeTestData;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public abstract class AbstractStorageTest {
    protected Storage storage;

    private static final String UUID_1 = UUID.randomUUID().toString();
    private static final String UUID_2 = UUID.randomUUID().toString();
    private static final String UUID_3 = UUID.randomUUID().toString();
    private static final String UUID_4 = UUID.randomUUID().toString();

    protected static final Resume RESUME_1;
    protected static final Resume RESUME_2;
    protected static final Resume RESUME_3;
    protected static final Resume RESUME_4;

    protected static final File STORAGE_DIR = Config.get().getStorageDir();

    static {
        RESUME_1 = ResumeTestData.createResume(UUID_1, "Ivan Ivanov");
        RESUME_2 = ResumeTestData.createResume(UUID_2, "Petr Petrov");
        RESUME_3 = ResumeTestData.createResume(UUID_3, "Petr Petrov");
        RESUME_4 = ResumeTestData.createResume(UUID_4, "Taras Tarasov");
    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws ExistStorageException {
        storage.save(RESUME_1);
    }

    @Test
    public void update() {
        Resume updatedResume = ResumeTestData.createResume(UUID_1, "Pavel Pavlov");
        storage.update(updatedResume);
        Assert.assertEquals(updatedResume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExists() {
        Resume updatedResume = ResumeTestData.createResume(UUID_4, "");
        storage.update(updatedResume);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExists() throws StorageException {
        storage.delete(UUID_4);
    }

    @Test
    public void getAllSorted() {
        List<Resume> actualResumes = storage.getAllSorted();
        List<Resume> expectedResumes = new LinkedList<>();
        expectedResumes.add(RESUME_1);
        expectedResumes.add(RESUME_2);
        expectedResumes.add(RESUME_3);
        expectedResumes.sort(Resume.COMPARATOR_FULLNAME_UUID);

        Assert.assertEquals(expectedResumes, actualResumes);
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws StorageException {
        storage.get("uuid_notExists");
    }

    private void assertGet(Resume r) {
        Assert.assertEquals(r, storage.get(r.getUuid()));
    }

    private void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }
}