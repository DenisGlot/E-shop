package com.denisgl.controllers;

import com.denisgl.cache.realization.UserCache;
import com.denisgl.dao.entities.User;
import com.denisgl.dao.hash.Hashing;
import com.denisgl.models.Sender;
import com.denisgl.models.ShoppingCard;
import com.denisgl.templates.TemplateController;
import com.denisgl.templates.View;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet("register")
public class RegisterController extends TemplateController {
    private static final long serialVersionUID = 1;

    @Override
    protected View doLogicAndReturnView(HttpServletRequest request, HttpServletResponse response) {
    	String success = request.getParameter("success");
    	if(Objects.equals(success,"true")){
    	    return View.REGISTERSUCCESS;
        }
        return View.REGISTER;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);

        if (data != null) {
            registerUser(data, request, response);
        }
    }
    private void registerUser(JsonObject data, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String firstName =  data.get("fname").getAsString();
        String lastName = data.get("lname").getAsString();
        String phone = data.get("phone").getAsString();
        String email = data.get("email").getAsString();
        String password = data.get("password").getAsString();
        String passwordHash = Hashing.sha1(password);
        if(!isUniqueEmail(email)){
            response.sendError(406,"email");
            return;
        }
        if(!isUniquePhone(phone)){
            response.sendError(406,"phone");
            return;
        }
        User user = User.newBuilder().setFirstName(firstName).setLastName(lastName).setPhone(phone).setEmail(email).setPassword(passwordHash).build();
        saveUser(user);
        sendMessageToEmail(user);
        letPassThroughFilter(request,user);
        user = null;
    }

    private boolean isUniquePhone(String phone){
        User user = UserCache.getCache().findByCriteria("phone",phone);
        if(user.areFieldsNull()){
            return true;
        }
        return false;
    }
    private boolean isUniqueEmail(String email){
        User user = UserCache.getCache().findByCriteria("email",email);
        if(user.areFieldsNull()){
            return true;
        }
        return false;
    }

    private void saveUser(User user){
        UserCache.getCache().save(user);
    }
    private void sendMessageToEmail(User user){
        Sender.sendPassword(user.getEmail(),user.getPassword());
    }

    private void letPassThroughFilter(HttpServletRequest request,User user){
        HttpSession session = request.getSession();
        session.setAttribute("loggedin","login");
        addShoppingCardToSession(session, user);
        session.setAttribute("email", user.getEmail());
        session.setAttribute("phone",user.getPhone());
    }
    private void addShoppingCardToSession(HttpSession session,User user) {
        session.setAttribute("card", new ShoppingCard(user));
    }

}