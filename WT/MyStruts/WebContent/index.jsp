<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
 <%@ taglib uri="/struts-tags" prefix="s" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<s:form action="get" method="post">
	<s:textfield label="username" name="username"></s:textfield>
	<br>
	<s:password label="password" name="password"></s:password>
	<br>
	<s:submit name="login"></s:submit>
</s:form>

<a href="newuser.jsp">New user? Register</a>
</body>
</html>