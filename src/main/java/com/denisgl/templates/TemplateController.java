package com.denisgl.templates;

import com.denisgl.templates.BaseTemplate;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Every realization of this must have
 * 1.annotation WebServlet
 * @author denis
 *
 */
public abstract class TemplateController extends HttpServlet implements BaseTemplate {
	
    private static final long serialVersionUID = 1765432234567L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        forwardToView(request, response, null);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public View generalDoLogicAndReturnView(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
        return doLogicAndReturnView(request, response);
    }

    protected abstract View doLogicAndReturnView(HttpServletRequest request, HttpServletResponse response);
}