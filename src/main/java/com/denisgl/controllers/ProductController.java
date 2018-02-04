package com.denisgl.controllers;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.denisgl.dao.entities.Product;
import com.denisgl.filters.AuthenticationFilter;
import com.denisgl.models.ProductGetter;
import com.denisgl.templates.TemplateController;
import com.denisgl.templates.View;

import java.io.IOException;

@WebServlet("product")
public class ProductController extends TemplateController {
    private static final long serialVersionUID = 1;
    
    private final static Logger logger = Logger.getLogger(AuthenticationFilter.class);

    @Override
    protected View doLogicAndReturnView(HttpServletRequest request, HttpServletResponse response) {
    	String productIdString = request.getParameter("productId");
    	if(isNumber(productIdString)) {
    		Integer productId = Integer.parseInt(productIdString);
    		return addProductToRequestAndForward(request,productId);
    	}
        return View.HOME;
    }
    
    private boolean isNumber(String productIdString) {
    	return productIdString != null && productIdString.matches("\\d+");
    }
    
    private View addProductToRequestAndForward(HttpServletRequest request, Integer productId) {
    	Product product = ProductGetter.getProductById(productId);
    	if(product.areFieldsNull()) {
    		logger.warn("There was no product with id = " + productId);
    		return View.PRODUCTERROR;
    	} 
		request.setAttribute("product", product);
    	request.setAttribute("suplierName", ProductGetter.getSuplierNameById(product.getSuplierid()));
		return View.PRODUCT;
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO put in card
	}
}
