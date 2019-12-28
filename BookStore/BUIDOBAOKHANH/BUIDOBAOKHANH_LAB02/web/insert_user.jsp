<%-- 
    Document   : insert_user
    Created on : Nov 25, 2019, 4:11:59 PM
    Author     : buido
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create user Page</title>
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
            input[type=email] {
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
                <li style="padding-top: 15px;">Insert new user</li>
                <li><a href="LoadBookSeverlet">Back</a></li>
            </ul>
        </div>
        <div style="width: 100%; float: left;">
            <span style="color: red;">${requestScope.INVALID}</span>
            <table>
                <form action="InsertUserServlet" method="POST">
                    <tr>
                        <td>Username:</td>
                        <td> <input type="text" required="" name="txtUsername"/></td>
                    </tr>
                    <tr>
                        <td>Password: </td>
                        <td><input type="password" required="" name="txtPassword" id="password"/></td>
                    </tr>
                    <tr>
                        <td>Comfirm password: </td>
                        <td><input type="password" required="" name="" id="comfirm"/></td>
                    </tr>
                    <tr>
                        <td>Fullname: </td>
                        <td><input type="text" required="" name="txtFullname"/></td>
                    </tr>
                    <tr>
                        <td>Email: </td>
                        <td><input type="email" required="" name="txtEmail"/></td>
                    </tr>
                    <tr>
                        <td>Phone: </td>
                        <td><input type="text" required="" name="txtPhone"/></td>
                    </tr>
                    <tr>
                        <td>
                            Role: 
                        </td>
                        <td>
                            <select name="txtRoleID">
                                <c:if test="${sessionScope.LISTROLE != null}">
                                    <c:if test="${ not empty sessionScope.LISTROLE}">
                                        <c:forEach var="dto" items="${sessionScope.LISTROLE}">
                                            <option value="${dto.roleID}">${dto.roleName}</option>
                                        </c:forEach>
                                    </c:if>
                                </c:if>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Insert" name="action"/>  </td>
                    </tr>          
                </form>
            </table>
        </div>
        <script>
            var pass = document.getElementById("password");
            var comfirm = document.getElementById("comfirm");
            function checkPassword() {
                if (pass.value !== comfirm.value) {
                    comfirm.setCustomValidity("Comfirm password is not match");
                } else {
                    comfirm.setCustomValidity("");
                }
            }
            pass.onchange = checkPassword;
            comfirm.onkeyup = checkPassword;
        </script>
    </body>
</html>
