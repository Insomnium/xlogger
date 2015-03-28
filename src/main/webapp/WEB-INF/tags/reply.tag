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
<%@ attribute name="topic" required="true" type="net.ins.xlogger.msg.domain.PreparedTopic" %>
<%@ attribute name="comment" required="false" type="net.ins.xlogger.msg.entities.Message" %>

<div id="wall-reply-wrapper-${topic.id}" class="wall-reply-wrapper">
    <div id="wall-reply-${topic.id}" class="wall-reply" data-topicid="${topic.id}">
      <sf:form class="form" role="form" modelAttribute="form" method="POST" action="/comment/${topic.id}">
        <sf:textarea class="form-control" path="body" required="required" rows="5" size="4000" /><sf:errors path="body" element="label" cssClass="error" for="body"/>
          <c:if test="${comment != null}">
            <sf:input hidden="true" path="commentId" value="${comment.id}" />
          </c:if>
        <button type="submit" class="btn btn-default btn-xs"><sp:message code="view.wall.comment"/></button>
      </sf:form>
    </div>
    <div class="wall-reply-add">
        <c:choose>
            <c:when test="${comment != null}">
                <input type="button" class="btn btn-default btn-xs" id="${topic.id}_add" value="<sp:message code="view.wall.comment"/>">
            </c:when>
            <c:otherwise>
                <input type="button" class="btn btn-default btn-xs" id="${topic.id}_add" value="<sp:message code="view.reply"/>">
            </c:otherwise>
        </c:choose>


    </div>
</div>