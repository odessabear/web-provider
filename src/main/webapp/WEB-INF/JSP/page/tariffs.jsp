<%--
  Created by IntelliJ IDEA.
  User: Odessa_Bear
  Date: 14.12.2021
  Time: 23:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:forEach var="p" items="${tariffs }">
    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3 col-xlg-2">
        <div id="tariff${tariff.id }" class="panel panel-default tariff">
            <h2>${fn:toUpperCase(p.name) }</h2>
            <div class="price">Price:
                <fmt:formatNumber value="${p.price }" type="currency" currencyCode="USD"/>
            </div>
            <div class="created">Created:
                <fmt:formatDate value="${p.created }" pattern="yyyy-MM-dd"/>
            </div>
            <div class="created">Created:
                <fmt:formatDate value="${p.created }" dateStyle="long" type="date"/>
            </div>
        </div>
    </div>
</c:forEach>
