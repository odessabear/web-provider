package com.odebar.webprovider.servlet.ajax;

import com.odebar.webprovider.servlet.AbstractController;
import com.odebar.webprovider.util.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ajax/html/more/tariffs")
public class LoadMoreTariffsController extends AbstractController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoutingUtils.forwardToFragment("tariffs-list.jsp", req, resp);
    }
}
