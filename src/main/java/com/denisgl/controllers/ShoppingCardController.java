package com.denisgl.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.denisgl.cache.realization.ProductCache;
import com.denisgl.dao.entities.Product;
import com.denisgl.models.ShoppingCard;
import com.denisgl.templates.TemplateController;
import com.denisgl.templates.View;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


@WebServlet("card")
public class ShoppingCardController extends TemplateController {
    private static final long serialVersionUID = 1;

    @Override
    protected View doLogicAndReturnView(HttpServletRequest request, HttpServletResponse response) {
        return View.CARD;
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);

		if (data != null) {
			HttpSession session = request.getSession();
			setQuantityInSessionCard(data, session);
		}
    }
    
    private void setQuantityInSessionCard(JsonObject data, HttpSession session) {
    	int productId,quantity;
    	productId = declareProductId(data);
    	quantity = declareQuantity(data);
    	changeQuantityInCard(session, productId, quantity);
    }
    
    private int declareProductId(JsonObject data) {
    	if(data.get("productId") == null){
    		return 0;
		}
    	String productIdString = data.get("productId").getAsString();
    	return Integer.parseInt(productIdString);
    }
    
    private int declareQuantity(JsonObject data) {
		if(data.get("quantity") == null){
			return 0;
		}
    	String quantityString = data.get("quantity").getAsString();
		return Integer.parseInt(quantityString);
	}
    
    private void changeQuantityInCard(HttpSession session, int productId, int quantity) {
    	ShoppingCard card = (ShoppingCard) session.getAttribute("card");
		card.setQuantityOfProduct(ProductCache.getCache().findById(productId), quantity);
		session.setAttribute("card", card);
		card = null; 
    }
}