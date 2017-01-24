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
<h2><fmt:message key="list.project"/></h2>

<div>
    <table class="list">
        <tr>
            <th>
                <fmt:message key="label.project.name"/>
            </th>
            <th>
                <fmt:message key="label.devel.name"/>
            </th>
            <th>
                <fmt:message key="label.action"/>
            </th>
        </tr>
        <c:forEach items="${projectList}" var="project">
            <tr>
                <td><c:out value="${project.name}"/></td>
                <td><c:out value="${project.developer.name}"/></td>
                <c:url value="/form/project/show" var="projectShow">
                    <c:param name="id" value="${project.id}"/>
                </c:url>
                <td><a href="${projectShow}"><fmt:message key="label.edit"/></a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
