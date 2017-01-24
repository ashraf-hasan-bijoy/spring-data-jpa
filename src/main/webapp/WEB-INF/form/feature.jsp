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
<h2><fmt:message key="label.feature"/></h2>
<form:form action="index" commandName="feature">
    <table>
        <tr>
            <td><label for="name"><fmt:message key="label.name"/></label></td>
            <td><form:input id="name" path="name"/></td>
            <td><form:errors path="name" cssClass="error" /></td>
        </tr>
        <tr>
            <td><label for="version"><fmt:message key="label.release.version"/></label></td>
            <td><form:input id="version" path="releaseVersion"/></td>
            <td><form:errors path="releaseVersion" cssClass="error" /></td>
        </tr>
    </table>

    <div class="buttons">
        <c:if test="${feature.new}">
            <input type="submit" value="Save" name="_action_save"/>
        </c:if>

        <c:if test="${not feature.new}">
            <input type="submit" value="Delete" name="_action_delete"/>
            <input type="submit" value="Update" name="_action_update"/>
        </c:if>
    </div>
</form:form>
</body>
</html>