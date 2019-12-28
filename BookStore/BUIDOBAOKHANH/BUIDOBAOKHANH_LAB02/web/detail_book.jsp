<%-- 
    Document   : detail_book
    Created on : Nov 25, 2019, 12:44:47 AM
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
                width: 400px;
                height: 400px;
            }
        </style>
    </head>
    <body>
        <div>
            <div style="width: 40%; float: left;background-color: pink;">
                <img src="img/${requestScope.DTO.image}" class="image" alt="Avatar"/>
            </div>
            <div style="width: 58%;float: left; background-color: yellow; height: 400px;">
                <form action="AddCartServlet" method="POST">
                    <input type="hidden" value="${requestScope.DTO.bookId}" name="txtBookID"/>
                    Title: <input type="text" value="${requestScope.DTO.title}" readonly=""/><br>
                    Author: <input type="text" readonly="" value="${requestScope.DTO.author}" /><br>
                    Price: <input type="number" readonly="" value="${requestScope.DTO.price}" /><br>
                    Quantity: <input type="number" name="txtQuantity" min="1" step="1" required="" value="1"/>
                    <c:if test="${sessionScope.ROLE != null}">
                        <c:if test="${sessionScope.ROLE eq "user"}">
                            <input type="submit" value="+ Add to cart" name="action"/>
                        </c:if>
                    </c:if>
                </form>
            </div>
            <div style="width: 100%; float: left;">
                Description: <textarea readonly="" >${requestScope.DTO.description}</textarea><br>
                Category: <input type="text" readonly="" value="${requestScope.DTO.cateName}" /><br>
            </div>
        </div>
    </body>
</html>
