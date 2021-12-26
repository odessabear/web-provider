<%--
  Created by IntelliJ IDEA.
  User: Odessa_Bear
  Date: 18.12.2021
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#providerNav" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/tariffs">TAKOGONET</a>
        </div>
        <div class="collapse navbar-collapse" id="providerNav">
            <ul id="currentShoppingCart" class="nav navbar-nav navbar-right ${CURRENT_SHOPPING_CART == null ? 'hidden' : ''}">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                        <i class="fa fa-shopping-cart" aria-hidden="true"></i> Shopping cart (<span class="total-count">${CURRENT_SHOPPING_CART.totalCount}</span>)<span class="caret"></span>
                    </a>
                    <div class="dropdown-menu shopping-cart-desc">
                        Total count: <span class="total-count">${CURRENT_SHOPPING_CART.totalCount}</span><br>
                        Total cost: <span class="total-cost">${CURRENT_SHOPPING_CART.totalCost}</span><br>
                        <a href="/shopping-cart" class="btn btn-primary btn-block">View cart</a>
                    </div>
                </li>
            </ul>
            <a href="login-page.html" class="btn btn-primary navbar-btn navbar-right sign-in"> Sign in</a>
        </div>
    </div>
</nav>
