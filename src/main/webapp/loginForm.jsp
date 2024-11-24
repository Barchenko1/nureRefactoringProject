<%@ taglib prefix="x" uri="http://java.sun.com/jstl/xml" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<%@ page import="java.util.ArrayList"%>

<html>
    <head>
        <%@ include file="/WEB-INF/jspf/head.jspf" %>
        <link rel="stylesheet" href="../style/css/bootstrap.css">
        <link rel="stylesheet" href="../style/css/st4.css">

    </head>
<body>

<h1 align="center">Login</h1>
<br/>
<div align="center" class="alert " role="alert">
                <c:out value="${errorMessage}"/>
</div>
<form name="login" method="post" action="login">
<table align="center">
    <tr>
        <td align="right">Email</td>
        <td>
            <input name="email" type="text" required>
        </td>
    </tr>
    <tr>
        <td align="right">Password</td>
        <td>
            <input name="password" type="password" required>
        </td>
    </tr>
    <tr>
        <td><p> <input class="btn btn-success" method="post" type="submit" name="Submit" value="Submit"> <a href="/createAdmin">Registration</a></p></td>
    </tr>
</table>
</form>

</body>
</html>
