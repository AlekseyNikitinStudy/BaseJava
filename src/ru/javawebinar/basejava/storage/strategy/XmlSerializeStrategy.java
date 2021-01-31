package ru.javawebinar.basejava.storage.strategy;

import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.util.XmlParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class XmlSerializeStrategy implements SerializeStrategy {
    private XmlParser xmlParser;

    public XmlSerializeStrategy() {
        xmlParser = new XmlParser(
                Resume.class, Company.class, Company.Period.class,
                CompanySection.class, SingleLineSection.class, BulletedListSection.class);
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (Reader r = new InputStreamReader(is, StandardCharsets.UTF_8)) {
            return xmlParser.unmarshall(r);
        }
    }

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (Writer w = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            xmlParser.marshall(resume, w);
        }
    }
}
