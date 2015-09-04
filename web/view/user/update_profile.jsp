<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>University</title>
</head>
<body>
<c:set var="page" value="update_profile"/>
<%@include file="../header.jsp" %>
<div class="container" align="center">
    <div class="row pad-top">
        <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1">
            <form action="${update_profile_action}" method="post">
                <div class="form-group input-group">
                    <span class="input-group-addon">A</span>
                    <input lang="en ru" type="text" name="name" maxlength="150" class="form-control"
                           placeholder="${sessionScope.user.name}"/>
                </div>
                <div class="form-group input-group">
                    <span class="input-group-addon">@</span>
                    <input type="email" name="email" maxlength="150" class="form-control" placeholder="${sessionScope.user.email}"/>
                </div>
                <div class="form-group input-group">
                    <span class="input-group-addon">#</span>
                    <input type="password" name="password" maxlength="45" class="form-control"
                           placeholder="${sessionScope.user.password}"/>
                </div>
                <input type="hidden" name="command" value="update_profile">
                <input type="hidden" name="switch_lang_action" value="${switch_lang_action}"/>
                <button type="submit" class="btn btn-success">
                    <fmt:message key="user.update_profile" bundle="${lang_current}"/>
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
