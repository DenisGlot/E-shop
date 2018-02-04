package com.denisgl.controllers;

import com.denisgl.cache.realization.UserCache;
import com.denisgl.dao.entities.User;
import com.denisgl.models.Authenticator;
import com.denisgl.models.ShoppingCard;
import com.denisgl.templates.TemplateController;
import com.denisgl.templates.View;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("login")
public class LoginController extends TemplateController {
    private static final long serialVersionUID = 1;

    @Override
    protected View doLogicAndReturnView(HttpServletRequest request, HttpServletResponse response) {
    	
    	String phoneOrEmail = request.getParameter("phoneOrEmail");
    	String password = request.getParameter("password");
    	if(phoneOrEmail == null || password == null) {
    		return View.LOGIN;
    	} else {
    		return authenticate(phoneOrEmail,password,request);
    	}
    }
    
    private View authenticate(String phoneOrEmail, String password,HttpServletRequest request) {
    	if(Authenticator.isSignedUp(phoneOrEmail,password)) {
    		letPassThroughFilter(request.getSession());
    		addShoppingCardToSession(phoneOrEmail,request.getSession());
    		return View.HOME.redirect();
    	} else {
    		request.setAttribute("error", "errorMessage");
    	    return View.LOGIN;
    	}
    }
    
    private void letPassThroughFilter(HttpSession session) {
    	session.setAttribute("loggedin", "login");
    }
    
    private void addShoppingCardToSession(String phoneOrEmail,HttpSession session) {
    	session.setAttribute("card", new ShoppingCard(addEmailToSessionAndReturnUser(phoneOrEmail,session)));
    }
    
    private User addEmailToSessionAndReturnUser(String phoneOrEmail,HttpSession session) {
    	User user = null;
    	if(phoneOrEmail.contains("@")) {
			user =  UserCache.getCache().findByCriteria("email", phoneOrEmail);
		} else {
			user =  UserCache.getCache().findById(phoneOrEmail);
		}
    	session.setAttribute("email", phoneOrEmail);
    	return user;
    }
}