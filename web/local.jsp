<%--
  Created by IntelliJ IDEA.
  User: karin
  Date: 18.09.2018
  Time: 23:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<span style="float: right">
    <a href="?lang=en">en</a>
    <a href="?lang=de">ru</a>
</span>

<p><spring:message code="label.button.registration"/></p>
<p><spring:message code="label.button.login"/></p>

</body>
</html>
