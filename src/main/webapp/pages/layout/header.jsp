<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
    <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>
    <script type="text/javascript" src="${param.js}"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/background.css">
    <link rel="stylesheet" type="text/css" href="${param.css}">
    <title>${param.title}</title>
  </head>
  <body>
<!-- Fixed navbar -->
    <nav class="navbar navbar-default navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li ><div class="dropdown">
               <button  style = "height: 50px; width: 70px; background: #d1ecf1" class="btn dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                 Menu</button>
               <ul class="dropdown-menu">
                  <li><a class="dropdown-item" href="/category?categoryId=1">Software</a></li>
                  <li><a class="dropdown-item" href="/category?categoryId=2">Hardware</a></li>
               </ul>
            </div></li>
            <li <%= request.getAttribute("javax.servlet.forward.request_uri").equals("/")?"class=\"active\"":""%> ><a href="/">Home</a></li>
            <li <%= request.getAttribute("javax.servlet.forward.request_uri").equals("/register")?"class=\"active\"":""%> ><a href="/register">Registration</a></li>
            <li <%= request.getAttribute("javax.servlet.forward.request_uri").equals("/login")?"class=\"active\"":""%> ><a href="/login">Log in</a></li>
            <li <%= request.getAttribute("javax.servlet.forward.request_uri").equals("/card")?"class=\"active\"":""%> ><a href="/card">Cart</a></li>
            <li <%= request.getAttribute("javax.servlet.forward.request_uri").equals("/feedback")?"class=\"active\"":""%> ><a href="/feedback">Feedback</a></li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
    <div id = "allBelowHeader" class = "imageOnBackground" style = "margin-top : 52px;">