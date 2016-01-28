<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <div align="center">
            <h1>Contact List</h1>
            <table border="1">
                <th>No</th>
                <th>Name</th>
                <th>Country</th>
                
                <c:forEach var="person" items="${listData}" varStatus="status">
	                <c:if test="${person.getClass().name =='org.redhat.model.Person'}">
		                <tr>
		                    <td>${status.index + 1}</td>
		                    <td>${person.name}</td>
		                    <td>${person.country}</td>
		                </tr>
	                </c:if>
                </c:forEach>             
            </table>
            <table border="1">
                <th>No</th>
                <th>Name</th>
                <th>User Id</th>
                 
				<c:forEach var="user" items="${listData}" varStatus="status">
	                <c:if test="${user.getClass().name =='org.redhat.model.User'}">
		                <tr>
		                    <td>${status.index + 1}</td>
		                    <td>${user.name}</td>
		                    <td>${user.username}</td>
		                </tr>
	                </c:if>
                </c:forEach>
			</table>
        </div>
    </body>
</html>