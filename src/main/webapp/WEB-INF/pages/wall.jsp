<%--
  Created by IntelliJ IDEA.
  User: ins
  Date: 3/4/15
  Time: 9:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="xlogger" %>
<html>
<head>
    <title>Wall</title>
</head>
<body>
  <p>Welcome to xlogger</p>
  <a class="btn btn-default btn-xs" href="${pageContext.request.contextPath}/topic/add"><sp:message code="view.wall.topic.new"/></a>
  <c:forEach var="topic" items="${topics}">
    <xlogger:wall-msg msg="${topic}" />
    <xlogger:reply topic="${topic}" />
  </c:forEach>
</body>
</html>
