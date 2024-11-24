<!doctype html>
<%@ taglib prefix="x" uri="http://java.sun.com/jstl/xml" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<%@ page import="java.util.ArrayList"%>
<html>
<head>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
    <link rel="stylesheet" href="style/css/bootstrap.css">
    <link rel="stylesheet" href="style/css/st4.css">
    <script>
        var message = $('#lname').text();
    </script>
</head>
<body>

<div class="alert" role="alert">
    <c:out value="${errorMessage}"/>
</div>
<c:if test="${not empty sessionScope.loggedInAdmin}">
    <div class="d-flex justify-content-end align-items-center">
        <p class="mr-3">Welcome, ${sessionScope.loggedInAdmin.email}</p>
        <form action="logout" method="get" class="mb-0">
            <button type="submit" class="btn btn-danger">Logout</button>
        </form>
    </div>
</c:if>
<h1 align="center">Edit Student</h1>
<br/>
<form name="update" method="post">
    <table align="center">
        <tr>
            <td align="right">First Name</td>
            <td>
                <input name="fname" type="text" required value='<%=request.getAttribute("fname")%>'/>
            </td>
        </tr>
        <tr>
            <td align="right">Last Name</td>
            <td>
                <input name="lname" type="text" required value='<%=request.getAttribute("lname")%>'/>
            </td>
        </tr>
        <tr>
            <td align="right">Age</td>
            <td>
                <input name="age" type="text" required value='<%=request.getAttribute("age")%>'/>
            </td>
        </tr>
        <tr>
            <td align="right">Gender</td>
            <td>
                <input name="gender" type="text" required value='<%=request.getAttribute("gender")%>'/>
            </td>
        </tr>
        <tr>
            <td align="right">Group</td>
            <td>
                <input name="group" type="text" required value='<%=request.getAttribute("group")%>'/>
            </td>
        </tr>
        <tr>
            <td align="right">Room</td>
            <td>
                <input name="room" type="text" required value='<%=request.getAttribute("room")%>'/>
            </td>
        </tr>
        <tr>
            <td><p> <input class="btn btn-success" method="post" type="submit" name="Submit" value="OK"> <a href="index.jsp" class="btn btn-success" role="button" aria-pressed="true">Cancel</a></p></td>
        </tr>
    </table>
</form>
</body>
</html>

