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
<div class="alert " role="alert">
    <c:out value="${errorMessage}"/>
</div>

<h1 align="center">Add Student</h1>
<br/>
<form name="create" method="post">
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
         <td align="right">Confirm password</td>
         <td>
             <input name="cpassword" type="password" required>
         </td>
    </tr>
    <tr>
        <td><p> <input class="btn btn-success" method="post" type="submit" name="Submit" value="OK"> <a href="loginForm.jsp" class="btn btn-success" role="button" aria-pressed="true">Cancel</a></p></td>
    </tr>
</table>
</form>

</body>
</html>
