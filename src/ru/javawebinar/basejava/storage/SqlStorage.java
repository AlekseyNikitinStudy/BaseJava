package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.sql.SqlHelper;
import ru.javawebinar.basejava.sql.SqlTransaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SqlStorage implements Storage {
    private final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume");
    }

    @Override
    public void update(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
            String uuid = r.getUuid();
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name = ? " +
                                                                  "WHERE uuid = ?")) {
                ps.setString(1, r.getFullName());
                ps.setString(2, uuid);
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(uuid);
                }
            }

            deleteJoined("contact", uuid);

            deleteJoined("section", uuid);

            insertContacts(conn, r);

            insertSections(conn, r);

            return null;
        });
    }

    @Override
    public void save(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                ps.setString(1, r.getUuid());
                ps.setString(2, r.getFullName());
                ps.execute();
            }

            insertContacts(conn, r);

            insertSections(conn, r);

            return null;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume " +
                              "WHERE uuid = ?", ps -> {
            ps.setString(1, uuid);
            if (ps.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }

            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.transactionalExecute(conn -> {
            Resume resume = sqlHelper.execute("SELECT * FROM resume r " +
                                                  "WHERE r.uuid = ? ", ps -> {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();

                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                return new Resume(uuid, rs.getString("full_name"));
            });

            sqlHelper.execute("SELECT * FROM contact " +
                                  "WHERE resume_uuid = ?", ps -> {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addContact(rs, resume);
                }
                return null;
            });

            sqlHelper.execute("SELECT * FROM section " +
                                  "WHERE resume_uuid = ?", ps -> {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    addSection(rs, resume);
                }
                return null;
            });

            return resume;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.transactionalExecute(conn -> {
            Map<String, Resume> resumes = new LinkedHashMap<>();
            sqlHelper.execute("SELECT * FROM resume r " +
                                  "ORDER BY r.full_name, r.uuid", ps -> {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String uuid = rs.getString("uuid");
                    resumes.put(uuid, new Resume(uuid, rs.getString("full_name")));
                }
                return null;
            });

            sqlHelper.execute("SELECT * FROM contact", ps -> {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Resume r = resumes.get(rs.getString("resume_uuid"));
                    addContact(rs, r);
                }
                return null;
            });

            sqlHelper.execute("SELECT * FROM section", ps -> {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Resume r = resumes.get(rs.getString("resume_uuid"));
                    addSection(rs, r);
                }
                return null;
            });

            return new ArrayList<>(resumes.values());
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT count(*) AS count " +
                                     "FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();

            return rs.next() ? rs.getInt("count") : 0;
        });
    }

    private void deleteJoined(String table, String uuid) {
        sqlHelper.execute("DELETE FROM " + table + " " +
                              "WHERE resume_uuid = ?", ps -> {
            ps.setString(1, uuid);
            ps.execute();
            return null;
        });
    }

    private void insertContacts(Connection conn, Resume r) throws SQLException {
        if (r.getContacts().size() > 0) {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
                for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
                    ps.setString(1, r.getUuid());
                    ps.setString(2, e.getKey().name());
                    ps.setString(3, e.getValue());
                    ps.addBatch();
                }

                ps.executeBatch();
            }
        }
    }

    private void insertSections(Connection conn, Resume r) throws SQLException {
        if (r.getContacts().size() > 0) {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO section (resume_uuid, type, value) VALUES (?,?,?)")) {
                for (Map.Entry<SectionType, AbstractSection> e : r.getSections().entrySet()) {
                    ps.setString(1, r.getUuid());
                    ps.setString(2, e.getKey().name());
                    ps.setString(3, e.getValue().getStringForDb());
                    ps.addBatch();
                }

                ps.executeBatch();
            }
        }
    }

    private void addContact(ResultSet rs, Resume r) throws SQLException {
        String typeString = rs.getString("type");
        if (typeString != null) {
            ContactType type = ContactType.valueOf(typeString);
            String value = rs.getString("value");
            if (value != null) {
                r.addContact(type, value);
            }
        }
    }

    private void addSection(ResultSet rs, Resume r) throws SQLException {
        String typeString = rs.getString("type");
        if (typeString != null) {
            SectionType type = SectionType.valueOf(typeString);
            String value = rs.getString("value");
            if (value != null) {
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        r.addSection(type, new SingleLineSection(value));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> values = Stream.of(value.split("\n", -1))
                                .collect(Collectors.toList());
                        r.addSection(type, new BulletedListSection(values));
                        break;
                }
            }
        }
    }
}
