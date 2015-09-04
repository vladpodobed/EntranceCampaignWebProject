<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>University</title>
</head>
<body>
<c:set var="page" value="register_at_faculty"/>
<%@include file="../header.jsp" %>
<div class="container" align="center">
    <div class="row pad-top">
        <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1">
            <h3><fmt:message key="page.input_data" bundle="${lang_current}"/></h3>

            <form action="${register_at_faculty_action}" method="post">
                <div class="form-group">
                    <c:forEach var="discipline" items="${faculty_disciplines}">
                        <label><c:out value="${discipline.name}"/> <fmt:message key="user.certificate" bundle="${lang_current}"/></label>
                        <input type="number" min="0" max="100" class="form-control" id="usr" name="${discipline.name}">
                    </c:forEach>
                    <label><fmt:message key="user.school_certificate_score" bundle="${lang_current}"/></label>
                    <input type="number" step="0.1" min="1.0" max="10.0" class="form-control" id="pwd"
                           name="school_certificate">
                </div>
                <input type="hidden" name="command" value="register_at_faculty">
                <input type="hidden" name="command_option" value="${command_option}">
                <input type="hidden" name="faculty_name" value="${faculty_name}">
                <button type="submit" class="btn btn-success">
                    <fmt:message key="user.register_at_a_faculty" bundle="${lang_current}"/>
                </button>
                <hr/>
            </form>
        </div>
    </div>
</div>

<div align="center">
    <br/><br/>
    <a href="/"><fmt:message key="page.index" bundle="${lang_current}"/></a>
</div>

</body>
</html>