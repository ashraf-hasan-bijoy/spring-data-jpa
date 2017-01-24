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
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            padding: 8px;
            text-align: left;
        }

        td input {
            width: 100%;
        }
    </style>
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
    <div class="buttons">
        <input type="submit" value="save" name="_action_save"/>
    </div>
</form:form>
</body>
</html>
