<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>University</title>
    <link rel="stylesheet" type="text/css" href="lib/css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="lib/css/style.css">
</head>
<body>
<c:set var="page" value="help"/>
<%@include file="../header.jsp" %>

<div style="align-self: auto;">
    <div id="list1">
        <ul>
            <h3><p><fmt:message key="help.registration" bundle="${lang_current}"/></p></h3>
            <li><p><fmt:message key="help.registration_advantages" bundle="${lang_current}"/></p></li>
            <li><p><fmt:message key="help.take_documents_ability" bundle="${lang_current}"/></p></li>
            <li><p><fmt:message key="help.faculty_apply_moment" bundle="${lang_current}"/></p></li>
        </ul>
        <ul>
            <h3><p><fmt:message key="help.profile" bundle="${lang_current}"/></p></h3>
            <li><p><fmt:message key="help.about_profile" bundle="${lang_current}"/></p></li>
            <li><p><fmt:message key="help.user_five_states" bundle="${lang_current}"/></p></li>
            <li><p><fmt:message key="help.visitor" bundle="${lang_current}"/></p></li>
            <li><p><fmt:message key="help.documents_processing" bundle="${lang_current}"/></p></li>
            <li><p><fmt:message key="help.abiturient" bundle="${lang_current}"/></p></li>
            <li><p><fmt:message key="help.student" bundle="${lang_current}"/></p></li>
            <li><p><fmt:message key="help.not_enrolled" bundle="${lang_current}"/></p></li>
        </ul>
    </div>
</div>

<div align="center">
    <br/><br/>
    <a href="/"><fmt:message key="page.index" bundle="${lang_current}"/></a>
</div>

</body>
</html>