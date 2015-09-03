<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>University</title>
</head>
<body>
<c:set var="page" value="index"/>
<%@include file="view/header.jsp" %>
<h2 align="center" style="margin-top: 100px"><fmt:message key="page.entrance_campaign_site"
                                                          bundle="${lang_current}"/></h2>
<img src="lib/image/university_main.jpg"
     style="display: block; margin-top: auto; margin-left: auto; margin-right: auto;">
</body>
</html>