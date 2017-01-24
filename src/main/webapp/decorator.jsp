<%--
  Created by IntelliJ IDEA.
  User: ashrafhasan
  Date: 1/18/17
  Time: 12:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<%@ include file="/WEB-INF/taglibs.jsp" %>
<link rel="stylesheet" href='<c:url value="/css/style.css"/>' type="text/css"/>
<head>
    <meta http-equiv="content-type" content="text/html; charset=iso-8859-1"/>
    <style type="text/css">
        div#container {
            width: 720px;
            margin-left: auto;
            margin-right: auto;
        }
    </style>
    <title>
    </title>
</head>
<body>

<div id="container">
    <div id="intro">
        <div id="pageHeader">
            <h1><a href=<c:url value="/home/"/>>Dashboard</a></h1>
            <hr style=""/>
            <sitemesh:write property='head'/>
        </div>
        <div id="preamble">
            <sitemesh:write property='body'/>
        </div>
    </div>
    <div id="supportingText">
        <div id="footer">
        </div>
    </div>
</div>
</body>
</html>
