package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Test;
import ru.javawebinar.basejava.model.Resume;

public class MapStorageTest extends AbstractStorageTest {
    public MapStorageTest() {
        super(new MapStorage());
    }

    @Override
    @Test
    public void getAll() {
        Resume[] array = storage.getAll();
        Assert.assertEquals(3, array.length);
        Assert.assertEquals(RESUME_1, storage.get(RESUME_1.getUuid()));
        Assert.assertEquals(RESUME_2, storage.get(RESUME_2.getUuid()));
        Assert.assertEquals(RESUME_3, storage.get(RESUME_3.getUuid()));
    }
}
