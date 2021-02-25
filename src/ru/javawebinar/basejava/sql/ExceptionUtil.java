package ru.javawebinar.basejava.sql;

import org.postgresql.util.PSQLException;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;

import java.sql.SQLException;

public class ExceptionUtil {
    private static final String SQL_STATE_DUPLICATE = "23505"; // http://www.postgresql.org/docs/9.3/static/errcodes-appendix.html

    private ExceptionUtil() {
    }

    public static StorageException convertException(SQLException e) {
        if (e instanceof PSQLException) {
            if (e.getSQLState().equals(SQL_STATE_DUPLICATE)) {
                return new ExistStorageException(null);
            }
        }
        return new StorageException(e);
    }
}