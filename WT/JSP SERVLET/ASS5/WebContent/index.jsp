<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>
<form  method="post" action="check">
<table>
<tr>
	<td>Name</td>
	<td><input type="text" name="username"></td>
</tr>
<tr>
	<td>Password</td>
	<td><input type="password" name="password"></td>
</tr>
<tr>
	<td></td>
	<td><input type="submit" value="submit"></td>
</tr>
<tr><td><a href="newuser.jsp">new User</a></td><td colspan=2><%= (new java.util.Date()).toLocaleString()%></td></tr>
</table>
</center>
</body>
</html>