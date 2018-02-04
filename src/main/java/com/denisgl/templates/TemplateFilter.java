package com.denisgl.templates;

import com.denisgl.templates.BaseTemplate;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface TemplateFilter extends Filter,BaseTemplate {
	
	@Override
    default public void init(FilterConfig filterConfig) throws ServletException { }

    @Override
    default public void destroy() { }

    @Override
    default public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        try {
			forwardToView((HttpServletRequest) request, (HttpServletResponse) response, chain);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
    }
}