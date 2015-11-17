<%@ taglib uri="http://www.springframework.org/tags/form" prefix="ff"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Add Department | BAS</title>
    
      <%@ include file="aimport.jsp" %>
      <script>
  $(function() {
    $( "#datepicker" ).datepicker();
  });
  </script>
  
   <style>
      .error {
       
   color: red;
   font-size: 12px;
}
      </style>
       
        
      
 <script>
   $(document).ready(function(){
	   
	   
	   $('#springForm').validate({ // initialize the plugin
	        rules: {
	        	
	        	departmentName:{
	                required: true,
	                 
	                
	            },
	            departmentShortName: {
	                required: true
	                
	            }
	        }
	    });
   });
   
  $(document).ready(function(){
	  
	  $("#departmentName").keyup(checkDepartment);
	  
	  });
  $(document).ready(function(){
  $('a').click(function (){ 
	     hrefId = jQuery(this).attr("id"); 
	     var resultRowId = $("#"+hrefId).closest('tr').attr("id");
	     var depName = $("#"+resultRowId).children('td.data0');
	     alert(depName)
 	     $.ajax({
	        url: "${pageContext.request.contextPath}/admin/deleteDepartment",
	        data:{"depName":depName},
	        type:"get",
	        success: function(response) {
	            alert(response)
	        }
	     }) 
	     return false; //for good measure
	});
  });
  function checkDepartment(){
	  
	  var depName = $("#departmentName").val();
	  $.ajax({
		  
		  url:"${pageContext.request.contextPath}/admin/validateDep",
		  type: "GET",
		  data :{"department": depName},
		  success:function(result){
			  if(result=="fail"){
				  
				  $("#errorMsg").show();
			  }else{
				  $("#errorMsg").hide();
			  }
		  }
		  
	  });
	  
  }
  
   </script>
    
    
</head>
<body>

<!--Header-->
      <%@ include file="aheader.jsp" %>
    <!-- /header -->

    <section class="title">
        <div class="container">
            <div class="row-fluid">
                 <div class="span6">
                    <!-- <h3>Add Department</h3> -->
                </div>
                <div class="span6">
                    <ul class="breadcrumb pull-right">
                        <li><a href="index.html">Home</a> <span class="divider">/</span></li>
                        <li><a href="#">Action</a> <span class="divider">/</span></li>
                        <li class="active">Add Department</li>
                    </ul>
                </div> 
            </div>
        </div>
    </section>

 <section id="portfolio" class="container main">   

 
 
	<ff:form action="${pageContext.request.contextPath}/admin/addDepartmentTodb"
					method="post" commandName="department"  id="springForm">
					<fieldset>
						<legend style="color: blue; font-weight: bold;" align="left">
							<b><font color="darkblue">Add New Department</font></b>
						</legend>
						<br />
						<table width="425" border="0" align="left">
							
							<!-- <tr bgcolor="#66CCFF" style="color: #000000; font-weight: bold;">
								<td>Designation Id</td>
								<td><input  type="text" id="designationId" name="designationId"
										size="10" style="height: 15px"></input>
										  
										</td> -->
										<tr  style="color: #000000; font-weight: bold;">
										
								<td>Department Name <span style="color: red;font-weight: bold;font-size: 16px;">*</span></td>
								<td>
								<input  type="text" id="departmentName" class="error" name="departmentName"
										size="10"  style="float:left;margin-right:10px;height: 15px"></input>
										
								</td>
  
								<span id="errorMsg" style="color: red;font-size: 12px; display:none;">Department already Exists</span>
								  
							</tr>
							
							<tr  style="color: #000000; font-weight: bold;">
								<td>Short Name of Department <span style="color: red;font-weight: bold;font-size: 16px;">*</span></td>
								<td>
								<input  type="text" id="departmentShortName"  name="departmentShortName"
										size="10" style="float:left;margin-right:10px;height: 15px"></input>
								<p id="designationError" style="display: none;">Please Enter the Designation</p>		  
										</td>
							</tr>
							<!-- <tr  style="color: #000000; font-weight: bold;">
								<td>Short Name of Department</td>
								<td>
								<textarea rows="" cols="" name="departmentShortName" id="departmentShortName"></textarea>
										</td>
										 
							</tr> -->
							<tr  style="color: #000000; font-weight: bold;">
								<td>Description</td>
								<td>
								<textarea rows="" cols="" name="description" id="description"></textarea>
										</td>
										 
							</tr>
							 
							 <tr bgcolor="white" style="color: #000000; font-weight: bold;">
								<td colspan="2">&nbsp;</td>
							</tr>
							 <tr bgcolor="white" style="color: #000000; font-weight: bold;">
								<td><input type="submit" id="AddDepartment" class="btn btn-info" size="30" value="Add Department" ></input></td>
							</tr>
							<td colspan="2">&nbsp;</td>
  
						</table>
						 
													 
					
					 
						<table  class="table table-bordered table-hover" width="100%" id="users">
    <thead>
      <tr class="ui-widget-header ">
        <!-- <th><h5>departmentId</h5></th> -->
        <th><h5>departmentName</h5></th>
        <th><h5>departmentShortName</h5></th>
        <th><h5>description	</h5></th>
        <th><h5>doe</h5></th>
        <th><h5>dom</h5></th>
        <th><h5>entryBy</h5></th>
        <th><h5>Modify</h5></th>
        <!-- <th><button type="button" id="create-user" onclick="formPopup()"><span class="glyphicon glyphicon-plus"></span></button></th> -->
      </tr>
    </thead>
    <tbody>
    	<c:forEach var="item" items="${departmentForms}" varStatus="status">
       <tr id="rowId${status.count}">
        <td class="data0" align="left" valign="middle">${item.departmentName}</td>
         <td>${item.departmentShortName}</td>
        <td>${item.description}</td>
        <td><fmt:formatDate pattern="dd-MM-yyyy" value="${item.doe}" /></td>
        <td><fmt:formatDate pattern="dd-MM-yyyy" value="${item.dom}" /></td>
        
        <td>${item.entryBy }</td>
        <td> <img name="jsbutton" src="${pageContext.request.contextPath}/images/edit.png" width="20" height="20" border="0" alt="javascript button">  &nbsp;&nbsp;
         <a id="deleteRecord${status.count}"  href=""><img name="jsdbutton" src="${pageContext.request.contextPath}/images/delete.png" width="20" height="20" border="0" alt="javascript button"></img></a></td>
         
          <!-- <td><button type="submit" id="pd" class="btn btn-default"><span class="glyphicon glyphicon-pencil"></span> </button></td>
          <td><button type="submit" id="pdr" class="btn btn-default"><span class="glyphicon glyphicon-remove"></span> </button></td>  -->
      </tr>
     </c:forEach>
    </tbody>
   
  </table>
   
  
						<%-- <table width="630" border="0" align="left">
							style="background-image: url(${pageContext.request.contextPath}/images/nhbg.jpg);">
							
							<tr bgcolor="#66CCFF" style="color: #000000; font-weight: bold;">
								<td>Department Name</td>
								<td><input  type="text" id="departmentName" name="departmentName"
										size="10" style="height: 15px"></input>
										  
										</td>
										<tr bgcolor="#66CCFF" style="color: #000000; font-weight: bold;">
								<td>Description</td>
								<td><input  type="text" id="description" name="description"
										size="10" style="height: 15px"></input>
										  
										</td>
							</tr>
							<tr bgcolor="#66CCFF" style="color: #000000; font-weight: bold;">
								<td>Short Name of Department</td>
								<td><input  type="text" id="departmentShortName" name="departmentShortName"
										size="10" style="height: 15px"></input>
										  
										</td>
							</tr>
							<tr bgcolor="#66CCFF" style="color: #000000; font-weight: bold;">
								<td>Entry By</td>
								<td><input  type="text" id="entryBy" name="entryBy"
										size="10" style="height: 15px"></input>
										  
										</td>
							</tr>
							 
							
							<tr bgcolor="white" style="color: #000000; font-weight: bold;">
								<td><input type="submit" class="btn btn-info" size="30" value="AddDepartment" ></input></td>
							</tr>
							
						</table> --%>
					</fieldset>
				</ff:form>
</section>

<footer id="footer">
    <div class="container">
        <div class="row-fluid">
            <div class="span5 cp">
                &copy; 2013 <a target="_blank" href="http://shapebootstrap.net/" title="Free Twitter Bootstrap WordPress Themes and HTML templates">ShapeBootstrap</a>. All Rights Reserved.
            </div>
            <!--/Copyright-->

            <div class="span6">
                <ul class="social pull-right">
                    <li><a href="#"><i class="icon-facebook"></i></a></li>
                    <li><a href="#"><i class="icon-twitter"></i></a></li>
                    <li><a href="#"><i class="icon-pinterest"></i></a></li>
                    <li><a href="#"><i class="icon-linkedin"></i></a></li>
                    <li><a href="#"><i class="icon-google-plus"></i></a></li>                       
                    <li><a href="#"><i class="icon-youtube"></i></a></li>
                    <li><a href="#"><i class="icon-tumblr"></i></a></li>                        
                    <li><a href="#"><i class="icon-dribbble"></i></a></li>
                    <li><a href="#"><i class="icon-rss"></i></a></li>
                    <li><a href="#"><i class="icon-github-alt"></i></a></li>
                    <li><a href="#"><i class="icon-instagram"></i></a></li>                   
                </ul>
            </div>

            <div class="span1">
                <a id="gototop" class="gototop pull-right" href="#"><i class="icon-angle-up"></i></a>
            </div>
            <!--/Goto Top-->
        </div>
    </div>
</footer>
<!--/Footer-->

<!--  Login form -->
<div class="modal hide fade in" id="loginForm" aria-hidden="false">
    <div class="modal-header">
        <i class="icon-remove" data-dismiss="modal" aria-hidden="true"></i>
        <h4>Login Form</h4>
    </div>
    <!--Modal Body-->
    <div class="modal-body">
        <form class="form-inline" action="index.html" method="post" id="form-login">
            <input type="text" class="input-small" placeholder="Email">
            <input type="password" class="input-small" placeholder="Password">
            <label class="checkbox">
                <input type="checkbox"> Remember me
            </label>
            <button type="submit" class="btn btn-primary">Sign in</button>
        </form>
        <a href="#">Forgot your password?</a>
    </div>
    <!--/Modal Body-->
</div>
<!--  /Login form -->

 
  

</body>




</html>

