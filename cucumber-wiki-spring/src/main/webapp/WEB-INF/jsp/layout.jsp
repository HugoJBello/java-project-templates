<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
  <head>
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href= "<c:url value= "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>" rel="stylesheet">
    <script src= "<c:url value= "http://code.jquery.com/jquery.js"/>"></script>
    <link href="<c:url value="css/style.css" />" rel="stylesheet" type="text/css">
    <script src= "<c:url value= "http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"/>"></script>
    <style type="text/css">
      .navbar-fixed-top {
      background-color: #d6d6c2;
      background-image: none;
      }
      small {
      font-size: 60%;
      }
      .small-icon{
        font-size: 70%;
      }
    </style>
  </head>
  <body>
  <div class="col-sm-12">
    <div class="sidebar-nav">
      <div role="navigation" class="navbar navbar-default navbar-fixed-top">
        <!-- Brand and toggle get grouped for better mobile display-->
        <div class="navbar-header">
          <button type="button" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" class="navbar-toggle"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button><a href="/" class="navbar-brand">
          <!-- <img src="/logo/logo_navbar3.png" width="80"> --></a>
        </div>
        <!-- Collect the nav links, forms, and other content for toggling-->
        <div id="bs-example-navbar-collapse-1" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li><a href="/entry_editor">
                New Page <span class="small-icon glyphicon glyphicon-pencil"></span></a></li>
            <li><a href="/entry_list/sort=updated_at&amp;order=-1&amp;page=1">
                Last entries <span class="small-icon glyphicon glyphicon-refresh"></span></a></li>
            <li><a href="/cathegories/sort=updated_at&amp;order=-1&amp;page=1">Cathegories <span class="small-icon glyphicon glyphicon-tags"></span></a></li>
          </ul>
          <div class="col-sm-3 col-md-3">
            <form role="search" action="/search" method="post" class="navbar-form">
              <div class="input-group">
                <input type="text" placeholder="Search" name="text" class="form-control">
                <div class="input-group-btn">
                  <button type="submit" class="btn btn-default"><i class="small-icon glyphicon glyphicon-search"></i></button>
                </div>
              </div>
            </form>
          </div>
          <ul class="nav navbar-nav navbar-right">
            <li><a href="/login">
                Login <span class="small-icon glyphicon glyphicon-road"></span></a></li>
            <li><a href="/register">Register <span class="small-icon glyphicon glyphicon-tent"></span></a></li>
            <li><a href="/register">
                 </a></li>
          </ul>
        </div>
      </div>
    </div>
  </div>
  </body>
</html>