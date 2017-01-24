<%--
  Created by IntelliJ IDEA.
  User: ashrafhasan
  Date: 1/19/17
  Time: 11:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ include file="/WEB-INF/taglibs.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>
<h2><fmt:message key="list.devel"/></h2>

<div>
    <table class="list">
        <tr>
            <th>
                <fmt:message key="label.name"/>
            </th>
            <th>
                <fmt:message key="label.designation"/>
            </th>
            <th>
                <fmt:message key="label.action"/>
            </th>
        </tr>
        <c:forEach items="${develList}" var="devel">
            <tr>
                <td><c:out value="${devel.name}"/></td>
                <td><c:out value="${devel.designation}"/></td>
                <c:url value="/form/devel/show" var="develShow">
                    <c:param name="id" value="${devel.id}"/>
                </c:url>
                <c:url value="/form/feature/create" var="createFeature">
                    <c:param name="develId" value="${devel.id}"/>
                </c:url>
                <c:url value="/form/project/create" var="createProject">
                    <c:param name="develId" value="${devel.id}"/>
                </c:url>
                <td><a href="${develShow}"><fmt:message key="label.edit"/></a> |
                    <a href="${createFeature}"><fmt:message key="label.feature.create"/></a> |
                    <a href="${createProject}"><fmt:message key="label.project.create"/></a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
