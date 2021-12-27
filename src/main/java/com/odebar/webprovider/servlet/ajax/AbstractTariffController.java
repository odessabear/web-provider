package com.odebar.webprovider.servlet.ajax;

import com.odebar.webprovider.form.TariffForm;
import com.odebar.webprovider.repository.model.ShoppingCart;
import com.odebar.webprovider.servlet.AbstractController;
import com.odebar.webprovider.util.RoutingUtils;
import com.odebar.webprovider.util.SessionUtils;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractTariffController extends AbstractController {

    protected final void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TariffForm form = createTariffForm(req);
        ShoppingCart shoppingCart = SessionUtils.getCurrentShoppingCart(req);
        processTariffForm(form, shoppingCart, req, resp);
        sendResponse(shoppingCart, req, resp);
    }

    protected abstract void processTariffForm(TariffForm form, ShoppingCart shoppingCart, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException;

    protected void sendResponse(ShoppingCart shoppingCart, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JSONObject cardStatistics = new JSONObject();
        cardStatistics.put("totalCount", shoppingCart.getTotalCount());
        cardStatistics.put("totalCost", shoppingCart.getTotalCost());
        RoutingUtils.sendJSON(cardStatistics, req, resp);
    }
}
