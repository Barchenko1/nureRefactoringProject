<%@ taglib prefix="x" uri="http://java.sun.com/jstl/xml" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<%@ page import="java.util.ArrayList"%>
<%@ taglib prefix="myTag" uri="/WEB-INF/table.tld" %>
<html>
<head>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
    <link rel="stylesheet" href="../style/css/bootstrap.css">
    <link rel="stylesheet" href="../style/css/st4.css">

</head>
<body>
<div class="row">
    <div class="col-md-4">
        <h3><a href="/create">Add new student to hotel</a></h3>
    </div>
    <div class="col-md-4 col-md-push-4">
        <h3><a href="room.jsp">See rooms in the hotel</a></h3>
    </div>
    <c:if test="${not empty sessionScope.loggedInAdmin}">
        <div class="col-md-4 col-md-push-4 d-flex justify-content-end align-items-center">
            <p class="mr-3">Welcome, ${sessionScope.loggedInAdmin.email}</p>
            <form action="logout" method="get" class="mb-0">
                <button type="submit" class="btn btn-danger">Logout</button>
            </form>
        </div>
    </c:if>
</div>
<h2 class= "center">Our students</h2>
<form class= "center">
    <myTag:table students="${students}" rooms="${rooms}" />
</form>
<a href="/generate">Automatically assign students to rooms</a>
</body>
</html>
