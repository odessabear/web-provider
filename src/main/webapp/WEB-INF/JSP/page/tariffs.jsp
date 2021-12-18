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
<%@ taglib prefix="web-provider" tagdir="/WEB-INF/tags" %>
<div id="tariffList">
    <jsp:include page="../fragment/tariffs-list.jsp"/>
    <div class="text-center hidden-print">
        <img id="loadMoreIndicator" src="${pageContext.request.contextPath}/static/img/loading.gif" class="hidden" alt="Loading...">
        <a id="loadMore" class="btn btn-primary">Load more tariffs</a>
    </div>
</div>
<web-provider:add-tariff-popup/>

