<%@ page import="ru.javawebinar.basejava.model.SingleLineSection" %>
<%@ page import="ru.javawebinar.basejava.model.BulletedListSection" %>
<%@ page import="ru.javawebinar.basejava.model.CompanySection" %>
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
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<ru.javawebinar.basejava.model.ContactType, java.lang.String>"/>
            <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
        </c:forEach>
    </p>
    <p>
        <c:forEach var="sectionEntry" items="${resume.sections}">
            <jsp:useBean id="sectionEntry"
                         type="java.util.Map.Entry<ru.javawebinar.basejava.model.SectionType, ru.javawebinar.basejava.model.AbstractSection>"/>
                <c:set var="type" value="${sectionEntry.key}"/>
                <c:set var="section" value="${sectionEntry.value}"/>
                <jsp:useBean id="section" type="ru.javawebinar.basejava.model.AbstractSection"/>
                <td colspan="2"><h2><a name="type.name">${type.title}</a></h2></td>
            <c:choose>
                <c:when test="${type=='OBJECTIVE' || type=='PERSONAL'}">
                    <%=((SingleLineSection) section).getValue()%>
                </c:when>
                <c:when test="${type=='QUALIFICATIONS' || type=='ACHIEVEMENT'}">
                    <ul>
                        <c:forEach var="entry" items="<%=((BulletedListSection) section).getValues()%>">
                            <li>${entry}</li>
                        </c:forEach>
                    </ul>
                </c:when>
                <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                    <c:forEach var="org" items="<%=((CompanySection) section).getCompanies()%>">
                        <c:choose>
                            <c:when test="${empty org.homePage.url}">
                                <h3>${org.homePage.name}</h3>
                            </c:when>
                            <c:otherwise>
                                <h3><a href="${org.homePage.url}">${org.homePage.name}</a></h3>
                            </c:otherwise>
                        </c:choose>
                        <c:forEach var="position" items="${org.periods}">
                            <jsp:useBean id="position" type="ru.javawebinar.basejava.model.Company.Period"/>
                            <BR>с <B>${org.getPrettyDate(position.start)}</B> по <B>${org.getPrettyDate(position.end)}</B>
                            <BR>${position.name}
                            <BR>${position.description}
                        </c:forEach>
                    </c:forEach>
                </c:when>
            </c:choose>
        </c:forEach>
    </p>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
