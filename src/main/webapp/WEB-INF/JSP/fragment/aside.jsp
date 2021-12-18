<%--
  Created by IntelliJ IDEA.
  User: Odessa_Bear
  Date: 18.12.2021
  Time: 23:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="visible-xs-block xs-option-container">
    <a class="pull-right" data-toggle="collapse" href="#serviceCatalog">Service Catalog <span
            class="caret"></span></a>
    <a data-toggle="collapse" href="#findTariffs">Find Tariffs <span class="caret"></span></a>
</div>
<!-- Categories-->
<div id="serviceCatalog" class="panel panel-success collapse">
    <div class="panel-heading">Service Catalog</div>
    <div class="list-group">
        <a href="/tariffs" class="list-group-item"><span class="badge">3</span>Internet</a>
        <a href="/tariffs" class="list-group-item"><span class="badge">3</span>Telephony</a>
        <a href="/tariffs" class="list-group-item"><span class="badge">3</span>IP-TV</a>
        <a href="/tariffs" class="list-group-item"><span class="badge">3</span>Cable TV</a>
    </div>
</div>
<!-- Sorting form -->
<form class="sort" action="/sort">
    <div id="findTariffs" class="panel panel-success collapse">
        <div class="panel-heading">Filters</div>
        <div class="panel-body">
            <div class="more-options">
                <a data-toggle="collapse" href="#sortingOptions"> Choose Filter <span class="caret"></span></a>
            </div>
        </div>
        <div id="sortingOptions" class="collapse">
            <div class="panel-heading">Alphabetic sorting</div>
            <div class="panel-body tariffs">
                <div class="form-group">
                    <div class="checkbox">
                        <label><input type="checkbox" name="ta" value="1" class="sorting-option">A ->
                            Z</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="checkbox">
                        <label><input type="checkbox" name="ta" value="2" class="sorting-option">Z ->
                            A</label>
                    </div>
                </div>
            </div>
            <div class="panel-heading">Price sorting</div>
            <div class="panel-body tariffs">
                <div class="form-group">
                    <div class="checkbox">
                        <label><input type="checkbox" name="ta" value="1" class="sorting-option">Up </label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="checkbox">
                        <label><input type="checkbox" name="ta" value="2" class="sorting-option">Down
                        </label>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
<!-- /sorting form -->
<!-- <div class="panel panel-primary">
<div class="panel-heading">Admin Panel</div>
<div class="list-group">
<a href="#" class="list-group-item">Create Tariff</a>
<a href="#" class="list-group-item">Update Tariff</a>
<a href="#" class="list-group-item">Delete Tariff</a>
<a href="#" class="list-group-item">Register Client</a>
<a href="#" class="list-group-item">Block Client</a>
<a href="#" class="list-group-item">Unblock Client</a>
</div>
</div> -->
