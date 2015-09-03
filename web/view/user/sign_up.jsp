<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>University</title>
</head>
<body>
<c:set var="page" value="sign_up"/>
<%@include file="../header.jsp" %>
<div class="container" align="center">
    <div class="row pad-top">
        <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-10 col-xs-offset-1">
            <form action="${sign_up_action}" method="post">
                <div class="form-group input-group">
                    <span class="input-group-addon">A</span>
                    <input type="text" maxlength="150" name="name" class="form-control"
                           placeholder="<fmt:message key="authentication.name" bundle="${lang_current}"/>"/>
                </div>
                <div class="form-group input-group">
                    <span class="input-group-addon">@</span>
                    <input type="email" maxlength="70" name="email" class="form-control"
                           placeholder="<fmt:message key="authentication.email" bundle="${lang_current}"/>"/>
                </div>
                <div class="form-group input-group">
                    <span class="input-group-addon">#</span>
                    <input type="password" maxlength="45" name="password" class="form-control"
                           placeholder="<fmt:message key="authentication.password" bundle="${lang_current}"/>"/>
                </div>
                <input type="hidden" name="command" value="sign_up">
                <button type="submit" class="btn btn-success">
                    <fmt:message key="user.sign_up" bundle="${lang_current}"/>
                </button>
                <hr/>
            </form>
            <a href="/">index page</a>
        </div>
    </div>
</div>
</body>
</html>
