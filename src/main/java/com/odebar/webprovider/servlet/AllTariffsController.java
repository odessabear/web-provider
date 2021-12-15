package com.odebar.webprovider.servlet;

import com.odebar.webprovider.repository.entity.Tariff;
import com.odebar.webprovider.util.RoutingUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet("/tariffs")
public class AllTariffsController extends AbstractController {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Tariff> tariffs = getBusinessService().getTariffs();
        req.setAttribute("tariffs", tariffs);
        RoutingUtils.forwardToPage("tariffs.jsp", req, resp);
    }
}
