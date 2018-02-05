package com.denisgl.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.denisgl.models.ShoppingCard;
import org.apache.log4j.Logger;

import com.denisgl.dao.entities.Product;
import com.denisgl.models.ProductGetter;
import com.denisgl.templates.TemplateController;
import com.denisgl.templates.View;

import java.io.IOException;

@WebServlet("product")
public class ProductController extends TemplateController {
    private static final long serialVersionUID = 1;
    
    private final static Logger logger = Logger.getLogger(ProductController.class);

    @Override
    protected View doLogicAndReturnView(HttpServletRequest request, HttpServletResponse response) {
    	String productIdString = request.getParameter("productId");
    	if(isNumber(productIdString)) {
    		Integer productId = Integer.parseInt(productIdString);
    		return checkOnExistAndReturnView(request,productId);
    	}
		logger.warn(productIdString + " is not a number");
        return View.PRODUCTERROR;
    }
    
    private boolean isNumber(String productIdString) {
    	return productIdString != null && productIdString.matches("\\d+");
    }
    
    private View checkOnExistAndReturnView(HttpServletRequest request, Integer productId) {
    	Product product = ProductGetter.getProductById(productId);
    	if(product.areFieldsNull()) {
    		logger.warn("There was no product with id = " + productId);
    		return View.PRODUCTERROR;
    	} 
		addAttributesToRequest(request,product);
		return View.PRODUCT;
	}
	private void addAttributesToRequest(HttpServletRequest request,Product product){
		request.setAttribute("product", product);
		String suplierName = ProductGetter.getSuplierNameById(product.getSuplierid());
		request.setAttribute("suplierName", suplierName);
		suplierName = null;
	}

    /**
     * Put product in shopping card
     */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productIdString = request.getParameter("productId");
        if(isNumber(productIdString)) {
            Integer productId = Integer.parseInt(productIdString);
            checkOnExistAndPutInCard(request.getSession(),productId);
        }
	}
	private void checkOnExistAndPutInCard(HttpSession session, Integer productId){
        Product product = ProductGetter.getProductById(productId);
        //check on exist
        if(product.areFieldsNull()) {
            logger.warn("There was no product with id = " + productId);
            return;
        }
        putProductToCard(session,product);
    }
    private void putProductToCard(HttpSession session,Product product) {
        ShoppingCard card = (ShoppingCard) session.getAttribute("card");
        card.addProduct(product,1);
        session.setAttribute("card",card);
    }

}
