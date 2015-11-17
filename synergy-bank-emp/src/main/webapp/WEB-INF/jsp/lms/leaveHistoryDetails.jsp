
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<title>LMS | Leave History</title>
<%@include file="aimport.jsp"%>
<script src="${pageContext.request.contextPath}/js/admin/upAttd.js"></script>
<script>
	$(document).ready(function(){
		$('#markBttn').click(function(){
			var date1 = $("#datepicker1").val();
			var date2 = $("#datepicker2").val();
			//var eid = $("#eid").val();
			var eid = $("#tab #eid").text();  //.find('td[id="'+eid+'"]').val();
			alert(eid);
			ajaxCallForDateFilter(eid,date1,date2);
		})
	});

</script>
<style>

	#td{
		text-align:left;
	}
</style>

</head>

<body>
 
	<!--Header-->
	<%@include file="aheader.jsp"%>
	<!-- /header --> 

	<section class="title">
		<div class="container">
			<div class="row-fluid">
				<div class="span6">
					<h3>Leave History</h3>
				</div>
				
			</div>
		</div>
	</section>
	<!-- / .title -->

	<section id="portfolio" class="container main">
	<table id="tab" border="0" height="200" width="100%" text-align="left" >
		<tbody>
			
			<tr valign="bottom">
				<td id="eid"><div width="50" class="alert alert-info"><h4>EmpId : ${faculityLeaveMasterVOslist.get(1).empNo}</h4></div></td>
				<td><div width="50" class="alert alert-info"><h4>Department :  ${faculityLeaveMasterVOslist.get(1).department}</h4></div></td>
 				<td align="center"> <img src="${pageContext.request.contextPath}/admin/images?id=${faculityLeaveMasterVOslist.get(1).empNo}" width="120" height="150"/></td>
 			</tr>
			<tr>
				<td><div class="alert alert-info"><h4>Name :  ${faculityLeaveMasterVOslist.get(1).name}</h4></div></td>
				<td><div class="alert alert-info"><h4>Designation :  ${faculityLeaveMasterVOslist.get(1).designation}</h4></div></td>
				 <td><div class="alert alert-info"><h4>Date of join :  <fmt:formatDate pattern="dd-MMM-yyyy"
							value="${faculityLeaveMasterVOslist.get(1).doj}" /></h4></div></td>
			</tr>
			<tr>
				<td>
				<p>
					<b>From: </b><input type="text" id="datepicker1" class="form-control">
			
				</td>
				<td>
				
					<b>To: </b><input type="text" id="datepicker2" class="form-control">
				
				</td>
				<td>					  	 
						<button type="button" width="10px" class="btn btn-primary" id="markBttn">Go</button>
				</td>
			</tr>
		</tbody>
	</table>
	<br>
	
		<table id="chgTable" class="table table-bordered table-hover" width="100%">
			<tr height="30px"
				style="color: black; background-color: #F1F1F1; vertical-align: middle;"
				align="center">
				<td id="td"><b>SNO.</b></td>
				<td id="td"><b>Date from</b></td>
				<td id="td"><b>Date to</b></td>
				<td id="td"><b>Total day(s)</b></td>

				<td id="td"><b>Leave type</b></td>
				<td id="td"><b>Leave category</b></td>
				<td id="td"><b>Leave status</b></td>
				<td id="td"><b>Purpose</b></td>
			</tr>

			<c:forEach varStatus="sn" var="facleavelist"
				items="${faculityLeaveMasterVOslist}">
				<tr bgcolor="#FFFFFF" style="color: black;">
					<td id="td">${sn.count}</td>
					<td id="td"><fmt:formatDate pattern="dd-MMM-yyyy"
							value="${facleavelist.leaveFrom}" /></td>
					<td id="td"><fmt:formatDate pattern="dd-MMM-yyyy"
							value="${facleavelist.leaveTo}" /></td>
					<td id="td">${facleavelist.totalDays}</td>
					<td id="td">${facleavelist.leaveType}</td>
					<td id="td">${facleavelist.leaveCategory}</td>
					<td id="td">${facleavelist.lstatus}</td>
					<td id="td">${facleavelist.purpose}</td>
				</tr>
			</c:forEach>

		</table>
		<div style="clear: both;"></div>
		<br> <br> <br> <br> <br> <br> <br>
		<br>
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
				<!--/Goto Top-->
			</div>
		</div>
	</footer>
	<!--/Footer-->
</body>
</html>
