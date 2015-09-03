<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<fmt:setBundle basename="resources.i18n_en" var="lang_en"/>
<fmt:setBundle basename="resources.i18n_ru" var="lang_ru"/>

<c:choose>
    <c:when test="${empty bundle}">
        <fmt:setBundle basename="resources.i18n_en" var="lang_current" scope="session"/>
        <c:set var="bundle" value="resources.i18n_en" scope="session"/>
    </c:when>
    <c:otherwise>
        <fmt:setBundle basename="${bundle}" var="lang_current" scope="session"/>
        <c:set var="bundle" value="${bundle}" scope="session"/>
    </c:otherwise>
</c:choose>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>University</title>
    <link rel="stylesheet" type="text/css" href="lib/css/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="lib/css/style.css">
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <a class="navbar-brand navbar-btn" href="/">
            <fmt:message key="page.university_title" bundle="${lang_current}"/>
        </a>

        <div class="navbar-header">
            <button type="button" class="navbar-btn navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle navbar-btn" data-toggle="dropdown" role="button"
                       aria-expanded="false">
                        <c:choose>
                            <c:when test="${sessionScope.user.status == 'администратор'}">
                                <fmt:message key="admin.admin_operations" bundle="${lang_current}"/>
                            </c:when>
                            <c:otherwise>
                                <fmt:message key="user.user_operations" bundle="${lang_current}"/>
                            </c:otherwise>
                        </c:choose>
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu" role="menu">
                        <c:choose>
                            <c:when test="${sessionScope.user.status == 'администратор'}">
                                <li>
                                    <form action="/select_faculty" method="post" class="navbar-form">
                                        <input type="hidden" name="command" value="show_select_faculty"/>
                                        <input type="hidden" name="command_option"
                                               value="admin_show_faculty_statistics"/>
                                        <button type="submit" class="btn btn-primary btn-sm btn-group-justified">
                                            <fmt:message key="admin.faculty_statistics" bundle="${lang_current}"/>
                                        </button>
                                    </form>
                                </li>
                                <li class="divider"></li>
                                <li>
                                    <form action="/select_faculty" method="post" class="navbar-form">
                                        <input type="hidden" name="command" value="show_select_faculty"/>
                                        <input type="hidden" name="command_option" value="admin_select_abiturient"/>
                                        <button type="submit" class="btn btn-primary btn-sm btn-group-justified">
                                            <fmt:message key="admin.select_abiturients" bundle="${lang_current}"/>
                                        </button>
                                    </form>
                                </li>
                                <li class="divider"></li>
                                <li>
                                    <form action="/index" method="post" class="navbar-form">
                                        <input type="hidden" name="command" value="finish_entrance_campaign"/>
                                        <button type="submit" <c:if test="${sessionScope.finishCampaign}"><c:out
                                                value="disabled='disabled'"/></c:if>
                                                class="btn btn-primary btn-sm btn-group-justified">
                                            <fmt:message key="admin.finish_campaign" bundle="${lang_current}"/>
                                        </button>
                                    </form>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${not empty sessionScope.user}">
                                        <li>
                                            <form action="/select_faculty" method="post" class="navbar-form">
                                                <input type="hidden" name="command" value="show_select_faculty"/>
                                                <input type="hidden" name="command_option"
                                                       value="user_register_at_faculty"/>
                                                <button type="submit" <c:if
                                                        test="${(not empty sessionScope.user.facultyId and sessionScope.user.facultyId != 0) and sessionScope.finishCampaign}"><c:out
                                                        value="disabled='disabled'"/></c:if>
                                                        class="btn btn-primary btn-sm btn-group-justified">
                                                    <fmt:message key="user.register_at_a_faculty"
                                                                 bundle="${lang_current}"/>
                                                </button>
                                            </form>
                                        </li>
                                        <li class="divider"></li>
                                    </c:when>
                                </c:choose>
                                <li>
                                    <form action="/university_structure" method="post" class="navbar-form">
                                        <input type="hidden" name="command" value="show_university_structure"/>
                                        <button type="submit"
                                                class="btn btn-primary btn-sm btn-group-justified">
                                            <fmt:message key="page.university_structure" bundle="${lang_current}"/>
                                        </button>
                                    </form>
                                </li>
                                <li class="divider"></li>
                                <li>
                                    <form action="/help" method="post" class="navbar-form">
                                        <input type="hidden" name="command" value="show_help"/>
                                        <button type="submit" class="btn btn-primary btn-sm btn-group-justified">
                                            <fmt:message key="user.help" bundle="${lang_current}"/>
                                        </button>
                                    </form>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </li>
            </ul>

            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                    <div class="navbar-left form-group collapse navbar-collapse" id="bs-example-navbar-collapse-2">
                        <ul class="nav navbar-nav">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle navbar-btn" data-toggle="dropdown" role="button"
                                   aria-expanded="false">
                                        ${sessionScope.user.email}
                                    <span class="caret"></span></a>
                                <ul class="dropdown-menu" role="menu">
                                    <li>
                                        <form action="/profile" method="post" class="navbar-form">
                                            <input type="hidden" name="command" value="show_profile"/>
                                            <button type="submit" class="btn btn-primary btn-sm btn-group-justified">
                                                <fmt:message key="user.profile" bundle="${lang_current}"/>
                                            </button>
                                        </form>
                                    </li>
                                    <li class="divider"></li>
                                    <li>
                                        <form action="/index" method="post" class="navbar-form">
                                            <input type="hidden" name="command" value="log_out"/>
                                            <button type="submit" class="btn btn-primary btn-sm btn-group-justified">
                                                <fmt:message key="user.log_out" bundle="${lang_current}"/>
                                            </button>
                                        </form>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="navbar-left form-group">
                        <form action="/index" method="post" class="navbar-form navbar-left">
                            <input type="email" id="email" name="email" class="form-control btn-sm navbar-btn"
                                   placeholder="<fmt:message key="authentication.email" bundle="${lang_current}"/>">
                            <input type="password" id="password" name="password" class="form-control btn-sm navbar-btn"
                                   placeholder="<fmt:message key="authentication.password" bundle="${lang_current}"/>"/>
                            <input type="hidden" name="command" value="log_in"/>
                            <button type="submit" class="form-control btn btn-primary btn-sm navbar-btn">
                                <fmt:message key="user.log_in" bundle="${lang_current}"/>
                            </button>
                        </form>
                        <form action="/sign_up_form" method="post" class="navbar-form navbar-left">
                            <input type="hidden" name="command" value="show_sign_up_form"/>
                            <button type="submit" class="form-control btn btn-primary btn-sm navbar-btn">
                                <fmt:message key="user.sign_up" bundle="${lang_current}"/>
                            </button>
                        </form>
                    </div>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${login_error or input_error}">
                    <div class="navbar-btn glyphicon-alert navbar-left">
                        <p style="color: #c9302c;">
                            <fmt:message key="authentication.check_input" bundle="${lang_current}"/>
                        </p>
                    </div>
                </c:when>
            </c:choose>

            <c:choose>
                <c:when test="${empty switch_lang_action}">
                    <c:set var="switch_lang_action" value="/index"/>
                </c:when>
            </c:choose>

            <div class="navbar-right">
                <form action="${switch_lang_action}" method="post" class="navbar-form navbar-right">
                    <input type="hidden" name="command" value="switch_lang"/>
                    <input type="hidden" name="switch_lang_action" value="${switch_lang_action}"/>

                    <c:choose>
                        <c:when test="${sessionScope.bundle == 'resources.i18n_en'}">
                            <input type="hidden" name="lang" value="en">
                        </c:when>
                        <c:otherwise>
                            <input type="hidden" name="lang" value="ru">
                        </c:otherwise>
                    </c:choose>

                    <c:choose>
                        <c:when test="${page == 'select_faculty' or
                        page == 'faculty_statistics' or
                        page == 'select_abiturient' or
                        page == 'register_at_faculty'}">
                            <input type="hidden" name="command_option" value="${command_option}"/>
                            <input type="hidden" name="faculty_name" value="${faculty_name}">
                        </c:when>
                    </c:choose>

                    <button type="submit" class="form-control btn btn-primary btn-sm navbar-btn">
                        <fmt:message key="page.switch_lang" bundle="${lang_current}"/>
                    </button>
                </form>
            </div>
        </div>
    </div>
</nav>

<!-- For bootstrap -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="lib/js/bootstrap/bootstrap.min.js"></script>
</body>
</html>