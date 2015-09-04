<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>University</title>
</head>
<body>
<c:set var="page" value="university_structure"/>
<%@include file="../header.jsp" %>

<div align="center" style="width:600px; margin:0 auto;">
    <table class="table table-striped table-responsive">
        <caption><h4 align="center"><fmt:message key="page.university_structure" bundle="${lang_current}"/></h4>
        </caption>
        <tr>
            <th><fmt:message key="page.faculty" bundle="${lang_current}"/></th>
            <th><fmt:message key="page.faculty_enrollment" bundle="${lang_current}"/></th>
        </tr>
        <c:forEach var="faculty" items="${faculties}">
            <tr>
                <td><c:out value="${faculty.name}"/></td>
                <td><c:out value="${faculty.enrollment}"/></td>
            </tr>
        </c:forEach>
    </table>
</div>

<div align="center">
    <br/><br/>
    <a href="/"><fmt:message key="page.index" bundle="${lang_current}"/></a>
</div>

</body>
</html>