<%-- 
    Document   : insert_book
    Created on : Nov 24, 2019, 3:30:59 PM
    Author     : buido
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create book Page</title>
        <style>
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
        <script>
            function validationFile() {
                var fileInput = document.getElementById('file');
                var filePath = fileInput.value;
//                window.alert(filePath);
                var allowedExtensions = /(\.jpg|\.png|\.gif)$/i;
                if (!allowedExtensions.exec(filePath)) {
                    alert('Please upload file having extensions .jpeg/.jpg/.png/.gif only.');
                    fileInput.value = '';
                    return false;
                } else {
                    alert("ok");
                    return true;
                }
            }
        </script>
        <div style="width: 100%; float: left;">
            <ul>
                <li style="padding-top: 15px;">Insert new book</li>
                <li><a href="LoadBookSeverlet">Back</a></li>
            </ul>
        </div>
        <div style="width: 100%; float: left;">
            <table>
                <form action="InsertServlet" method="POST">
                    <tr>
                        <td>Title:</td>
                        <td><input type="text" name="txtTitle" required=""/></td>
                    </tr>
                    <tr>
                        <td>Quantity:</td>
                        <td><input type="number" name="txtQuantity" min="1" step="1" required=""/></td>
                    </tr>
                    <tr>
                        <td>Image:</td>
                        <td><input type="file" name="txtFileUp" required="" id="file"/></td>
                    </tr>
                    <tr>
                        <td>Description:</td>
                        <td><textarea name="txtDes"></textarea></td>
                    </tr>
                    <tr>
                        <td>Price: </td>
                        <td><input type="number" name="txtPrice" min="1" step="0.01" required=""/></td>
                    </tr>
                    <tr>
                        <td>Author:</td>
                        <td><input type="text" name="txtAuthor" required=""/></td>
                    </tr>
                    <tr>
                        <td> Category:</td>
                        <td>
                            <select name="txtCate">
                                <c:if test="${sessionScope.LISTCATE != null}">
                                    <c:if test="${ not empty sessionScope.LISTCATE}" var="checkCate">
                                        <c:forEach items="${sessionScope.LISTCATE}" var="dtoCate">
                                            <option value="${dtoCate.cateId}">${dtoCate.cateName}</option>
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
                        <td></td>
                        <td>
                            <input type="submit" value="Insert" name="action" onclick="validationFile()"/>
                        </td>
                    </tr>
                </form>
            </table>
        </div>
    </body>
</html>
