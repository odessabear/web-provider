<%--
  Created by IntelliJ IDEA.
  User: Odessa_Bear
  Date: 12/20/2021
  Time: 1:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="web-provider" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div id="shoppingCart">
    <div class="alert alert-warning hidden-print" role="alert">To make order, please sign in</div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Tariff</th>
            <th>Price</th>
            <th>Count</th>
            <th class="hidden-print">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${CURRENT_SHOPPING_CART.items}">
            <tr id="tariff${item.tariff.id}" class="item">
                <td class="text-center"><img class="small" src="https://via.placeholder.com/400"
                                             alt="${item.tariff.name}"><br>${item.tariff.name}
                </td>
                <td class="price">${item.tariff.price}</td>
                <td class="count">${item.count}</td>
                <td class="hidden-print">
                    <a class="btn btn-danger remove-tariff" data-id-tariff="${item.tariff.id}" data-count="1">Remove</a>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="2" class="text-right"><strong>Total:</strong></td>
            <td colspan="2" class="total">${CURRENT_SHOPPING_CART.totalCost}</td>
        </tr>
        </tbody>
    </table>
    <div class="row hidden-print">
        <div class="col-md-4 col-md-offset-4 col-lg-2 col-lg-offset-5">
            <a href="login-page.jsp" class="btn btn-primary btn-block"> Sign in</a>
        </div>
    </div>
</div>
