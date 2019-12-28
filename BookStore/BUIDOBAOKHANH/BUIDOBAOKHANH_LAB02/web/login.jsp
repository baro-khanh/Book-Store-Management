<%-- 
    Document   : login
    Created on : Nov 24, 2019, 1:26:29 AM
    Author     : buido
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <style>
            body{
                margin: 0px;
                font-family: 'Pacifico', cursive;
                background-color: beige;
                background-image: url(img/76248608_1920697028073762_5555552442327760896_o.jpg);
                /*background-position: center;*/
                background-size: cover;
                background-repeat: no-repeat;
                background-attachment: fixed;
                color: #2F4F4F;
            }
            ul {
                list-style-type: none;
                margin: 0;
                padding: 0;
                overflow: hidden;
                background-color:#FAEBD7;
                /*color: black;*/
            }

            li {
                float: right;
            }

            li a {
                display: block;
                /*color: white;*/
                text-align: center;
                padding: 14px 16px;
                text-decoration: none;
            }

            li a:hover {
                background-color: #2F4F4F;
                color: white;
            }
            input[type=text] {
                width: 100%;
                padding: 12px 20px;
                /*margin: 8px 0;*/
                box-sizing: border-box;
                border: none;
                background-color: #3CBC8D;
                color: white;
            }
            input[type=password] {
                width: 100%;
                padding: 12px 20px;
                margin: 8px 0;
                box-sizing: border-box;
                border: none;
                background-color: #3CBC8D;
                color: white;
            }
            input[type=submit] {
                background-color: #2F4F4F; /* Green */
                border: none;
                color: white;
                padding: 12px 20px;
                text-align: center;
                font-size: 16px;
                cursor: pointer;
                float: right;
            }
        </style>
    </head>
    <body>
        <div style="width: 100%; float: left;">
            <ul>
                <li><a href="login.jsp">Login</a></li>
                <li><a href="LoadBookSeverlet">Book</a></li>
            </ul> 
        </div>
        <div style="background-color: #FAEBD7; width: 40%; float: right; margin-top: 20px; text-align: center; margin-right: 20px;">
            <h1 style="">Login page</h1>
            <span style="color: red;">${requestScope.INVALID}</span>
            <form action="LoginServlet" method="POST" style="">
                <table style="width: 100%; padding-right: 10px;">
                    <tr>
                        <td>Username:</td>
                        <td><input type="text" name="txtUsername" value="" required=""/></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="password" name="txtPassword" value="" required=""/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Login" name="action" style="margin-bottom: 3px;"/></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>

