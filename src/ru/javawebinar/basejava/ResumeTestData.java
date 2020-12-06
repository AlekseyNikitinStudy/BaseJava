package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.*;

import java.util.Arrays;
import java.util.Collections;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");

        resume.addContact(new Contact("Тел.", "+7(921) 855-0482"));

        resume.addContact(new Contact("Skype", "grigory.kislin"));

        resume.addContact(new Contact("Почта", "gkislin@yandex.ru"));

        resume.addContact(new Contact("Профиль LinkedIn", "https://www.linkedin.com/in/gkislin"));

        resume.addContact(new Contact("Профиль GitHub", "https://github.com/gkislin"));

        resume.addContact(new Contact("Профиль StackOverflow", "https://stackoverflow.com/users/548473"));

        resume.addContact(new Contact("Домашняя страница", "http://gkislin.ru/"));

        resume.addSection(SectionType.OBJECTIVE,
                "Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");

        resume.addSection(SectionType.PERSONAL,
                "Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");

        resume.addSection(SectionType.ACHIEVEMENT,
                Arrays.asList("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.",
                        "Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.",
                        "Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.",
                        "Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.",
                        "Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).",
                        "Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа."));

        resume.addSection(SectionType.QUALIFICATIONS,
                Arrays.asList("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2.",
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

        resume.addSection(SectionType.EXPERIENCE, new JobSectionNode("Java Online Projects",
                Collections.singletonList(new JobTimeSectionSubNode("10/2013",
                        "now",
                        "Автор проекта.",
                        "Создание, организация и проведение Java онлайн проектов и стажировок."))));

        resume.addSection(SectionType.EXPERIENCE, new JobSectionNode("Wrike",
                Collections.singletonList(new JobTimeSectionSubNode("10/2014",
                        "01/2016",
                        "Старший разработчик (backend)",
                        "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."))));

        resume.addSection(SectionType.EXPERIENCE, new JobSectionNode("Yota",
                Collections.singletonList(new JobTimeSectionSubNode("06/2008",
                        "12/2010",
                        "Ведущий специалист",
                        "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)"))));

        resume.addSection(SectionType.EXPERIENCE, new JobSectionNode("Enkata",
                Collections.singletonList(new JobTimeSectionSubNode("03/2007",
                        "06/2008",
                        "Разработчик ПО",
                        "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining)."))));

        resume.addSection(SectionType.EXPERIENCE, new JobSectionNode("Siemens AG",
                Collections.singletonList(new JobTimeSectionSubNode("01/2005",
                        "02/2007",
                        "Разработчик ПО",
                        "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix)."))));

        resume.addSection(SectionType.EXPERIENCE, new JobSectionNode("Alcatel",
                Collections.singletonList(new JobTimeSectionSubNode("09/1997",
                        "01/2005",
                        "Инженер по аппаратному и программному тестированию",
                        "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM)."))));

        resume.addSection(SectionType.EDUCATION, new EducationSectionNode("Coursera",
                Collections.singletonList(new EducationTimeSectionSubNode("03/2013",
                        "05/2013",
                        "\"Functional Programming Principles in Scala\" by Martin Odersky"))));

        resume.addSection(SectionType.EDUCATION, new EducationSectionNode("Luxoft",
                Collections.singletonList(new EducationTimeSectionSubNode("03/2011",
                        "04/2011",
                        "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\""))));

        resume.addSection(SectionType.EDUCATION, new EducationSectionNode("Siemens AG",
                Collections.singletonList(new EducationTimeSectionSubNode("01/2005",
                        "04/2005",
                        "3 месяца обучения мобильным IN сетям (Берлин)"))));

        resume.addSection(SectionType.EDUCATION, new EducationSectionNode("Alcatel",
                Collections.singletonList(new EducationTimeSectionSubNode("09/1997",
                        "03/1998",
                        "6 месяцев обучения цифровым телефонным сетям (Москва)"))));

        resume.addSection(SectionType.EDUCATION, new EducationSectionNode("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики",
                Arrays.asList(new EducationTimeSectionSubNode("09/1987",
                        "07/1993",
                        "Инженер (программист Fortran, C)"),
                        new EducationTimeSectionSubNode("09/1993",
                                "07/1996",
                                "Аспирантура (программист С, С++)"))));

        resume.addSection(SectionType.EDUCATION, new EducationSectionNode("Заочная физико-техническая школа при МФТИ",
                Collections.singletonList(new EducationTimeSectionSubNode("09/1984",
                        "06/1987",
                        "Закончил с отличием"))));

        System.out.println(resume.toString());
        System.out.println(resume.getContacts());
        resume.getSections().values().forEach(section -> {
            System.out.println(section.toString());
        });
    }
}
