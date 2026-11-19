<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html><head><meta charset="UTF-8"><title>Murach's Java Servlets and JSP</title>
<link rel="stylesheet" href="styles/main.css" type="text/css"/></head><body>
<h1>Your cart</h1>
<c:choose><c:when test="${cart.count == 0}"><p>Your cart is empty.</p></c:when><c:otherwise>
<table><tr><th>Quantity</th><th>Description</th><th class="right">Price</th><th class="right">Amount</th><th></th></tr>
<c:forEach var="item" items="${cart.items}"><tr><td><form action="cart" method="post">
<input type="hidden" name="action" value="cart"><input type="hidden" name="productCode" value="${item.product.code}">
<input type="text" size="2" name="quantity" value="${item.quantity}"><input type="submit" value="Update"></form></td>
<td>${item.product.description}</td><td class="right">${item.product.priceCurrencyFormat}</td><td class="right">${item.totalCurrencyFormat}</td><td>
<form action="cart" method="post"><input type="hidden" name="action" value="cart"><input type="hidden" name="productCode" value="${item.product.code}">
<input type="hidden" name="quantity" value="0"><input type="submit" value="Remove Item"></form></td></tr></c:forEach></table>
<p><strong>To change the quantity</strong>, enter the new quantity and click on the Update button.</p>
</c:otherwise></c:choose>
<form action="cart" method="get"><input type="hidden" name="action" value="shop"><input type="submit" value="Continue Shopping"></form>
<form action="cart" method="get"><input type="hidden" name="action" value="checkout"><input type="submit" value="Checkout"></form>
</body></html>
