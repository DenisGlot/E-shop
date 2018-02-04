package com.denisgl.controllers;

import com.denisgl.models.Sender;
import com.denisgl.templates.TemplateController;
import com.denisgl.templates.View;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("feedback")
public class FeedBackController extends TemplateController {
    private static final long serialVersionUID = 137489438L;

    @Override
    protected View doLogicAndReturnView(HttpServletRequest request, HttpServletResponse response) {
    	
    	HttpSession session = request.getSession();
    	String email = (String) session.getAttribute("email");
    	session=null;
    	String message = request.getParameter("message");
    	String subject  = request.getParameter("subject");
    			
    	if(message == null || subject == null) {
    		return View.FEEDBACK;
    	} 
    	
	    if(Sender.sendFeedBackAndCheck(message, subject, email)) {
	    	return View.FEEDBACKSUCCESS;
	    } else {
	    	return View.FEEDBACKERROR;
	    }
    	
    }
}
