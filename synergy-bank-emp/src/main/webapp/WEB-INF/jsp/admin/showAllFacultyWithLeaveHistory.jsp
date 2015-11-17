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
<script src="${pageContext.request.contextPath}/js/jquery.searchable.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.searchable-ie-1.1.0.min.js"></script>
<script type="text/javascript">
$(document).ready(function () {
    $( '#table' ).searchable({
        striped: true,
        oddRow: { 'background-color': '#f5f5f5' },
        evenRow: { 'background-color': '#fff' },
        searchType: 'fuzzy'
    }); 
});
</script>

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
	/* $("#suggestBoxd").autocomplete({ 
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
	}); */
	
	 $( '#suggestBoxd' ).searchable({
	        searchField: '#container-search',
	        selector: '.row',
	        childSelector: '.col-xs-4',
	        show: function( elem ) {
	            elem.slideDown(100);
	        },
	        hide: function( elem ) {
	            elem.slideUp( 100 );
	        }
	    })
	    
	    $( '#employeeTable' ).searchable({
	        striped: true,
	        oddRow: { 'background-color': '#f5f5f5' },
	        evenRow: { 'background-color': '#fff' },
	        searchType: 'fuzzy'
	    });
});
</script>
		 
<style type="text/css">
//PK
.row-padding {
    margin-top: 25px;
    margin-bottom: 25px;
}

#td{
		text-align:left;
	}

//End


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
	
	<section class="title">
		
	</section>
	<!-- / .title -->
		 
		
		
		<div id="main_content">
			<h3 align="center">
				<font color="black">Employee Data</font>
			</h3>
			
		<ff:form class="container main">					
            <input type="search" id="search" value="" class="form-control" placeholder="Search using Fuzzy searching">

		</ff:form>
			
		<ff:form enctype="multipart/form-data">
		<div class="container main"> 
		 
		  <div class="row">
            <div class="col-lg-12">
		      <table id="table" class="table table-bordered table-hover" width="100%">
				<thead>
			<tr height="50px" style="color: black; background-color: #F1F1F1; vertical-align: middle;">
				<td style="text-align:left;"><b>EmpId</b></td> 
				<td style="text-align:left;"><b> Name</b></td>
				<td style="text-align:left;"><b>Department</b></td>
				<td style="text-align:left;"><b>Designation</b></td>
				<td style="text-align:left;"><b> Email</b></td>
				<td style="text-align:left;"><b>Mobile</b></td>
				<td style="text-align:left;"><b>Image</b></td> 
 				<td style="text-align:left;"><b>Leave history</b></td> 
			</tr>
            </thead>
            <tbody>
            <c:forEach items="${employeeForms}" var="item" varStatus="status">
				<tr id="resultDataRow${status.count}" height="50px"
					style="color: black; vertical-align: middle;">
					 <td style="text-align:left;" class="data0">${item.id}</td>
					<td style="text-align:left;" class="data0">${item.name}</td>
					<td style="text-align:left;" class="data0">${item.department}</td>
					<td style="text-align:left;" class="data0">${item.designation}</td>
					<td style="text-align:left;" class="data0">${item.email}</td>
					<td style="text-align:left;" class="data0">${item.phoneNumber}</td>
					<td style="text-align:left;" class="data0"> <img class="img-circle" src="${pageContext.request.contextPath}/admin/image?id=${item.id}" width="50" height="50"/></td>
					<td style="text-align:left;" class="data0"><a href="${pageContext.request.contextPath}/admin/leaveHistoryById?id=${item.id}">view history</a></td>					 
				</tr>
			</c:forEach>
			</tbody>
		</table>
		</div>
		</div>
		</div>
		</ff:form>	
		<br />

<!--Footer-->
	<footer class="footer navbar-fixed-bottom" id="footer">
		<div class="container">
			<div class="row-fluid">
				<div class="span5 cp">
					&copy; 2013 <a target="_blank" href="http://shapebootstrap.net/"
						title="Free Twitter Bootstrap WordPress Themes and HTML templates">ShapeBootstrap</a>.
					All Rights Reserved.
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
				<!--/Goto Top-->
			</div>
		</div>
	</footer>
	<!--/Footer-->
</body>
</html>