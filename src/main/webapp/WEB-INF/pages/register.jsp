<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags"%>
<%
    session.setAttribute("reg-page-visited", Boolean.TRUE);
%>
<html>
<head>
    <title>Registration</title>
</head>
<body>
    <sf:form class="form-horizontal" role="form" modelAttribute="form" method="POST" action="registration">

        <div class="form-group">
            <label class="col-sm-2 control-label"><sp:message code="view.signup.nickname" />:</label>
            <div class="col-sm-10">
                <sf:input class="form-control"  path="login" required="required" size="40" /><sf:errors path="login" element="label" cssClass="error" for="login"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label"><sp:message code="view.signup.firstname" />:</label>
            <div class="col-sm-10">
                <sf:input class="form-control" path="firstName" required="required" size="40" /><sf:errors path="firstName" element="label" cssClass="error" for="firstName"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label"><sp:message code="view.signup.lastname" />:</label>
            <div class="col-sm-10">
                <sf:input class="form-control" path="lastName" required="required" size="40" /><sf:errors path="lastName" element="label" cssClass="error" for="lastName"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label"><sp:message code="view.signup.email" />:</label>
            <div class="col-sm-10">
                <sf:input class="form-control" path="email" type="email" required="required"  size="40" cssErrorClass="error"/><sf:errors path="email" element="label" cssClass="error" for="email"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label"><sp:message code="view.signup.password" />:</label>
            <div class="col-sm-10">
                <sf:password class="form-control" path="password" size="40" required="required" /><sf:errors path="password" element="label" cssClass="error" for="password"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label"><sp:message code="view.signup.password.confirmation" />:</label>
            <div class="col-sm-10">
                <sf:password class="form-control" path="passwordConfirmation" size="40" required="required" cssErrorClass="error"/><sf:errors path="passwordConfirmation" element="label" cssClass="error" for="passwordConfirmation"/>
            </div>
        </div>

        <div class="form-group">
            <label class="col-sm-2 control-label"><sp:message code="view.signup.rules.confirmation" />:</label>
            <div class="col-sm-10">
                <label for="rules"><sp:message code="view.signup.rules.confirmation.knownto" /> <a href="${pageContext.request.contextPath}/rules.jsp" target="_blank" title="правила откроются в новом окне"><sp:message code="view.signup.rules.confirmation.rules" /></a> <sp:message code="view.signup.rules.confirmation.agree" />: <sf:checkbox path="rules" value="okay" required="required" cssErrorClass="error"/><sf:errors path="rules" element="label" cssClass="error" for="rules"/></label>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default"><sp:message code="view.signup.proceed" /></button>
            </div>
        </div>
    </sf:form>
</body>
</html>
