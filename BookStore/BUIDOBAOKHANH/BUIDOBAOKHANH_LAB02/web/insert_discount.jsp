<%-- 
    Document   : insert_discount
    Created on : Nov 28, 2019, 9:37:45 AM
    Author     : buido
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create discount Page</title>
        <style>
            nput[type=text], select {
                width: 100%;
                /*padding: 12px 20px;*/
                /*margin: 8px 0;*/
                box-sizing: border-box;
                border: none;
                background-color: #3CBC8D;
                color: white;
            }
            input[type=submit] {
                background-color: #2F4F4F; /* Green */
                /*border: none;*/
                color: white;
                /*font-size: 16px;*/
                cursor: pointer;
                /*float: right;*/
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
                float: left;
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
        </style>
    </head>
    <body>
        <div style="width: 100%; float: left;">
            <ul>
                <li style="padding-top: 15px;">Insert new discount</li>
                <li><a href="LoadBookSeverlet">Back</a></li>
            </ul>
        </div>
        <div style="width: 100%; float: left;">
            <span style="color: red;">${requestScope.INVALID}</span>
            <table>
                <form action="InsertDiscountServlet" method="POST">
                    <tr>
                        <td>Discount code: </td>
                        <td><input type="text" name="txtCode" required=""/></td>
                    </tr>
                    <tr>
                        <td>Percent:</td>
                        <td>
                            <select name="txtPercent">
                                <c:forEach var="i" begin="10" end="100" step="10">
                                    <option value="${i}">${i}%</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Description: </td>
                        <td><input type="text" name="txtDes"/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Create" name="action"/></td>
                    </tr>
                </form>
            </table>
        </div>
    </body>
</html>
