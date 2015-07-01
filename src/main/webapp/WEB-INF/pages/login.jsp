
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title>Login Page</title>
</head>
<body onload='document.loginForm.login.focus();'>

<h1><sp:message code="view.welcome.welcome" /></h1>
<div id="login-box">

    <h3><sp:message code="view.welcome.message" /></h3>

    <c:if test="${param.error == 'true'}">
        <div class="error"><sp:message code="exception.signup.wrongcredentials" /></div>
    </c:if>

    <%--<form name='loginForm' role='form' action="${config.mainUrl}/perform_login" method='POST'>--%>
    <form name='loginForm' role='form' action="<c:url value='/perform_login' />" method='POST'>
        <div class='form-group'>
            <label for='login'><sp:message code="view.welcome.login" />:</label>
            <input type='text' class='form-control' id='login' name='login'>
        </div>
        <div class='form-group'>
            <label for='passwd'><sp:message code="view.welcome.password" />:</label>
            <input type='password' class='form-control' id='passwd' name='passwd'>
        </div>
        <button type='submit' class='btn btn-default'><sp:message code="view.welcome.signin" /></button>

        <a href="${pageContext.request.contextPath}/registration"><sp:message code="view.welcome.signup" /></a>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</div>

</body>
</html>