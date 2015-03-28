<%--
  Created by IntelliJ IDEA.
  User: ins
  Date: 3/7/15
  Time: 11:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ tag pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="xlogger" %>
<%--<%@ taglib prefix="l" uri="http://net.ins.xlogger" %>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ attribute name="msg" required="true" type="net.ins.xlogger.msg.domain.PreparedTopic" %>

<div id="msg-${msg.id}">
  <a href="${msg.url}">${msg.subject}</a>
  <div>${msg.body}</div>
</div>