package com.denisgl.controllers;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.denisgl.models.CategoryList;
import com.denisgl.templates.TemplateController;
import com.denisgl.templates.View;

@WebServlet("category")
public class CategoryController extends TemplateController {
    private static final long serialVersionUID = 1;

    @Override
    protected View doLogicAndReturnView(HttpServletRequest request, HttpServletResponse response) {
    	
    	String categoryIdString = request.getParameter("categoryId");
    	if(isNumber(categoryIdString)) {
    		Integer categoryId = Integer.parseInt(categoryIdString);
    		addProductsToRequest(request, categoryId);
    		addCategoryNameToRequest(request,categoryId);
    		return View.CATEGORY;
    	} else {
            return View.HOME;
    	}
    }
    
    private boolean isNumber(String categoryIdString) {
    	return categoryIdString != null && categoryIdString.matches("\\d+");
    }
    
    private void addProductsToRequest(HttpServletRequest request,Integer categoryId) {
    	request.setAttribute("products", CategoryList.getProductsByCategoryId(categoryId));
    }
    
    private void addCategoryNameToRequest(HttpServletRequest request,Integer categoryId) {
    	request.setAttribute("categoryName", CategoryList.getNameOfCategory(categoryId));
    }
}