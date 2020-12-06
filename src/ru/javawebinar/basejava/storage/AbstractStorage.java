package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {
    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    protected static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName).
            thenComparing(Resume::getUuid);

    public void update(Resume resume) {
        LOG.info("Update " + resume);
        updateBySearchKey(getSearchKeyIfExists(resume.getUuid()), resume);
    }

    public void save(Resume resume) {
        LOG.info("Save " + resume);
        addElement(getSearchKeyIfNotExists(resume.getUuid()), resume);
    }

    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        removeElement(getSearchKeyIfExists(uuid));
    }

    public Resume get(String uuid) {
        LOG.info("Get " + uuid);
        return getBySearchKey(getSearchKeyIfExists(uuid));
    }

    private SK getSearchKeyIfExists(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isSearchKeyExists(searchKey)) {
            return searchKey;
        }
        LOG.warning("Resume " + uuid + " not exists");
        throw new NotExistStorageException(uuid);
    }

    private SK getSearchKeyIfNotExists(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isSearchKeyExists(searchKey)) {
            return searchKey;
        }
        LOG.warning("Resume " + uuid + " already exists");
        throw new ExistStorageException(uuid);
    }

    public List<Resume> getAllSorted() {
        LOG.info("GetAllSorted");
        List<Resume> sortedStorage = getAll();
        sortedStorage.sort(RESUME_COMPARATOR);
        return sortedStorage;
    }

    abstract List<Resume> getAll();

    abstract SK getSearchKey(String uuid);

    abstract void updateBySearchKey(SK searchKey, Resume resume);

    abstract void addElement(SK searchKey, Resume resume);

    abstract void removeElement(SK searchKey);

    abstract Resume getBySearchKey(SK searchKey);

    abstract boolean isSearchKeyExists(SK searchKey);
}
