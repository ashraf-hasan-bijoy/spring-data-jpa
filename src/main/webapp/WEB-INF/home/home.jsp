<%--
  Created by IntelliJ IDEA.
  User: ashrafhasan
  Date: 1/16/17
  Time: 4:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/taglibs.jsp" %>
<html>
<head>
    <title></title>
    <meta name='description' content='A simple page'>
</head>
<body>
<div>
    <table class="list">
        <tr>
            <td><h2><fmt:message key="label.developer"/></h2></td>
            <td><a href="<c:url value="/form/devel/create"/>">New</a> | <a
                    href="<c:url value="/form/devel/list"/>">List</a></td>
        </tr>
        <tr>
            <td><h2><fmt:message key="label.project"/></h2></td>
            <td><a href="<c:url value="/form/project/list"/>">List</a></td>
        </tr>
    </table>
</div>
</body>
</html>
