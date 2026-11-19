<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.cdlist.data.ProductIO" %>
<% request.setAttribute("products", ProductIO.getProducts()); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>CD list</title>
    <link rel="stylesheet" href="styles/main.css">
</head>
<body>
<main>
    <h1>CD list</h1>
    <table>
        <tr><th>Description</th><th>Price</th><th></th></tr>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.description}</td>
                <td>$${product.price}</td>
                <td>
                    <form action="cart" method="post">
                        <input type="hidden" name="action" value="add">
                        <input type="hidden" name="productCode" value="${product.code}">
                        <input type="submit" value="Add To Cart">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <p><a href="cart">View cart</a></p>
</main>
</body>
</html>
