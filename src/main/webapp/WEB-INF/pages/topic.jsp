<%--
  Created by IntelliJ IDEA.
  User: ins
  Date: 3/7/15
  Time: 11:37 PM
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
  <div>${topic.subject}</div>
  <div>${topic.body}</div>
  <xlogger:reply topic="${topic}" />
  <c:forEach var="msg" items="${messages}">
    <xlogger:msg msg="${msg}" />
    <xlogger:reply topic="${topic}" comment="${msg}" />
  </c:forEach>
  <%--<a class="btn btn-default" href="${pageContext.request.contextPath}/topic/comment"><sp:message code="view.message.add"/></a>--%>
</body>
</html>