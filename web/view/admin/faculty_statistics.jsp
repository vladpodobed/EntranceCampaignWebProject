<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>University</title>
</head>
<body>
<c:set var="page" value="faculty_statistics"/>
<%@include file="../header.jsp" %>

<div align="center" style="width:1000px; margin:0 auto;">
    <table class="table table-striped table-responsive">
        <caption><h4 align="center"><fmt:message key="admin.faculty_statistics" bundle="${lang_current}"/></h4>
        </caption>
        <tr>
            <th><fmt:message key="user.status" bundle="${lang_current}"/></th>
            <th><fmt:message key="user.name" bundle="${lang_current}"/></th>
            <th><fmt:message key="user.email" bundle="${lang_current}"/></th>
            <th><fmt:message key="user.school_certificate_score" bundle="${lang_current}"/></th>
            <c:forEach var="discipline" items="${faculty_disciplines}">
                <th><c:out value="${discipline.name}"/></th>
            </c:forEach>
        </tr>
        <c:forEach var="candidate" items="${candidates}">
            <tr>
                <td><c:out value="${candidate.status}"/></td>
                <td><c:out value="${candidate.name}"/></td>
                <td><c:out value="${candidate.email}"/></td>
                <td><c:out value="${candidate.schoolCertificateScore}"/></td>
                <c:forEach var="certificate" items="${candidate.certificates}">
                    <td><c:out value="${certificate.result}"/></td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <br/>
    <%--<a href="/">index page</a>--%>
</div>

<div align="center">
    <br/><br/>
    <a href="/"><fmt:message key="page.index" bundle="${lang_current}"/></a>
</div>

</body>
</html>
