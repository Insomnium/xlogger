<%--
  Created by IntelliJ IDEA.
  User: ins
  Date: 3/7/15
  Time: 9:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title>Topic</title>
</head>
<body>
  <sf:form class="form" role="form" modelAttribute="form" method="POST" action="add">
    <div class="form-group">
      <label class="col-sm-2 control-label"><sp:message code="view.topic.topic" />:</label>
      <div class="col-sm-10">
        <sf:input class="form-control" path="subject" required="required" size="100" /><sf:errors path="subject" element="label" cssClass="error" for="subject"/>
      </div>
    </div>
    <div class="form-group">
      <label class="col-sm-2 control-label"><sp:message code="view.topic.body"/>:</label>
      <div class="col-sm-10">
        <sf:textarea class="form-control" path="body" required="required" rows="15" size="4000" /><sf:errors path="body" element="label" cssClass="error" for="body"/>
      </div>
    </div>
    <div class="form-group">
      <div class="col-sm-offset-2 col-sm-10">
        <button type="submit" class="btn btn-default"><sp:message code="view.message.add"/></button>
      </div>
    </div>
  </sf:form>
</body>
</html>
