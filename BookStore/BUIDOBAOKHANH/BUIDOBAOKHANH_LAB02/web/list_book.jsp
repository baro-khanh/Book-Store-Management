<%-- 
    Document   : list_book
    Created on : Nov 24, 2019, 2:09:15 AM
    Author     : buido
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Book Page</title>
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
            input[type=number] {
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
            }
        </style>
        <script>
            function comfirm1(id, title) {
                var result = confirm("Do you want to continuous delete the book " + title + " ?");

                if (result) {
                    var location = "DeleteServlet?action=Delete&txtID=" + id;
                    window.location = location;
                }
            }
            function compareMinMax() {
                var min = document.getElementById("min").value;
                var max = document.getElementById("max").value;
                if (min > max) {
                    alert('Please min price value is smaller than max price value');
//                    window.location = "SearchBookServlet?action=Login";
                    return false;
                } else {
//                    alert('ok');
                    return true;
                }
            }
        </script>
    </head>
    <body>
        <div style="width: 100%; float: left; background-color: yellow;">
            <ul>
                <li style="padding-top: 15px;">LIST BOOK</li>
                <!--insert-->
                <c:if test="${sessionScope.ROLE != null}">
                    <c:if test="${sessionScope.ROLE eq "admin"}" var="checkRole">
                        <li><a href="insert_book.jsp">Insert new book</a></li>
                        <li><a href="insert_user.jsp">Insert new user</a></li>
                        <li><a href="insert_discount.jsp">Insert new discount event</a></li>
                        <li><a href="SearchOrderServlet?action=Load">All order</a></li>
                        </c:if>
                    <!--view-->
                    <c:if test="${!checkRole}">
                        <li><a href="view_cart.jsp">Your cart</a></li>
                        <li><a href="SearchOrderServlet?action=View">Your order</a></li>
                        </c:if>
                    </c:if>
                <li><a href="LogoutServlet">Logout</a></li>
            </ul>
        </div>
        <div style="width: 25%; float: left; margin-top: 10px;">
            <table>
                <tr>
                    <!--search by name-->
                <form action="SearchBookServlet" method="POST">
                    <td><input type="text" name="txtSearch" value="${requestScope.txtSearch}" placeholder="Title"/></td>
                    <td><input type="submit" name="action" value="Search by name"/></td>
                </form>
                </tr>
                <tr>
                    <!--search by range-->
                <form action="SearchBookServlet" method="POST">
                    <td>
                        <input type="number" name="txtMin" value="${requestScope.txtMin}" step="0.01" required="" min="1" placeholder="Min" id="min"/>
                        <input type="number" name="txtMax" value="${requestScope.txtMax}" step="0.01" required="" min="1" placeholder="Max" id="max"/>
                    </td>
                    <td>
                        <input type="submit" name="action" value="Search by range price" onclick="compareMinMax()"/>
                    </td>
                </form>
                </tr>
                <!--search by category-->
                <tr>
                    <c:if test="${sessionScope.LISTCATE != null}">
                        <c:if test="${not empty sessionScope.LISTCATE}">
                        <form action="SearchBookServlet" method="POST">
                            <td>
                                <select name="txtCateID" style=";">
                                    <c:forEach items="${sessionScope.LISTCATE}" var="dtoCate">
                                        <option value="${dtoCate.cateId}">${dtoCate.cateName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td><input type="submit" name="action" value="Search by category" style=""/></td>
                        </form>
                    </c:if>
                </c:if>
                </tr>
            </table>
        </div>
        <div style="width: 70%; float: left; margin-left: 40px; margin-top: 10px;">
            <c:if test="${requestScope.LISTBOOK != null}">
                <c:if test="${ not empty requestScope.LISTBOOK}" var="checkList">
                    <table style="text-align: center; border: 1px solid black; margin-bottom: 10px;">
                        <thead>
                            <tr>
                                <th>Image</th>
                                <th>Title</th>
                                <th>Author</th>
                                <th>Category</th>
                                <th>Price</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${requestScope.LISTBOOK}" var="dto">
                                <tr class="item">
                                    <td><img src="img/${dto.image}" alt="Avatar" class="image"></td>
                                    <td>${dto.title}</td>
                                    <td>${dto.author}</td>
                                    <td>${dto.cateName}</td>
                                    <td>${dto.price}</td>
                                    <c:if test="${sessionScope.ROLE != null}">
                                        <c:if test="${sessionScope.ROLE eq "user"}" var="checkRole">
                                            <td>
                                                <form action="EditServlet" method="POST">
                                                    <input type="hidden" value="${dto.bookId}" name="txtBookID"/>
                                                    <input type="submit" value="Buy" name="action"/>
                                                </form>
                                            </td>
                                        </c:if>
                                        <c:if test="${! checkRole}">
                                            <td>
                                                <input type="submit" name="action" value="Delete"  onclick="comfirm1('${dto.bookId}', '${dto.title}')" />
                                            </td>
                                            <td>
                                                <form action="EditServlet" method="POST">
                                                    <input type="hidden" value="${dto.bookId}" name="txtBookID"/>
                                                    <input type="submit" value="Edit" name="action"/>
                                                </form>
                                            </td>
                                        </c:if>
                                    </c:if>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </c:if>
        </div>

    </body>
</html>
