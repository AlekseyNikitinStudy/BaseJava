package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
    }

    @Test
    public void size() {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void save() {
        Resume savedResume = new Resume(UUID_4);
        storage.save(savedResume);
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(savedResume, storage.get(UUID_4));
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws StorageException {
        try {
            for (int i = 3; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume(String.valueOf(i)));
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume("Overflow"));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExists() throws ExistStorageException {
        storage.save(new Resume(UUID_1));
    }

    @Test
    public void update() {
        Resume updatedResume = new Resume(UUID_1);
        storage.update(updatedResume);
        Assert.assertEquals(updatedResume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExists() {
        Resume updatedResume = new Resume("uuid_notExists");
        storage.update(updatedResume);
    }

    @Test
    public void delete() {
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExists() throws StorageException {
        storage.delete("uuid_notExists");
    }

    @Test
    public void getAll() {
        Resume[] resumes = storage.getAll();
        Assert.assertEquals(resumes[0], storage.get(UUID_1));
        Assert.assertEquals(resumes[1], storage.get(UUID_2));
        Assert.assertEquals(resumes[2], storage.get(UUID_3));
    }

    @Test
    public void get() {
        Assert.assertEquals(new Resume(UUID_1), storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws StorageException {
        storage.get("uuid_notExists");
    }
}