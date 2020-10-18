package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.lang.reflect.Field;

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
    public void update() {
        Resume updatedResume = new Resume(UUID_1);
        storage.update(updatedResume);
        Assert.assertEquals(updatedResume, storage.get(UUID_1));
    }

    @Test
    public void getAll() {
        Resume[] resumes = storage.getAll();
        Assert.assertEquals(storage.get(UUID_1), resumes[0]);
        Assert.assertEquals(storage.get(UUID_2), resumes[1]);
        Assert.assertEquals(storage.get(UUID_3), resumes[2]);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws StorageException, IllegalAccessException {
        Field field = storage.getClass().getSuperclass().getDeclaredFields()[0];
        field.setAccessible(true);
        int storageLimit = field.getInt(storage);
        for (int i = 0; i < storageLimit - 3; i++) {
            storage.save(new Resume());
        }
        storage.save(new Resume());
    }

    @Test
    public void save() {
        storage.save(new Resume(UUID_4));
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(UUID_4, storage.get(UUID_4).getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws StorageException {
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
        storage.get(UUID_1);
    }

    @Test
    public void get() {
        storage.get(UUID_1);
        storage.get(UUID_2);
        storage.get(UUID_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws StorageException {
        storage.get("dummy");
    }
}