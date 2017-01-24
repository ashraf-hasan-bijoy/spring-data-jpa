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
<h2><fmt:message key="label.developer"/></h2>
<form:form action="index" commandName="devel">
    <table>
        <tr>
            <td><label for="name"><fmt:message key="label.name"/></label></td>
            <td><form:input id="name" path="name"/></td>
            <td><form:errors path="name" cssClass="error"/></td>
        </tr>
        <tr>
            <td><label for="designation"><fmt:message key="label.designation"/></label></td>
            <td><form:input id="designation" path="designation"/></td>
            <td><form:errors path="designation" cssClass="error"/></td>
        </tr>
    </table>

    <div>
        <h2><fmt:message key="label.feature.list"/></h2>
        <c:choose>
            <c:when test="${empty devel.featureList}">
                <span style="font-weight: bold"><fmt:message key="no.feature.message"/></span>
            </c:when>
            <c:otherwise>
                <table class="list">
                    <tr>
                        <th>
                            <fmt:message key="label.feature.name"/>
                        </th>
                        <th>
                            <fmt:message key="label.feature.deadline"/>
                        </th>
                        <th>
                            <fmt:message key="label.action"/>
                        </th>
                    </tr>
                    <c:forEach items="${devel.featureList}" var="feature">
                        <tr>
                            <td><c:out value="${feature.name}"/></td>
                            <td><c:out value="${feature.releaseVersion}"/></td>
                            <c:url value="/form/feature/show" var="showFeature">
                                <c:param name="id" value="${feature.id}"/>
                            </c:url>
                            <td><a href="${showFeature}"><fmt:message key="label.edit"/></a></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
    <div>
        <h2><fmt:message key="label.activity.list"/></h2>
        <table class="list">
            <tr>
                <th>
                    <fmt:message key="label.activity.message"/>
                </th>
            </tr>
            <c:forEach items="${devel.developerActivity.activityMessages}" var="message">
                <tr>
                    <td><c:out value="${message}"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="buttons">
        <input type="submit" value="Delete" name="_action_delete"/>
        <input type="submit" value="Update" name="_action_update"/>
    </div>

</form:form>
</body>
</html>