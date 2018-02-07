<%@page import="java.util.Objects"%>
<%@page import="java.util.List"%>
<%@ page import="com.denisgl.cache.realization.UserCache" %>
<%@ page import="com.denisgl.dao.entities.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%//Nobody can have email admin except admin 
Object mail = session.getAttribute("email");
if(!Objects.equals(mail, "admin")){
	out.println("You should not be here hacker!!!");
} else {
%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Data</title>
</head>
<body>
<h1>Users</h1>
<br/>
<table border="1" cellpadding="3" cellspacing="1">
<tr>
   <th>ID</th>
   <th>First Name</th>
   <th>Last Name</th>
   <th>Phone</th>
   <th>Email</th>
</tr>
<tr>
<%
List<User> userList = UserCache.getCache().getAll();
for (Object userObject : userList) {
	User user = (User) userObject;
	out.println("<td>" + user.getId() + "</td>");
    out.println("<td>" + user.getFirstname() + "</td>");
    out.println("<td>" + user.getLastname() + "</td>");
    out.println("<td>" + user.getPhone() + "</td>");
    out.println("<td>" + user.getEmail() + "</td>");
%>
</tr>

<%       
}
%>

</table>
</body>
</html>
<%}%>