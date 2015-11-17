<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="ff"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Portfolio | Nova</title>
<%@include file="aimport.jsp"%>
<script src="${pageContext.request.contextPath}/js/admin/upAttd.js"></script>
 <script> 
 $(document).ready(function(){
 	var idArr="";
 	$('#markBttn').click(function(){
			$("#hidden_txt").show(); 
 		$(".tbl").each(function(){
 			$(this).find('td input:checked').each(function () {
 				//alert("hello"+$(this).closest('tr').children('td.data0').text());
 				var temp = $(this).closest('tr').children('td.data0').text();
 				if(temp != null)
 					idArr += temp +" ";  //$($(this).attr("id")).html();
 	        });
 		});
 		updateAjaxCall(idArr);
 	});

	    $('#depSelect').change(function(){    	
	    	var department = $('#depSelect').find('option:selected').val();
	    	ajaxCallForDep(department);
	    });
	  });
 
    </script>
</head>

<body>

	<!--Header-->
	<%@include file="aheader.jsp"%>
	<!-- /header -->

	<section class="title">
		<div class="container">
			<div class="row-fluid">
				<div class="span6">
					<h3>Today's attendence</h3>
				</div>
				<div class="span6">
					<ul class="breadcrumb pull-right">
						<li><a href="index.html">Home</a> <span class="divider">/</span></li>
						<li><a href="#">${pageTitle}</a> <span class="divider">/</span></li>
						<li class="active">Portfolio</li>
					</ul>
				</div>
			</div>
		</div>
	</section>
	<!-- / .title -->
		
		
		<section id="portfolio" class="container main">
		 	<div><p id="hidden_txt" align="center" style="font-size:20px; color:green;" hidden="true">Out-time and out-status have been marked !</p></div>
		
			<ff:form class="form-inline" commandName="departmentForm">
			<table width="1150" border="0" align="left">

				<tbody>
					<tr style="color: #000000; font-weight: bold;">
					  <td style="padding-right: 12cm;">
						 <label for="depSelection" id="depLabel" class="control-label" style="font-weight: bold;">Department:</label>
						 <ff:select path="departmentShortName" items="${depList}" id="depSelect" class="form-control"></ff:select>
					  </td>
					  <td align="right">
					  	  <span style="float: right;">
							<button type="button" width="150px" class="btn btn-primary" id="markBttn"><span class="icon-pencil" aria-hidden="true"></span> Mark Out-Attendence</button>
						  </span>
					  </td>
					</tr>
				</tbody>
			</table>
		</ff:form>
		
 		<!-- <span style="float: right;">
			<button type="button" width="150px" class="btn btn-primary" id="markBttn"><span class="icon-pencil" aria-hidden="true"></span> Mark Out-Attendence</button>
		</span> -->
		<br><br>

		<table  class="table table-bordered table-hover" id="employeeTable"
			width="100%" height="100px">
			<tr height="30px"
				style="color: black; background-color: #F1F1F1;"
				align="center">
				<td width="50px"><b>Emp ID</b></td>
				<td><b>Name</b></td>
				<td width="200px"><b>Att Date</b></td>
				<td><b>In Time</b></td>
				<td><b>Out Time</b></td>
				<td><b>Department</b></td>
				<td><b>In Status</b></td>
				<td><b>Out Status</b></td>
				<td><b>Status</b></td>
				<td><b>Present</b></td>
				<td></td>
			</tr>

			<c:forEach var="item" items="${todayAttObj}"
				varStatus="status">
				<tr class="tbl" name="trr" id="resultDataRow${status.count}" height="25px"
					style="color: black; ">
					<td align="center" class="data0"><b>${item.fid}</b></td>
					<td class="data1">${item.name}&nbsp;${item.fatherName}</td>
					<c:if test="${empty item.cdate}">
     				 		<td>white space</td>
					</c:if>
					<c:if test="${not empty item.cdate}">
							<td class="data2">&nbsp; ${item.cdate} </td>
					</c:if>
					<td class="data3">&nbsp;${item.intime}</td>
					<td class="data4">&nbsp;${item.outtime}</td>
					<td class="data5">&nbsp;${item.department}</td>
					<td class="data6" align="center">&nbsp;${item.intimestatus}</td>
					<td class="data7" align="center">&nbsp;${item.outtimestatus}</td>
					<td class="data8" align="center">${item.status}</td>
					<td class="data9" align="center">${item.present}</td>
					<td class="data10">
						<input type="checkbox" name="check" id="btnId${status.count}" class="checkbox" checked="true">
					</td>
				</tr>
			</c:forEach>
		</table>
		</section>	


	<!--Footer-->
	<footer  class="footer navbar-fixed-bottom" id="footer">
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

				<div class="span1">
					<a id="gototop" class="gototop pull-right" href="#"><i
						class="icon-angle-up"></i></a>
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
			<form class="form-inline" action="index.html" method="post"
				id="form-login">
				<input type="text" class="input-small" placeholder="Email">
				<input type="password" class="input-small" placeholder="Password">
				<label class="checkbox"> <input type="checkbox">
					Remember me
				</label>
				<button type="submit" class="btn btn-primary">Sign in</button>
			</form>
			<a href="#">Forgot your password?</a>
		</div>
		<!--/Modal Body-->
	</div>
	<!--  /Login form -->
	<%-- 
<script src="${pageContext.request.contextPath}/js/vendor/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/vendor/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script> --%>
</body>
</html>
