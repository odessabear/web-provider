<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Odessa_Bear
  Date: 19.12.2021
  Time: 0:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<c:forEach var="tariff" items="${tariffs}">
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 col-xlg-2">
        <!-- tariff DATA -->
        <div id="tariff${tariff.id}" class="panel panel-default tariff ">
            <div class="panel-body">
                <div class="thumbnail">
                    <img src="https://via.placeholder.com/400" alt="${tariff.name}">
                    <div class="desc">
                        <div class="cell">
                            <p><span class="title">Details</span>${tariff.description}</p>
                        </div>
                    </div>
                </div>
                <h4 class="name">${tariff.name}</h4>
                <div class="code">Code: ${tariff.id}</div>
                <div class="price">${tariff.price}</div>
                <a class="btn btn-primary pull-right buy-btn" data-id-tariff="${tariff.id}">Buy</a>
                <div class="list-group">
                                        <span class="list-group-item"> <small>Category:</small> <span
                                                class="category">${tariff.category}</span></span>
                </div>
            </div>
        </div>
        <!-- tariff DATA -->
    </div>
</c:forEach>

