package com.denisgl.controllers;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.denisgl.dao.entities.Product;
import com.denisgl.models.CategoryList;
import com.denisgl.templates.TemplateController;
import com.denisgl.templates.View;
import org.apache.log4j.Logger;

import java.util.List;

@WebServlet("category")
public class CategoryController extends TemplateController {
    private static final long serialVersionUID = 1;

    private final static Logger logger = Logger.getLogger(CategoryController.class);

    @Override
    protected View doLogicAndReturnView(HttpServletRequest request, HttpServletResponse response) {
    	
    	String categoryIdString = request.getParameter("categoryId");
    	if(isNumber(categoryIdString)) {
    		Integer categoryId = Integer.parseInt(categoryIdString);
            addCategoryNameToRequest(request,categoryId);
    		return checkOnExistAndReturnView(request, categoryId);
    	}
        logger.warn(categoryIdString + "is not a number");
    	return View.CATEGORYERROR;
    }
    
    private boolean isNumber(String categoryIdString) {
    	return categoryIdString != null && categoryIdString.matches("\\d+");
    }
    
    private View checkOnExistAndReturnView(HttpServletRequest request, Integer categoryId) {
        List<Product> list = CategoryList.getProductsByCategoryId(categoryId);
        if(list.isEmpty()){
            logger.warn("There was no category with id = " + categoryId);
            return View.CATEGORYERROR;
        }
    	request.setAttribute("products", list);
        list = null;
        return View.CATEGORY;
    }
    
    private void addCategoryNameToRequest(HttpServletRequest request,Integer categoryId) {
    	request.setAttribute("categoryName", CategoryList.getNameOfCategory(categoryId));
    }
}