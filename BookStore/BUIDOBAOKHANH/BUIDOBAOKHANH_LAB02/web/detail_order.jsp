<%-- 
    Document   : detail_order
    Created on : Nov 28, 2019, 12:23:31 AM
    Author     : buido
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
                <li style="padding-top: 15px;">Detail of order #${requestScope.ORDERID}</li>
                <li><a href="LoadBookSeverlet">Back</a></li>
            </ul>
        </div>
        <div>
            <c:if test="${requestScope.LIST_DETAIL !=null }">
                <c:if test="${ not empty requestScope.LIST_DETAIL}" var="checkBill">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>Book title</th>
                                <th>Amount</th>
                                <th>Subprice</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.LIST_DETAIL}" var="dto">
                                <tr class="detail">
                                    <td>${dto.title}</td>
                                    <td>${dto.amount}</td>
                                    <td>${dto.subPrice}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </c:if>
                <c:if test="${! checkBill}">
                    This bill is empty
                </c:if>
            </c:if>
        </div>
    </body>
</html>
