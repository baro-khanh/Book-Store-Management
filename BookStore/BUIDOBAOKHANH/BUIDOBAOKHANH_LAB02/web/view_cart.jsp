<%-- 
    Document   : view_cart
    Created on : Nov 25, 2019, 2:56:53 PM
    Author     : buido
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            function myFunction(id) {
                var x = confirm("Do you want to delete this book in your cart ? ");
                if (x == true) {
                    var location = "DeleteCartDetailServlet?action=Delete&txtBookID=" + id;
                    window.location = location;
                }
            }
        </script>
    </head>
    <body>
        <h1>${sessionScope.USER}'s cart</h1>
        <span style="color: red;">${requestScope.INVALID}</span><br>
        <c:if test="${sessionScope.CART != null}">
            <div style="width: 100%; float: left; background-color: pink;">
                <c:if test="${ not empty sessionScope.CART.getCart() }" var="checkCart">
                    <div style="width: 35%; float: left; background-color: yellow; margin-right: 20px;">
                        Provisional: ${sessionScope.CART.getTotal()}
                        <hr>
                        Total: ${sessionScope.CART.getTotal() * requestScope.AMOUNT }
                        <hr>
                        Sale code: 
                        <form action="CheckSaleCodeServlet" method="POST">
                            <input type="text" name="txtSale" value="${requestScope.SALECODE}"/>
                            <input type="submit" name="action" value="Agree"/>
                        </form>
                            <span style="color: orange;">${requestScope.MESS}</span>
                    </div>
                    <div style="width: 60%; float: left; background-color: pink;">
                        <table border="1">
                            <thead>
                                <tr>
                                    <th>No</th>
                                    <th>Title</th>
                                    <th>Unit Price</th>
                                    <th>Quantity</th>
                                    <th>Sub Price</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${sessionScope.CART.getCart().values()}" var="dto" varStatus="counter">
                                    <tr>
                                <form action="UpdateCartDetailServlet" method="POST">
                                    <td>${counter.count}</td>
                                    <td>${dto.title}</td>
                                    <td>${dto.price}</td>
                                    <td><input type="number" step="1" value="${dto.quantity}" min="1" required="" name="txtQuantity"/> </td>
                                    <td>${dto.price * dto.quantity}</td>
                                    <td>
                                        <input type="hidden" value="${dto.bookId}" name="txtBookID"/>
                                        <input type="submit" value="Update" name="action"/>
                                    </td>
                                </form>
                                <td>
                                    <button onclick="myFunction('${dto.bookId}')">Delete</button>
                                </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <button onclick="window.location.href = 'SearchUserServlet?action=Proceed'">Proceed to ordering</button>
                    </div>
                </c:if>
            </div>
            <c:if test="${!checkCart}">
                <div style="width: 100%; background-color: beige;">
                    <span style="color: orange;">No book in your cart</span>
                    <form action="SearchBookServlet" method="POST">
                        <input type="submit" value="Continuous shopping" name="action">
                    </form>
                </div>
            </c:if>
        </c:if>        
    </body>
</html>
