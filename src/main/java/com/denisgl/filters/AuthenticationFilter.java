package com.denisgl.filters;

import com.denisgl.templates.TemplateFilter;
import com.denisgl.templates.View;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

@WebFilter(urlPatterns = {"/product","/feedback","/card"})
public class AuthenticationFilter implements TemplateFilter {
	
	private final Logger logger = Logger.getLogger(AuthenticationFilter.class);
	
	@Override
    public View generalDoLogicAndReturnView(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
        HttpSession session = request.getSession();
        
        if (isLoggedIn(session)) {
            return passFilter(request, response, chain);
        } else {
            return View.LOGIN.redirect();
        }
    }

    private boolean isLoggedIn(HttpSession session) {
    	return session.getAttribute("loggedin") != null;
    }

    private View passFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
        try {
            chain.doFilter(request, response);
        }
        catch (IOException | ServletException e) {
        	logger.error(e);
        }
        return View.NONFORWARD;
    }
}
