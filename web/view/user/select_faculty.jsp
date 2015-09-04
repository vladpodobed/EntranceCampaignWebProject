<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>University</title>

</head>
<body>
<c:set var="page" value="select_faculty"/>
<%@include file="../header.jsp" %>

<div class="container">
    <form action="${select_faculty_action}" method="post">
        <div class="form-group">
            <label for="sel_fac">Select faculty:</label>
            <select class="form-control" id="sel_fac" name="faculty_name">
                <c:forEach var="faculty" items="${faculties}">
                    <option><c:out value="${faculty.name}"/></option>
                </c:forEach>
            </select>
            <br/>
            <input type="hidden" name="command_option" value="${command_option}"/>
            <input type="hidden" name="command" value="select_faculty"/>
            <button type="submit" class="btn btn-success">
                <fmt:message key="user.select" bundle="${lang_current}"/>
            </button>
        </div>
    </form>
</div>

<div align="center">
    <br/><br/>
    <a href="/"><fmt:message key="page.index" bundle="${lang_current}"/></a>
</div>

</body>
</html>