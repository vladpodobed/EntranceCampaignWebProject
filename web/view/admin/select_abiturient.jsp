<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="selectTag" uri="/WEB-INF/tld/SelectAbiturient.tld" %>

<html>
<head>
    <title>University</title>

</head>
<body>
<c:set var="page" value="select_abiturient"/>
<%@include file="../header.jsp" %>

<div align="center" style="width:1000px; margin:0 auto;">
    <table class="table table-striped table-responsive">
        <caption><h4 align="center"><fmt:message key="admin.select_abiturients" bundle="${lang_current}"/></h4>
        </caption>
        <tr>
            <th><fmt:message key="user.name" bundle="${lang_current}"/></th>
            <th><fmt:message key="user.email" bundle="${lang_current}"/></th>
            <th><fmt:message key="user.school_certificate_score" bundle="${lang_current}"/></th>
            <c:forEach var="discipline" items="${faculty_disciplines}">
                <th><c:out value="${discipline.name}"/></th>
            </c:forEach>
            <td>
                <form action="/select_abiturient" method="post" class="navbar-form">
                    <input type="hidden" name="command" value="select_abiturient"/>
                    <input type="hidden" name="command_option" value="${command_option}"/>
                    <input type="hidden" name="switch_lang_action" value="${switch_lang_action}"/>
                    <input type="hidden" name="faculty_name" value="${faculty_name}"/>
                    <input type="hidden" name="candidate_id" value="0"/>
                    <button type="submit" class="btn btn-primary btn-xs btn-group-justified">
                        <fmt:message key="admin.select_all_abiturients" bundle="${lang_current}"/>
                    </button>
                </form>
            </td>
        </tr>
        <c:forEach var="user" items="${candidates}">
            <selectTag:userSelect user="${user}" facultyName="${faculty_name}"
                                  commandOption="${command_option}" switchLangAction="${switch_lang_action}"/>
        </c:forEach>
    </table>
</div>

<div align="center">
    <br/><br/>
    <a href="/"><fmt:message key="page.index" bundle="${lang_current}"/></a>
</div>

</body>
</html>