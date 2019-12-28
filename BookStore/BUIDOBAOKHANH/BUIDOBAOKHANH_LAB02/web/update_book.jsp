<%-- 
    Document   : update_book
    Created on : Nov 25, 2019, 8:41:23 AM
    Author     : buido
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update book Page</title>
        <style>
            .img{
                height: 300px;
                width: 300px;
            }
            input[type=text], select, file, textArea {
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
            input[type=number] {
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
                <li style="padding-top: 15px;">Update book</li>
                <li><a href="LoadBookSeverlet">Back</a></li>
            </ul>
        </div>
        <div style="width: 100%; float: left;">
            <form action="UpdateServlet" method="POST">
            <div style="width: 30%; float: left; background-color: blue;">
                <img src="img/${requestScope.DTO.image}"  class="img" alt="Avatar"/>
                <input type="hidden" name="txtImage" value="${requestScope.DTO.image}"/>
                Image: <input type="file" name="txtFileUp" id="file"/><br>
            </div>
            <div style="width: 70%; float: left; background-color: orange;">
                <table>
                    <tr>
                        <td> BookID:</td>
                        <td> <input type="text" name="txtBookID" value="${requestScope.DTO.bookId}" readonly=""/></td>
                    </tr>
                    <tr>
                        <td>Title: </td>
                        <td><input type="text" name="txtTitle" required="" value="${requestScope.DTO.title}"/></td>
                    </tr>
                    <tr>
                        <td>Quantity: </td>
                        <td><input type="number" name="txtQuantity" min="1" step="1" required="" value="${requestScope.DTO.quantity}"/></td>
                    </tr>
                    <tr>
                        <td>Description: </td>
                        <td><textarea name="txtDes" style="height: 100px;">${requestScope.DTO.description}</textarea></td>
                    </tr>
                    <tr>
                        <td>Price: </td>
                        <td><input type="number" name="txtPrice" min="1" step="0.01" required="" value="${requestScope.DTO.price}"/></td>
                    </tr>
                    <tr>
                        <td>Author: </td>
                        <td><input type="text" name="txtAuthor" required="" value="${requestScope.DTO.author}"/></td>
                    </tr>
                    <tr>
                        <td>Category: </td>
                        <td>
                            <select name="txtCate">
                                <c:if test="${sessionScope.LISTCATE != null}">
                                    <c:if test="${ not empty sessionScope.LISTCATE}" var="checkCate">
                                        <c:forEach items="${sessionScope.LISTCATE}" var="dtoCate">
                                            <option value="${dtoCate.cateId}"<c:if test="${dtoCate.cateId == requestScope.DTO.cateId }">selected=""</c:if>>${dtoCate.cateName}</option>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${!checkCate}">
                                        No category is found
                                    </c:if>
                                </c:if>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Import date: </td>
                        <td><input type="text" value="${requestScope.DTO.importDate}" readonly=""/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Update" name="action" onclick="validationFile()"/></td>
                    </tr>
                </table>
            </div>
        </form>
        </div>
    </body>
</html>
