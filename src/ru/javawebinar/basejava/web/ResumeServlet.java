package ru.javawebinar.basejava.web;

import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.SqlStorage;
import ru.javawebinar.basejava.storage.Storage;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class ResumeServlet extends HttpServlet {
    private Storage sqlStorage;

    @Override
    public void init() throws ServletException {
        sqlStorage = Config.get().getSqlStorage();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        Writer writer = response.getWriter();

        writer.write("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><title>Title</title></head><body>");
        writer.write("<h1>Resumes</h1>");
        writer.write("<table border=\"1\">");
        writer.write("<th>UUID</th><th>Full name</th>");
        sqlStorage.getAllSorted().forEach(r -> {
            try {
                outResume(response, writer, r);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.write("</table>");
        writer.write("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    private void outResume(HttpServletResponse response, Writer writer, Resume resume) throws IOException {
        writer.write("<tr><td>" + resume.getUuid() + "</td><td>" + resume.getFullName() + "</td></tr>");
    }
}
