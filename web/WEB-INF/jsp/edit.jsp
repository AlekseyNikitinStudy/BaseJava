<%@ page import="ru.javawebinar.basejava.model.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <dt>Имя:</dt>
            <dd><input type="text" name="fullName" size=50 value="${resume.fullName}"></dd>
        </dl>
        <h3>Контакты:</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd><input type="text" name="${type.name()}" size=30 value="${resume.getContact(type)}"></dd>
            </dl>
        </c:forEach>

        <c:forEach var="type" items="${SectionType.values()}">
            <c:set var="section" value="${resume.getSection(type)}"/>

            <c:if test="${not empty section}">
                <h3><a>${type.title}</a></h3>
                <jsp:useBean id="section" type="ru.javawebinar.basejava.model.AbstractSection"/>
                <c:choose>
                    <c:when test="${type=='PERSONAL' || type=='OBJECTIVE'}">
                        <c:set var="textPO" value="<%=((SingleLineSection) section).getValue()%>"/>
                        <textarea name='${type}' cols=150 rows=3>${textPO}</textarea>
                    </c:when>
                    <c:when test="${type=='QUALIFICATIONS' || type=='ACHIEVEMENT'}">
                        <c:set var="textQA" value="<%=String.join(\"\n\", ((BulletedListSection) section).getValues())%>"/>
                        <textarea name='${type}' cols=150 rows=10>${textQA}</textarea>
                    </c:when>

                    <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                        <table>
                        <c:forEach var="company" items="<%=((CompanySection) section).getCompanies()%>" varStatus="companyIndex">
                            <tr>
                                <td><b>Наименование компании:</b></td>
                                <td><input type="text" name='${type}name' size=80 value="${company.homePage.name}"></td>
                            </tr>
                            <tr>
                                <td><b>Домашняя страница компании:</b></td>
                                <td><input type="text" name='${type}url' size=80 value="${company.homePage.url}"></td>
                            </tr>

                            <c:forEach var="period" items="${company.periods}">
                                <tr>
                                    <td colspan="2">
                                        <table>
                                            <tr>
                                                <td><b>Начало:</b></td>
                                                <td>
                                                    <jsp:useBean id="period" type="ru.javawebinar.basejava.model.Company.Period"/>
                                                    <input type="text" name="${type}${companyIndex.index}startDate" size=10 value="${company.getPrettyDate(period.start)}">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td><b>Окончание:</b></td>
                                                <td><input type="text" name="${type}${companyIndex.index}endDate" size=10 value="${company.getPrettyDate(period.end)}"></td>
                                            </tr>
                                            <tr>
                                                <td><b>Должность:</b></td>
                                                <td><input type="text" name='${type}${companyIndex.index}name' size=80 value="${period.name}"></td>
                                            </tr>
                                            <tr>
                                                <td><BR> <b>Описание:</b></td>
                                                <td><BR> <textarea name="${type}${companyIndex.index}description" rows=5 cols=75>${period.description}</textarea></td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td height="24"></td>
                            </tr>
                        </c:forEach>
                            <tr>
                                <td height="48"></td>
                            </tr>
                        </table>
                    </c:when>

                </c:choose>
            </c:if>

             </c:forEach>

            <hr>
             <button type="submit">Сохранить</button>
             <button onclick="window.history.back()" type="reset">Отменить</button>
         </form>
     </section>
     <jsp:include page="fragments/footer.jsp"/>
</body>
</html>
