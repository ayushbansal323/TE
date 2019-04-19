<%-- 
    Document   : index
    Created on : 19 Apr, 2019, 2:27:52 PM
    Author     : aaditya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bank transaction</title>
    </head>
    <body>
        <h1>Bankportal</h1>
        <form action="result" method="post">
            <input type="text" name="amt" placeholder="Amount"></input><br>
            <input type="radio" name="operation" value="add">Deposit<br>
            <input type="radio" name="operation" value="sub">WithDraw<br>
            <button type="submit">Submit</button>
        </form>
    </body>
</html>
