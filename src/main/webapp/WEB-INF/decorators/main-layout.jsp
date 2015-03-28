<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="net.ins.xlogger.util.UserConfig" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags"%>
<%--@elvariable id="config" type="net.ins.xlogger.util.UserConfig"--%>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel="stylesheet"  type="text/css" />
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.js"></script>
    <script type="text/javascript" src="https://code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
    <link href="<c:url value='/resources/static/css/jquery-ui-ins.css' />" rel="stylesheet">
    <link href="<c:url value='/resources/static/css/xlogger.css' />" rel="stylesheet">
    <script src="<c:url value='/resources/static/js/common.js' />"></script>
    <script src="<c:url value='/resources/static/js/wall.js' />"></script>
    <decorator:head/>
</head>
<body>
<c:if test="${config == null}">
    <c:set var="config" value="<%= UserConfig.getConfig(request) %>"/>
</c:if>
<div class="container">
    <nav class="navbar navbar-default " role="navigation">
        <span style="display: inline-block; margin-left: 20px">
            <sp:message code="view.locale" />:
        </span>
        <span style="display: inline-block;">
            <select style="height: 20px !important; padding: 0px 0px !important;" class="form-control" id="locale_selector">
                <option value="ru">ru</option>
                <option value="en">en</option>
            </select>
        </span>
        <c:if test="${config.authenticated}" >
            <span style="display: inline-block;">
                <a href="<c:url value='${pageContext.request.contextPath}/wall' />"><sp:message code="view.header.wall"/></a>
            </span>
            <a style="float: right; margin-left: 12px"
               href="<c:url value='${pageContext.request.contextPath}/logout' />"><sp:message code="view.logout" /></a>
            <span style="float: right;"><sp:message code="view.welcome" />, ${config.userLogin}</span>
        </c:if>
    </nav>
    <script type="text/javascript">
        $('#locale_selector').val('${pageContext.response.locale}');
    </script>
    <decorator:body/>
</div>
</body>
</html>
