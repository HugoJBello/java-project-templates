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
    
  </head>
  <body>
  <jsp:include page="layout.jsp" />

	<c:choose>
		<c:when test="${hasEntries}">
		<h1>${entry.title}</h1>
		  <ul class="nav nav-tabs">
		    <li id="liView"><a id="view" href="/entry/${entry.entryName}">
		        View
		         <span class="glyphicon glyphicon-eye-open"></span></a></li>
		    <li id="liEdit"><a id="edit" href="/entry_editor/${entry.entryName}">
		        Edit
		         <span class="glyphicon glyphicon-pencil"></span></a></li>
		    <li id="liUpload"><a href="/upload/${entry.entryName}">
		        Add image
		         <span class="glyphicon glyphicon-picture"></span></a></li>
		    <li id="liHistory"><a href="/entry_history/${entry.entryName}&amp;page=1">History</a></li>
		  </ul>
		</c:when>
	<c:when test="${!hasEntries}">
		<c:if test="${has_entry_name}">
			<h1>${entryName}</h1>
			  <ul class="nav nav-tabs">
			    <li id="liView"><a id="view" href="/entry/${entryName}">
			        View
			         <span class="glyphicon glyphicon-eye-open"></span></a></li>
			    <li id="liEdit"><a id="edit" href="/entry_editor/${entryName}">
			        Edit
			         <span class="glyphicon glyphicon-pencil"></span></a></li>
			    <li id="liUpload"><a href="/upload/${entryName}">
			        Add image
			         <span class="glyphicon glyphicon-picture"></span></a></li>
			    <li id="liHistory"><a href="/entry_history/${entryName}&amp;page=1">History</a></li>
			  </ul>
			
		</c:if>
		<c:if test="${!hasEntryName}">
			<h1> empty entry</h1>
			  <ul class="nav nav-tabs">
			    <li id="liView"><a id="view" href="/entry_viewer/">
			        View
			         <span class="glyphicon glyphicon-eye-open"></span></a></li>
			    <li id="liEdit"><a id="edit" href="/entry_editor/">
			        Edit
			         <span class="glyphicon glyphicon-pencil"></span></a></li>
			    <li id="liUpload"><a href="">
			        Add image
			         <span class="glyphicon glyphicon-picture"></span></a></li>
			    <li id="liHistory"><a href="">History</a></li>
			  </ul>
		
		</c:if>	
	</c:when>
	</c:choose>
  </body>
</html>