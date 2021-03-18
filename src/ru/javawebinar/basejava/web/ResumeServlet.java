package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.storage.Storage;
import ru.javawebinar.basejava.util.DateUtil;
import ru.javawebinar.basejava.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ResumeServlet extends HttpServlet {
    private Storage sqlStorage;

    @Override
    public void init() throws ServletException {
        sqlStorage = Config.get().getSqlStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", sqlStorage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume r;
        switch (action) {
            case "delete":
                sqlStorage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "add":
                r = Resume.createEmpty();
                break;
            case "view":
                r = sqlStorage.get(uuid);
                break;
            case "edit":
                r = sqlStorage.get(uuid);

                for (SectionType type : SectionType.values()) {
                    AbstractSection section = r.getSection(type);
                    switch (type) {
                        case OBJECTIVE:
                        case PERSONAL:
                            if (section == null) {
                                section = SingleLineSection.createEmpty();
                            }
                            break;
                        case ACHIEVEMENT:
                        case QUALIFICATIONS:
                            if (section == null) {
                                section = BulletedListSection.createEmpty();
                            }
                            break;
                        case EXPERIENCE:
                        case EDUCATION:
                            if (section == null) {
                                section = CompanySection.createEmpty();
                            }

                            List<Company> companies = ((CompanySection) section).getCompanies();
                            if (companies.isEmpty()) {
                                section = CompanySection.createEmpty();
                            }

                            ((CompanySection)section).getCompanies().forEach(company -> {
                                if (company.getPeriods().isEmpty()) {
                                    company.setPeriods(Collections.singletonList(Company.Period.createEmpty()));
                                }
                            });
                            break;
                    }
                    r.addSection(type, section);
                }

                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        request.setAttribute("resume", r);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");

        boolean isNewResume = ((uuid == null) || (uuid.length() == 0));
        Resume r;
        if (isNewResume) {
            r = new Resume(fullName);
        } else {
            r = sqlStorage.get(uuid);
            r.setFullName(fullName);
        }

        for (ContactType type : ContactType.values()) {
            String value = StringUtil.removeUselessSymbols(request.getParameter(type.name()));
            if (value != null && !value.isEmpty()) {
                r.addContact(type, value);
            } else {
                r.getContacts().remove(type);
            }
        }

        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            if (value != null) {
                switch (type) {
                    case OBJECTIVE:
                    case PERSONAL:
                        r.addSection(type, new SingleLineSection(value));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        r.addSection(type,
                                new BulletedListSection(Arrays.stream(value.split("\\n"))
                                        .filter(s -> StringUtil.removeUselessSymbols(s).length() > 0)
                                        .collect(Collectors.toList())));
                        break;
                }
            } else {
                if (type.equals(SectionType.EDUCATION) || type.equals(SectionType.EXPERIENCE)) {
                    String[] values = request.getParameterValues(type.name() + "name");
                    if (values != null) {
                        List<Company> companies = new ArrayList<>();
                        String[] urls = request.getParameterValues(type.name() + "url");
                        for (int i = 0; i < values.length; i++) {
                            String name = values[i];
                            if (!name.isEmpty()) {
                                List<Company.Period> periods = new ArrayList<>();
                                String index = type.name() + i;

                                String[] startDates = request.getParameterValues(index + "startDate");
                                String[] endDates = request.getParameterValues(index + "endDate");
                                String[] titles = request.getParameterValues(index + "name");
                                String[] descriptions = request.getParameterValues(index + "description");

                                for (int j = 0; j < titles.length; j++) {
                                    if (!titles[j].isEmpty()) {
                                        periods.add(new Company.Period(DateUtil.fromString(startDates[j]),
                                                DateUtil.fromString(endDates[j]), titles[j], descriptions[j]));
                                    }
                                }
                                companies.add(new Company(new Link(name, urls[i]), periods));
                            }
                        }
                        r.addSection(type, new CompanySection(companies));
                    }

                } else {
                    r.getSections().remove(type);
                }
            }
        }

        if (isNewResume) {
            sqlStorage.save(r);
        } else {
            sqlStorage.update(r);
        }

        response.sendRedirect("resume");
    }
}
