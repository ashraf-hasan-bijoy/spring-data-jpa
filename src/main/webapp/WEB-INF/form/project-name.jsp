<%--
  Created by IntelliJ IDEA.
  User: ashrafhasan
  Date: 1/16/17
  Time: 3:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<%@ include file="/WEB-INF/taglibs.jsp" %>
<html>
<head>
    <title></title>
</head>
<body>
<h2><fmt:message key="label.project"/></h2>
<form:form action="index" commandName="project">
    <table>
        <tr>
            <td><label for="name"><fmt:message key="label.project.name"/></label></td>
            <td><form:input id="name" path="name"/></td>
            <td><form:errors path="name" cssClass="error" /></td>
        </tr>
        <tr>
            <td><label><fmt:message key="label.devel.name"/></label></td>
            <td><c:out value="${project.developer.name}"/></td>
        </tr>
    </table>
    <div class="buttons">
        <input type="submit" value="Next" name="_action_show_desc"/>
    </div>
</form:form>
</body>
</html>