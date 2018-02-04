
package com.denisgl.controllers;

import com.denisgl.templates.TemplateController;
import com.denisgl.templates.View;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("home")
public class HomeController extends TemplateController {
    private static final long serialVersionUID = 1;

    @Override
    protected View doLogicAndReturnView(HttpServletRequest request, HttpServletResponse response) {
        return View.HOME;
    }
}