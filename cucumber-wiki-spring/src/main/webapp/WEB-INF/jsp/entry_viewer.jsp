<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
  <head>
     <link href="<c:url value="/css/style.css" />" rel="stylesheet" type="text/css">
     <script type="text/javascript" src="<c:url value="/js/filename_utils.js"/>"></script>
     
     
    <title></title>
    
  </head>
  <body>
  <jsp:include page="layout_editor.jsp" />
  
  <script type="text/javascript">
  $(function() {
      $('#liView').attr('class','active');
      $('#liEdit').attr('class','');
      $('#liUpload').attr('class','');
    });  
  </script>
  
  <c:if test="${hasEntries}">
 	<br>
  	<div id="content">
  		${textInHtml}
  	</div>
  </c:if>
  
   <c:if test="${hasCathegories}">
	   <c:forEach var="cathegory" items="${cathegories}">
	   		<p>
	   			<a id="${cathegory}" class="label label-default">${cathegory}</a>
	   		</p>
			<script type="text/javascript">
			$(function(){
	            $("[id='${cathegory}']").attr('href','/cathegory/' + titleToFilename('${cathegory}'));
	            var data={};
	            if ('${cathegory}'!=''){    
	              data.cathegory_name='${cathegory}';
	              $.ajax({
	                        type: 'POST',
	                        url: '/obtain_cathegory_id',
	                        data: JSON.stringify(data),
	                        cache: false,
	                        contentType: "application/json",
	                        timeout: 50000,
	                        success: function(data) {
	                            console.log('success');
	                            console.log(data);
	                            $("[id='${cathegory}']").attr('href','/cathegory/cat=' + data.cathegory_id+'&page=1');
	                          }
	              });
	            }
	          });
			</script>			
	   </c:forEach>
  
   </c:if>
  </body>
</html>