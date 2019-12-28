<%-- 
    Document   : list_user
    Created on : Nov 26, 2019, 2:55:54 PM
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
                <li style="padding-top: 15px;">List user</li>
                <li><a href="LoadBookSeverlet">Back</a></li>
            </ul>
        </div>
        <div style="width: 100%; float: left;">
            <c:if test="${requestScope.LISTUSER != null}">
                <c:if test="${not empty requestScope.LISTUSER}">
                    <table>
                        <thead>
                            <tr>
                                <th>No</th>
                                <th>UserID</th>
                                <th>Fullname</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th>Available</th>
                                <th>Role</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.LISTUSER}" var="dto" varStatus="counter">
                                <tr class="detail">
                                    <td>${counter.count}</td>
                                    <td>${dto.username}</td>
                                    <td>${dto.fullname}</td>
                                    <td>${dto.email}</td>
                                    <td>${dto.phone}</td>
                                    <td>
                                        <input type="checkbox" <c:if test="${dto.available == true}"> checked="" </c:if> onclick="return false;"/>
                                        </td>
                                        <td>${dto.roleName}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </c:if>
            </c:if>
        </div>
    </body>
</html>
