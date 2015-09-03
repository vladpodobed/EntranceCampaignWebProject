<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Error</title>
    <link rel="stylesheet" type="text/css" href="lib/css/bootstrap/bootstrap.min.css">
</head>
<body>
<c:set var="page" value="404"/>
<%@include file="../header.jsp" %>
<h1 style="font-size: 50px">error 404</h1>
</body>
</html>