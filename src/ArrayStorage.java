import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        int size = size();
        Arrays.fill(storage, 0, size, null);
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        int newIndex = size();
        if (newIndex < 10000) {
            storage[newIndex] = r;
        }
    }

    Resume get(String uuid) {
        int size = size();
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        int size = size();
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                if (size - i + 1 >= 0) {
                    System.arraycopy(storage, i + 1, storage, i + 1 - 1, size - i + 1);
                break;
                }
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size());
    }

    int size() {
        int i;
        for ( i = 0; i < 10000; i++) {
            if (storage[i] == null) {
                break;
            }
        }
        return i;
    }
}
