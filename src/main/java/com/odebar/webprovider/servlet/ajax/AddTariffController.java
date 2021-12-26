package com.odebar.webprovider.servlet.ajax;

import com.odebar.webprovider.form.TariffForm;
import com.odebar.webprovider.repository.model.ShoppingCart;
import com.odebar.webprovider.servlet.AbstractController;
import com.odebar.webprovider.util.RoutingUtils;
import com.odebar.webprovider.util.SessionUtils;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ajax/json/tariff/add")
public class AddTariffController extends AbstractController {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TariffForm tariffForm = createTariffForm(req);
        ShoppingCart shoppingCart = SessionUtils.getCurrentShoppingCart(req);
        getOrderService().addTariffToShoppingCart(tariffForm, shoppingCart);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("totalCount", shoppingCart.getTotalCount());
        jsonObject.put("totalCost", shoppingCart.getTotalCost());

        RoutingUtils.sendJSON(jsonObject, req, resp);
    }
}
