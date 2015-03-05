<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags"%>

<p>${message}</p>

<c:if test="${instructions!=null}">
  <p>${instructions}</p>
</c:if>

<c:if test="${link!=null}">
  <p>
    <a href="${link}"><sp:message code="view.done.continue" /></a>
  </p>
</c:if>


