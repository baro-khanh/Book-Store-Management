<%-- 
    Document   : view_order
    Created on : Nov 27, 2019, 3:10:13 PM
    Author     : buido
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View order Page</title>
        <style>
            .image{
                width: 100px;
                height: 100px;
                padding-bottom: -10px;
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
            .item:hover{
                background-color: #2F4F4F;
                color: white;
            }

            table{
                width: 100%;
                border-collapse: collapse;
            }
            input[type=text], select {
                width: 100%;
                /*padding: 12px 20px;*/
                /*margin: 8px 0;*/
                box-sizing: border-box;
                border: none;
                background-color: #3CBC8D;
                color: white;
            }
            input[type=date] {
                width: 100%;
                padding: 8px 10px;
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
                width: 100%;
            }
            table{
                text-align: center;
            }
        </style>
    </head>
    <body>
        <div style="width: 100%; float: left;">
            <ul>
                <li style="padding-top: 15px;">
                    <c:if test="${sessionScope.ROLE eq "admin"}" var="checkRole">
                        All order
                    </c:if>
                    <c:if test="${!checkRole}">
                        ${sessionScope.USER}'s order
                    </c:if>
                </li>
                <li><a href="LoadBookSeverlet">Back</a></li>
            </ul>
        </div>
        <div  style="width: 25%; float: left; margin-top: 10px;">
            <form action="SearchOrderServlet" method="POST">
                <input type="date" name="txtDate" value=""/>
                <input type="submit" name="action" value="Search by date"/>
            </form>
            <c:if test="${checkRole}">
                <form action="SearchOrderServlet" method="POST">
                    <input type="text" name="txtSearch" placeholder="Account"/>
                    <input type="submit" name="action" value="Search by Account"/>
                </form>
            </c:if>
        </div>
        <div style="width: 70%; float: left; margin-left: 40px; margin-top: 10px;">
            <c:if test="${requestScope.LIST_ORDER != null}">
                <c:if test="${ not empty requestScope.LIST_ORDER}" var="checkListOrder">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>Bill code</th>
                                <th>Total</th>
                                <th>Order date</th>
                                    <c:if test="${checkRole}">
                                    <th>Account</th>
                                    </c:if>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.LIST_ORDER}" var="dto">
                                <tr>
                                    <td><a href="SearchDetailOrderServlet?txtOrderID=${dto.orderID}" >#${dto.orderID}</a></td>
                                    <td>${dto.total}</td>
                                    <td>${dto.datetime}</td>
                                    <c:if test="${checkRole}">
                                        <td>${dto.userID}</td>
                                        <td>
                                            <form action="SearchDetailOrderServlet" method="POST">
                                                <input type="hidden" value="${dto.orderID}" name="txtOrderID"/>
                                                <input type="submit" value="Detail"/>
                                            </form>
                                        </td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </c:if>
                <c:if test="${! checkListOrder}">
                    You have no bill
                </c:if>
            </c:if>
        </div>
    </body>
</html>
