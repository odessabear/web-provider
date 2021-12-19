<%--
  Created by IntelliJ IDEA.
  User: Odessa_Bear
  Date: 12/20/2021
  Time: 1:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <tr id="tariff278009" class="item">
            <td class="text-center"><img class="small" src="" alt="Tariff Name"><br>Tariff Name</td>
            <td class="price">180.00</td>
            <td class="count">1</td>
            <td class="hidden-print">
                <a class="btn btn-danger remove-tariff" data-id-tariff="278009" data-count="1">Remove</a>
            </td>
        </tr>
        <tr id="tariff278010" class="item">
            <td class="text-center"><img class="small" src="" alt="Tariff Name"><br>Tariff Name</td>
            <td class="price">250.00</td>
            <td class="count">1</td>
            <td class="hidden-print">
                <a class="btn btn-danger remove-tariff" data-id-tariff="278010" data-count="1">Remove</a><br><br>
            </td>
        </tr>
        <tr>
            <td colspan="2" class="text-right"><strong>Total:</strong></td>
            <td colspan="2" class="total">430.00</td>
        </tr>
        </tbody>
    </table>
    <div class="row hidden-print">
        <div class="col-md-4 col-md-offset-4 col-lg-2 col-lg-offset-5">
            <a href="login-page.html" class="btn btn-primary btn-block"> Sign in</a>
        </div>
    </div>
</div>
