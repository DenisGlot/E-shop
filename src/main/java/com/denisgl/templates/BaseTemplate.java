package com.denisgl.templates;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This is Template pattern 
 * @author denis
 *
 */
public interface BaseTemplate {
	
	/**
	 * This is template method
	 * @throws IOException 
	 * @throws ServletException 
	 */
    default void forwardToView(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
    	
    	View  view = generalDoLogicAndReturnView(request, response, chain);
    	if(view == View.NONFORWARD) {
    		return;
    	}
        String urlOfWebServlet = view.getName();
        String pathToJspPage = "/views/" + urlOfWebServlet + ".jsp";
        	
    	if(view.isRedirect()) {
    		//REDIRECT
    		if(isViewHasServletUrlAnnotation(view)) {
    			response.sendRedirect(urlOfWebServlet);
    			view.setRedirect(false);
    		} else {
    			throw new IllegalArgumentException("This View " + view + " is not accessable for redirect");
    		}
    	} else {
    		//FORWARD
    		request.getRequestDispatcher(pathToJspPage).forward(request, response);
        }
    }
    
    default boolean isViewHasServletUrlAnnotation(View view) {
    	try {
    		System.out.println(view.getClass().getField(view.name()).getAnnotation(ServletUrl.class));
    		System.out.println(view.getClass().getField(view.name()).getAnnotation(ServletUrl.class) != null);
			return view.getClass().getField(view.name()).getAnnotation(ServletUrl.class) != null;
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		return false;
    }

    /**
     * It can return null if just needed to pass a filter
     * @return view which is in webapp/view/ but without extension .jsp
     */
    public View generalDoLogicAndReturnView(HttpServletRequest request, HttpServletResponse response, FilterChain chain);
}