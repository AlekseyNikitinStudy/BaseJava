package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.time.LocalDate;
import java.util.*;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");

        Map<ContactType, String> contacts = new HashMap<>();
        contacts.put(ContactType.PHONE, "+7(921) 855-0482");
        contacts.put(ContactType.SKYPE, "grigory.kislin");
        contacts.put(ContactType.EMAIL, "gkislin@yandex.ru");
        contacts.put(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        contacts.put(ContactType.GITHUB, "https://github.com/gkislin");
        contacts.put(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        contacts.put(ContactType.HOMEPAGE, "http://gkislin.ru/");
        resume.setContacts(contacts);


        Map<SectionType, Section<?>> sections = new HashMap<>();
        Section<String> objective = new Section<>();
        objective.setNodes(Collections.singletonList("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
        sections.put(SectionType.OBJECTIVE, objective);


        Section<String> personal = new Section<>();
        personal.setNodes(Collections.singletonList("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
        sections.put(SectionType.PERSONAL, personal);


        Section<String> achievement = new Section<>();
        achievement.setNodes(Arrays.asList(
                "С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.",
                "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.",
                "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.",
                "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.",
                "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).",
                "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."));
        sections.put(SectionType.ACHIEVEMENT, achievement);


        Section<String> qualification = new Section<>();
        qualification.setNodes(Arrays.asList(
                "JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2.",
                "Version control: Subversion, Git, Mercury, ClearCase, Perforce.",
                "DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB.",
                "Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy, XML/XSD/XSLT, SQL, C/C++, Unix shell scripts.",
                "Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).",
                "Python: Django.",
                "JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js.",
                "Scala: SBT, Play2, Specs2, Anorm, Spray, Akka.",
                "Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.",
                "Инструменты: Maven + plugin development, Gradle, настройка Ngnix, администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.",
                "Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования.",
                "Родной русский, английский \"upper intermediate\"."));
        sections.put(SectionType.QUALIFICATIONS, qualification);


        Section<OrganizationSection> experience = new Section<>();
        experience.setNodes(Arrays.asList(
                new OrganizationSection("Java Online Projects",
                        Collections.singletonList(new ExperiencePeriod(LocalDate.of(2013, 10, 1),
                                LocalDate.now(),
                                "Автор проекта.",
                                "Создание, организация и проведение Java онлайн проектов и стажировок."))),

                new OrganizationSection("Wrike",
                        Collections.singletonList(new ExperiencePeriod(LocalDate.of(2014, 10, 1),
                                LocalDate.of(2016, 1, 1),
                                "Старший разработчик (backend)",
                                "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."))),

                new OrganizationSection("Yota",
                        Collections.singletonList(new ExperiencePeriod(LocalDate.of(2008, 6, 1),
                                LocalDate.of(2016, 1, 1),
                                "Ведущий специалист",
                                "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)"))),

                new OrganizationSection("Enkata",
                        Collections.singletonList(new ExperiencePeriod(LocalDate.of(2007, 3, 1),
                                LocalDate.of(2008, 6, 1),
                                "Разработчик ПО",
                                "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining)."))),

                new OrganizationSection("Siemens AG",
                        Collections.singletonList(new ExperiencePeriod(LocalDate.of(2005, 1, 1),
                                LocalDate.of(2007, 2, 1),
                                "Разработчик ПО",
                                "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix)."))),

                new OrganizationSection("Alcatel",
                        Collections.singletonList(new ExperiencePeriod(LocalDate.of(1997, 9, 1),
                                LocalDate.of(2005, 1, 1),
                                "Инженер по аппаратному и программному тестированию",
                                "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).")))
                ));
        sections.put(SectionType.EXPERIENCE, experience);


        Section<OrganizationSection> education = new Section<>();
        education.setNodes(Arrays.asList(
                new OrganizationSection("Coursera",
                        Collections.singletonList(new ExperiencePeriod(LocalDate.of(2013, 3, 1),
                                LocalDate.of(2013, 5, 1),
                                "\"Functional Programming Principles in Scala\" by Martin Odersky",
                                ""))),

                new OrganizationSection("Luxoft",
                        Collections.singletonList(new ExperiencePeriod(LocalDate.of(2011, 3, 1),
                                LocalDate.of(2011, 4, 1),
                                "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"",
                                ""))),

                new OrganizationSection("Siemens AG",
                        Collections.singletonList(new ExperiencePeriod(LocalDate.of(2005, 1, 1),
                                LocalDate.of(2005, 4, 1),
                                "3 месяца обучения мобильным IN сетям (Берлин)",
                                ""))),

                new OrganizationSection("Alcatel",
                        Collections.singletonList(new ExperiencePeriod(LocalDate.of(1997, 9, 1),
                                LocalDate.of(1998, 3, 1),
                                "6 месяцев обучения цифровым телефонным сетям (Москва)",
                                ""))),

                new OrganizationSection("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики",
                        Arrays.asList(
                                new ExperiencePeriod(LocalDate.of(1987, 9, 1),
                                        LocalDate.of(1993, 7, 1),
                                        "Инженер (программист Fortran, C)",
                                        ""),

                                new ExperiencePeriod(LocalDate.of(1993, 9, 1),
                                        LocalDate.of(1996, 7, 1),
                                        "Аспирантура (программист С, С++)",
                                        "")
                                )),

                new OrganizationSection("Заочная физико-техническая школа при МФТИ",
                        Collections.singletonList(new ExperiencePeriod(LocalDate.of(1984, 9, 1),
                                LocalDate.of(1987, 6, 1),
                                "Закончил с отличием",
                                "")))
                ));
        sections.put(SectionType.EDUCATION, education);


        resume.setSections(sections);


        System.out.println(resume.toString());
    }
}
