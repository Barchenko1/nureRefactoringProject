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

<h1 align="center">Edit Room</h1>
<br/>
<form name="updateRoom" method="post">
    <table align="center">
        <tr>
            <td align="right">Number</td>
            <td>
                <input name="number" type="text" required value='<%=request.getAttribute("number")%>'/>
            </td>
        </tr>
        <tr>
            <td align="right">Capacity</td>
            <td>
                <input name="capacity" type="text" required value='<%=request.getAttribute("capacity")%>'/>
            </td>
        </tr>
        <tr>
            <td align="right">Flour</td>
            <td>
                <input name="flour" type="text" required value='<%=request.getAttribute("flour")%>'/>
            </td>
        </tr>
        <tr>
            <td><p> <input class="btn btn-success" method="post" type="submit" name="Submit" value="OK"> <a href="room.jsp" class="btn btn-success" role="button" aria-pressed="true">Cancel</a></p></td>
        </tr>
    </table>
</form>
</body>
</html>

