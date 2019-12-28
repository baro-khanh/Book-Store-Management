<%-- 
    Document   : confirm_cart
    Created on : Nov 26, 2019, 10:56:04 PM
    Author     : buido
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Comfirm order</h1>
        <div style="width: 100%; float: left;">
            <div style="width: 35%; float: left; background-color: gray; border: 1px solid red; margin-right: 10px;">
                <h3>User information</h3>
                <b>Fullname: ${requestScope.DTO.fullname}</b><br>
                Email: ${requestScope.DTO.email}<br>
                Phone: ${requestScope.DTO.phone}<br>
            </div>
            <form action="ConfirmServlet" method="POST">
                <div style="width: 60%; float: left; background-color: plum; border: 1px solid red;">
                    <h3>Order information: ${sessionScope.CART.getCart().size()} <button onclick="window.location.href = 'view_cart.jsp'">Edit</button></h3>
                    <c:forEach items="${sessionScope.CART.getCart().values()}" var="dto">
                        ${dto.quantity} x ${dto.title} = <span style="color: blue;">${dto.quantity * dto.price}</span>
                        <hr>
                    </c:forEach>
                    Provisional: ${sessionScope.CART.getTotal()}<br>
                    Discount: <c:if test="${sessionScope.DISCOUNT != null}" var="checkDiscount">${sessionScope.DISCOUNT} %</c:if>
                    <c:if test="${!checkDiscount}">No discount</c:if><br>
                    Total: <c:if test="${checkDiscount}"><input type="text" value="${sessionScope.CART.getTotal() * ( 100 - sessionScope.DISCOUNT) /100 }" name="txtTotal" readonly=""/></c:if>
                    <c:if test="${!checkDiscount}" ><input type="text" value="${sessionScope.CART.getTotal()}" name="txtTotal" readonly=""/></c:if>
                </div>
        </div>
        <div style="width: 100%; float: left;">
            <input type="submit" value="Confirm" name="action" style="margin-left: 400px; margin-top: 100px;"/>
        </div>
    </form>
</body>
</html>
