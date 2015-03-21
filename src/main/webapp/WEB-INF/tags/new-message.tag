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
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags"%>
<%@ attribute name="topic" required="true" type="net.ins.xlogger.msg.entities.Topic" %>

<div id="wall-comment-wrapper-${topic.id}">
    <div id="wall-comment-${topic.id}" class="wall-comment" data-topicid="${topic.id}">
      <sf:form class="form" role="form" modelAttribute="form" method="POST" action="comment/${topic.id}/add">
        <sf:textarea class="form-control" path="body" required="required" rows="5" size="4000" /><sf:errors path="body" element="label" cssClass="error" for="body"/>
        <button type="submit" class="btn btn-default"><sp:message code="view.wall.comment"/></button>
      </sf:form>
    </div>
    <div class="wall-comment-add">
        <input type="button" class="btn btn-default wall-comment-btn" id="${topic.id}_add" value="<sp:message code="view.wall.comment"/>">
    </div>
</div>