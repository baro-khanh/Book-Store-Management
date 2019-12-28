<%-- 
    Document   : error
    Created on : Nov 23, 2019, 10:43:10 PM
    Author     : buido
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Error page</h1>
        <span style="color: red;">${requestScope.ERROR}</span>
    </body>
</html>
