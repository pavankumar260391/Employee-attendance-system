<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="ff"%>

<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Login</title>
<%@include file="aimport.jsp"%>
<script> 


 
 function tableProcessing(data){
	
	$('#employeeTable').empty();
	
    var trHTML ="";
    
    trHTML += '<tr height="30px" style="color: black; background-color: #F1F1F1; vertical-align: middle;" align="center">  <td width="50px"><b>EmpId</b></td>  <td width="50px"><b>Name</b></td><td><b>Email</b></td><td width="200px"><b>Mobile</b></td><td><b>Designation</b></td><td><b>Department</b></td><td><b>Image</b></td>';		
    
    $.each(data, function (i, item) {
    	var counter = 0;
    	var empid = item.id;
    	var name = $.trim(item.name);
    	var email = $.trim(item.email); 
    	var mobile=(item.mobile);
    	var designation=(item.designation);
    	var department=(item.department);
    	var image=(item.image);
    	 
    	
    	 
    	
        trHTML += '<tr height="25px" id="resultDataRow'+i+'" style="color: black; vertical-align: middle;">  <td class="data'+counter+'"><b>' +empid + '.</b></td>  <td class="data'+counter+'"><b>' +name + '.</b></td><td class="data'+(counter+1)+'">' +email+' '+ '</td><td class="data'+(counter+2)+'">' + mobile + '</td><td class="data'+(counter+3)+'">' +designation+ '</td><td class="data'+(counter+4)+'">' + department + '</td><td class="data'+(counter+5)+'">' + image + '</td>' ;
        
         
    });
    
    $('#employeeTable').append(trHTML);
}
   
 
 
		function ajaxCall(option,controller){
	$.ajax({
    	url:contextPath+"/employee/"+controller,	        	
    	type: "get",
    	data:{"requiredDatas":option},
    	success: function(data){
    		 
    		  tableProcessing(data);  
    	}});  
}

$(document).ready(function(){
	var req = contextPath+'/employee/searchEmployeeRama';
	$("#suggestBoxd").autocomplete({ 
		delay:500,
		source: function(request, response){    			
			$.ajax({    				
				url:req,
				data:request,
				success: function(result){
					var items = result;
					response(result.slice(0, 10));
				}    				
			});    			
		},
		 select: function (event,ui){
			 
			 selectedName = ui.item.value;
			 
			ajaxCall(selectedName,'retreiveEmployeeForAdminspecific');  			
		}
	});
});
</script>
		 
<style type="text/css">
#pager {
	margin-top: 30px;
	position: absolute;
	top: 32em;
	left: 30em;
}

img {
	max-width: 100%;
	border-color: red;
}

.image {
	width: 100%;
	height: 100%;
	border-color: red;
}

.image img {
	-webkit-transition: all 1s ease; /* Safari and Chrome */
	-moz-transition: all 1s ease; /* Firefox */
	-o-transition: all 1s ease; /* IE 9 */
	-ms-transition: all 1s ease; /* Opera */
	transition: all 1s ease;
}

.image:hover img {
	-webkit-transform: scale(4.0);
	-moz-transform: scale(4.0);
	transform: scale(4.0);
	border-color: red;
}
</style>
</head>

<body>

<%@include file="aheader.jsp"%>
	<div id="main_container">
		<%-- <div id="header">
			<div id="logo">
				<a href="home.html"><img
					src="${pageContext.request.contextPath}/images/registerp.jpg"
					alt="" title="" border="0" width="900" /></a>
			</div> --%>
	
		</div>
		 
		<hr style="color: blue" />
		<hr style="color: red" />
		<br />
		
		<div id="main_content">
		<%-- <form action="${pageContext.request.contextPath}/admin/showAllEmployee"
					method="get" >  --%>

			<!--end of left content-->
			<h3 align="center">
				<font color="black">Employee Data</font>
			</h3>
			
		<ff:form
			
			class="container main" commandName="adminMonthUtility">
			<table width="1150" border="0" align="left">
				<tbody>
					<tr style="color: #000000; font-weight: bold;" >
						<td>							
					 <input type="text" id="suggestBoxd" name="employeeName"
							placeholder="Search Employee" class="form-control"
							style="width: 300px;"></input>
						</td>
						
						 </table>
						 </ff:form>
			<%-- <form action=""${pageContext.request.contextPath}/admin/showAllFacultyd" method="get" commandName="employeeform"> --%>
			
			<div class="container main"> 
		<table class="table table-bordered table-hover" id="employeeTable"
			width="100%">
			<tr height="30px"
				style="color: black; background-color: #F1F1F1; vertical-align: middle;"
				align="center">
				<td><b>EmpId</b></td> 
				<td><b> Name</b></td>
					<td><b> Email</b></td>
					<td><b>Mobile</b></td>
					<td><b>Designation</b></td>
					<td><b>Department</b></td>
					<td><b>Image</b></td> 
			</tr>
            
            <c:forEach items="${employeeFormsD}" var="item" varStatus="status">
				<tr id="resultDataRow${status.count}" height="25px"
					style="color: black; vertical-align: middle;">
					 <td align="center" class="data0">${item.id}</td>
					<td align="center" class="data0">${item.name}</td>  
					
					<td align="left" class="data0">${item.email}</td>
					<td align="center" class="data0">${item.phoneNumber}</td>
					<td align="center" class="data0">${item.designation}</td>
					<td align="center" class="data0">${item.department}</td>
					<td align="center" class="data0">${item.image}</td>
					<!-- <td align="center" class="data0">Null</td> -->
						<%-- <button id="btnId${status.count}" class="btn btn-primary"
							onClick="popupOpen(this.id)">
							<span class="icon-pencil" aria-hidden="true"></span>Edit
						</button> --%>
					 
				</tr>
			</c:forEach>
		</table>
		</div>
			
			
			 
<!-- </form> -->
			<!--end of right content-->
			<%-- <button id="bttn">Export to Excel</button>

			<br /> <br /> <br /> <br />
			<div id="pager" class="pager">
				<form>
					<img src="${pageContext.request.contextPath}/images/first.png"
						class="first" /><img
						src="${pageContext.request.contextPath}/images/prev.png"
						class="prev" /><input type="text" class="pagedisplay" /><img
						src="${pageContext.request.contextPath}/images/next.png"
						class="next" /><img
						src="${pageContext.request.contextPath}/images/last.png"
						class="last" /><select class="pagesize"><option
							selected="selected" value="2">2</option>
						<option value="2">2</option>
						<option value="4">4</option>
						<option value="8">8</option></select>
				</form>
			</div>

			<br /> <br /> <br /> <br /> <br /> <br /> <br /> <br />


			<div style="clear: both;"></div>
		</div> --%>
		<!--end of main content-->
		<br />
		<div id="footer">
			<div class="copyright">
				<a href="home.html"><img
					src="${pageContext.request.contextPath}/images/footer_logo.gif"
					border="0" alt="" title="" /></a>
			</div>
			<div class="footer_links">
				<a href="#">About us</a> <a href="privacy.html">Privacy policy</a> <a
					href="contact.html">Contact us </a> <a
					href="http://www.webpagedesign.com.au">Art for the web</a>

			</div>


		</div>



	</div>
	<!--end of main container-->
</body>
</html>