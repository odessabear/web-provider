<%--
  Created by IntelliJ IDEA.
  User: Odessa_Bear
  Date: 12/20/2021
  Time: 10:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="page-centered">
    <div class="logo">
        <p>TAKOGONET</p>
    </div>
    <h1 class="login-h1">Sign In or Sign Up</h1>

    <form method="post">
        <div class="form-group">
            <label>
                <input type="text" placeholder="Email" required class="form-control form-input">
            </label>
        </div>
        <div class="form-group">
            <label>
                <input type="password" placeholder="Password" required class="form-control form-input">
            </label>
        </div>
        <input type="submit" class="form-submit" value="Log In">
    </form>
</div>
