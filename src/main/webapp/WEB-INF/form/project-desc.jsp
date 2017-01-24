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
            <td><label for="description"><fmt:message key="label.project.desc"/></label></td>
            <td><form:textarea rows="3" cols="60" id="description" path="description"/></td>
            <td><form:errors path="description" cssClass="error" /></td>
        </tr>
    </table>
    <div>
        <h2><fmt:message key="label.feature.list"/></h2>
        <c:choose>
            <c:when test="${empty project.developer.featureList}">
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
                    </tr>
                    <c:forEach items="${project.developer.featureList}" var="feature">
                        <tr>
                            <td><c:out value="${feature.name}"/></td>
                            <td><c:out value="${feature.releaseVersion}"/></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="buttons">
        <c:if test="${project.new}">
            <input type="submit" value="Save" name="_action_save"/>
        </c:if>

        <c:if test="${not project.new}">
            <input type="submit" value="Update" name="_action_update"/>
        </c:if>
    </div>
</form:form>
</body>
</html>