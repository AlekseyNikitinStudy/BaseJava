package ru.javawebinar.basejava.storage.strategy;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class DataStreamSerializeStrategy implements SerializeStrategy {
    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);

            readMap(dis,
                    () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));

            readMap(dis,
                    () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.addSection(sectionType, readSection(dis, sectionType));
            });

            return resume;
        }
    }

    private void readMap(DataInputStream dis, MapReader mapReader) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            mapReader.read();
        }
    }

    private AbstractSection readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        switch(sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                return new SingleLineSection(dis.readUTF());
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                return new BulletedListSection(readList(dis, dis::readUTF));
            case EDUCATION:
            case EXPERIENCE:
                return new CompanySection(readList(dis,
                                () -> new Company(dis.readUTF(), dis.readUTF(), readList(dis,
                                        () -> new Company.Period(readLocalDate(dis), readLocalDate(dis), dis.readUTF(), dis.readUTF())))));
            default:
                throw new IllegalStateException();
        }
    }

    private <T> List<T> readList(DataInputStream dis, EntryReader<T> entryReader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(entryReader.read());
        }
        return list;
    }

    private LocalDate readLocalDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), dis.readInt());
    }

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());

            writeCollection(dos, resume.getContacts().entrySet(),
                    contactEntry -> {
                dos.writeUTF(contactEntry.getKey().name());
                dos.writeUTF(contactEntry.getValue());
            });

            writeCollection(dos, resume.getSections().entrySet(),
                    sectionEntry -> {
                SectionType type = sectionEntry.getKey();
                AbstractSection section = sectionEntry.getValue();
                dos.writeUTF(type.name());
                switch(type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((SingleLineSection)section).getValue());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeCollection(dos, ((BulletedListSection)section).getValues(), dos::writeUTF);
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        writeCollection(dos, ((CompanySection)section).getCompanies(),
                                companyEntry -> {
                            Link homePage = companyEntry.getHomePage();
                            dos.writeUTF(homePage.getName());
                            dos.writeUTF(homePage.getUrl());
                            writeCollection(dos, companyEntry.getPeriods(),
                                    periodEntry -> {
                                writeLocalDate(dos, periodEntry.getStart());
                                writeLocalDate(dos, periodEntry.getEnd());
                                dos.writeUTF(periodEntry.getName());
                                dos.writeUTF(periodEntry.getDescription());
                            });
                        });
                        break;
                }
            });
        }
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, EntryWriter<T> entryWriter) throws IOException {
        dos.writeInt(collection.size());
        for (T entry : collection) {
            entryWriter.write(entry);
        }
    }

    private interface EntryWriter<T> {
        void write(T t) throws IOException;
    }

    private void writeLocalDate(DataOutputStream dos, LocalDate localDate) throws IOException {
        dos.writeInt(localDate.getYear());
        dos.writeInt(localDate.getMonth().getValue());
        dos.writeInt(localDate.getDayOfMonth());
    }

    public interface EntryReader<T> {
        T read() throws IOException;
    }

    public interface MapReader {
        void read() throws IOException;
    }
}
