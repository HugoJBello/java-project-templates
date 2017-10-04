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
      $('#liView').attr('class','');
      $('#liEdit').attr('class','active');
      $('#liUpload').attr('class','');
    });  
  </script>
  
  <c:if test="${hasEntries}">
 	<br>
  	<textarea id="text" rows="80" cols="80">${entry.contents}</textarea> 
      <input type='hidden' name='entry_name' value='${entry.entryName}'/>
      <input type='hidden' name='title' value='${ntry.title}'/>
      <input type='hidden' name='created_at' value='${entry.createdAt}'/>
      
      <div>Cathegories:</div>
      <input name='cathegoriesSemicolom', id='cathegories', placeholder="Cathegories separateb by ;" value='${entry.cathegories}'/>
  </c:if>
  
  <c:if test="${!hasEntries}">
 	<br>
 	<input id='is_new' type='hidden' name='new' value='true'/>
  	<textarea id="content" rows="80" cols="80"></textarea> 
  	<p value="Creating new entry with title: " class="lead">
	  <input id="title" type="text" name="title" value="Title"/>
	  <div id="div_available"><font color="red">(the title can not be empty)</font></div><br/>
	  <emph>Entry name: </emph>
	  <div id="div_entry_name" value="title"></div>
	  <input id="entry_name_hidden" type="hidden" name="entry_name"/>
	</p>
	<div>Cathegories:</div>
      <input name='cathegoriesSemicolom', id='cathegories', placeholder="Cathegories separateb by ;"/>
  
	<script type="text/javascript">
    var divEntryName = $( "#div_entry_name" );
    var inputEntryNameHidden = $( "#entry_name_hidden" );
    var title = $("#title");
    $(document).ready(function(){
      title.keyup(function(){
      var entry_name = titleToFilename(title.val());
       divEntryName.html();
       inputEntryNameHidden.val(entry_name);
       divEntryName.html(entry_name);
       var data={};
       data.entry_name=entry_name;
       $.ajax({
                 type: 'GET',
                 url: '/check_if_available/'+entry_name,
                 cache: false,
                 contentType: "application/json",
                 timeout: 50000,
                 success: function(data) {
                     console.log('success');
                     console.log(data);
                     if (data.used =='true'){
                       $('#div_available').html('<font color="red">(used title)</font>');
                       $('#button').prop("disabled",true);
                     } else {
                       $('#div_available').html('<font color="green">(available)</font>');
                       $('#button').prop("disabled",false);
                     }
                     
                   }
       });
     });
    });

	</script>
	
	<button id="button" type="submit" class="btn btn-default">Submit</button>
  <script>
    $(function(){
        $('#liView').attr('class','');
        $('#liEdit').attr('class','active');
        $('#liUpload').attr('class','');
        var simplemde = new SimpleMDE({
        element: document.getElementById('text'),
        spellChecker: false,
        insertTexts: {
      		horizontalRule: ["", "\n\n-----\n\n"],
      		image: ["![](", ")"],
      		link: ["[", "](http://)"],
          table: ["", "\n\n| Column 1 | Column 2 | Column 3 |\n| -------- | -------- | -------- |\n| Text     | Text      | Text     |\n\n"],
    	   },
      });
    });
  </script>

  </c:if>
  
  </body>
</html>