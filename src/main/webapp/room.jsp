<%@ taglib prefix="x" uri="http://java.sun.com/jstl/xml" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jstl/sql" %>
<%@ page import="java.util.ArrayList"%>
<%@ taglib prefix="myRooms" uri="/WEB-INF/rooms.tld" %>
<html>
    <head>
        <%@ include file="/WEB-INF/jspf/head.jspf" %>
       <link rel="stylesheet" href="../style/css/bootstrap.css">
       <link rel="stylesheet" href="../style/css/st4.css">

    </head>
          <body>
          <div class="row">
            <div class="col-md-4">
                <h3><a href="/createRoom">Add new room to hotel</a></h3>
            </div>
            <div class="col-md-4 col-md-push-4">
                <h3><a href="/index.jsp">Return</a></h3>
            </div>
          </div>
          <h2 class= "center">Our rooms</h2>
           <form class= "center">
                <myRooms:table students="${students}" rooms="${rooms}" />
           </form>
          </body>
</html>
