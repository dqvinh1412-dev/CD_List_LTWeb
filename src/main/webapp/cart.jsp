<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Your cart</title>
    <link rel="stylesheet" href="styles/main.css">
</head>
<body>
<main>
    <h1>Your cart</h1>
    <c:choose>
        <c:when test="${cart.empty}">
            <p>Your cart is empty.</p>
        </c:when>
        <c:otherwise>
            <table>
                <tr><th>Quantity</th><th>Description</th><th>Price</th><th>Amount</th><th></th></tr>
                <c:forEach var="item" items="${cart.items}">
                    <tr>
                        <td>
                            <form action="cart" method="post" class="quantity-form">
                                <input type="hidden" name="action" value="update">
                                <input type="hidden" name="productCode" value="${item.product.code}">
                                <input type="number" name="quantity" value="${item.quantity}" min="0">
                                <input type="submit" value="Update">
                            </form>
                        </td>
                        <td>${item.product.description}</td>
                        <td>$${item.product.price}</td>
                        <td>$${item.amount}</td>
                        <td>
                            <form action="cart" method="post">
                                <input type="hidden" name="action" value="remove">
                                <input type="hidden" name="productCode" value="${item.product.code}">
                                <input type="submit" value="Remove Item">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <p><strong>To change the quantity</strong>, enter the new quantity and click on the Update button.</p>
        </c:otherwise>
    </c:choose>
    <p><a href="index.jsp">Continue Shopping</a></p>
</main>
</body>
</html>
