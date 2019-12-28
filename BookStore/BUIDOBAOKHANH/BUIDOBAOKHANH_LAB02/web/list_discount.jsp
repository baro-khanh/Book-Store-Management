<%-- 
    Document   : list_discount
    Created on : Nov 28, 2019, 1:50:49 PM
    Author     : buido
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List discount Page</title>
        <style>
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
            table{
                width: 100%;
                margin-top: 30px;
                border-collapse: collapse;
                color: #2F4F4F;
                text-align: center;
            }
            .detail:hover{
                background:rgba(0,0,0,0.5);
                /*border: 0.5px solid tomato;*/
            }
        </style>
    </head>
    <body>
        <div style="width: 100%; float: left;">
            <ul>
                <li style="padding-top: 15px;">All discount event</li>
                <li><a href="LoadBookSeverlet">Back</a></li>
            </ul>
        </div>
        <c:if test="${requestScope.LIST_DISCOUNT != null}">
            <c:if test="${not empty requestScope.LIST_DISCOUNT}">
                <table>
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Discount code</th>
                            <th>Desciption</th>
                            <th>Percent</th>
                            <th>Import date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.LIST_DISCOUNT}" var="dto" varStatus="counter" >
                            <tr class="detail">
                                <td>${counter.count}</td>
                                <td>${dto.discountCode}</td>
                                <td>${dto.description}</td>
                                <td>${dto.percent} %</td>
                                <td>${dto.importDate}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </c:if>
        </c:if>
    </body>
</html>
