<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>University</title>
</head>
<body>
<c:set var="page" value="profile"/>
<%@include file="../header.jsp" %>

<div align="center" style="width:1000px; margin:0 auto;">
    <table class="table table-striped table-responsive">
        <caption><h4 align="center"><fmt:message key="user.profile" bundle="${lang_current}"/></h4>
        </caption>
        <tr>
            <th><fmt:message key="user.status" bundle="${lang_current}"/></th>
            <th><fmt:message key="user.email" bundle="${lang_current}"/></th>
            <th><fmt:message key="user.password" bundle="${lang_current}"/></th>
            <th><fmt:message key="user.name" bundle="${lang_current}"/></th>
            <c:choose>
                <c:when test="${sessionScope.user.status != 'посетитель' and
                sessionScope.user.status != 'администратор'}">
                    <th><fmt:message key="user.school_certificate_score" bundle="${lang_current}"/></th>
                    <c:forEach var="discipline" items="${faculty_disciplines}">
                        <th><c:out value="${discipline.name}"/></th>
                    </c:forEach>
                    <th><fmt:message key="user.faculty" bundle="${lang_current}"/></th>
                </c:when>
            </c:choose>
        </tr>
        <tr>
            <td><c:out value="${sessionScope.user.status}"/></td>
            <td><c:out value="${sessionScope.user.email}"/></td>
            <td><c:out value="${sessionScope.user.password}"/></td>
            <td><c:out value="${sessionScope.user.name}"/></td>
            <c:choose>
                <c:when test="${sessionScope.user.status != 'посетитель' and
                sessionScope.user.status != 'администратор'}">
                    <td><c:out value="${sessionScope.user.schoolCertificateScore}"/></td>
                    <c:forEach var="certificate" items="${sessionScope.user.certificates}">
                        <td><c:out value="${certificate.result}"/></td>
                    </c:forEach>
                    <td><c:out value="${faculty_name}"/></td>
                </c:when>
            </c:choose>
        </tr>
    </table>
</div>
<br/>
<br/>

<div class="form-group" align="center" style="width:200px; margin:0 auto;">
    <form action="/update_profile" method="post" class="navbar-form">
        <input type="hidden" name="command" value="show_update_profile"/>
        <input type="hidden" name="switch_lang_action" value="${switch_lang_action}"/>
        <button type="submit" class="btn btn-primary btn-sm btn-group-justified">
            <fmt:message key="user.update_profile" bundle="${lang_current}"/>
        </button>
    </form>
    <c:choose>
        <c:when test="${sessionScope.user.status != 'посетитель' and
        sessionScope.user.status != 'администратор'}">
            <form action="/index" method="post" class="navbar-form">
                <input type="hidden" name="command" value="take_documents"/>
                <button type="submit" class="btn btn-primary btn-sm btn-group-justified">
                    <fmt:message key="user.take_documents" bundle="${lang_current}"/>
                </button>
            </form>
        </c:when>
    </c:choose>
</div>

<div align="center">
    <br/><br/>
    <a href="/"><fmt:message key="page.index" bundle="${lang_current}"/></a>
</div>

</body>
</html>