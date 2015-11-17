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
<script src="${pageContext.request.contextPath}/js/admin/workingDays.js"></script>

<style>
.ui-datepicker {
	width: 18em; 
}
</style>

<style type="text/css">
table tr{
	padding : 5px;
}
.lwpColor{
	background-color : #FFFCD7;
}
</style>

<body>

	<!--Header-->
	<%@include file="aheader.jsp"%>
	<!-- /header -->
<br><br><br>
	<section id="portfolio" class="container main">
		 <ff:form class="form-inline" commandName="departmentForm">
			<table width="1150" border="0" align="left">

				<tbody>
					<tr style="color: #000000; font-weight: bold;">
						<td>							
								<b>Date: </b><input type="text" id="datepicker" value=""
									class="form-control">
						</td>
						<td style="padding-right: 5cm;"><label for="depSelection"
							id="depLabel" class="control-label" style="font-weight: bold;">Department:
						</label> 
<!-- 						onchange="javascript:ajaxOnDepartmentChange();
 -->						<ff:select path="departmentShortName" items="${depList}"
								id="depSelect" class="form-control">
							</ff:select></td>

						<td style="text-align:right">							
								<p id="totalDays">Total : ${totalEmp}</p>
						</td>
					</tr>
				</tbody>

			</table>
		</ff:form> 
 <br><br>
		<table class="table table-bordered table-hover" id="employeeTable">
			<thead>
			<tr style="color: black; background-color: #F1F1F1;">
				<td style="text-align:left;"><b>Sl. no</b></td>
				<td width="50px" style="text-align:left;"><b>Emp ID</b></td>
				<td style="text-align:left;"><b>Name</b></td>
				<td style="text-align:left;"><b>Designation</b></td>			
				<td style="text-align:left;"><b>Department</b></td>
				<td style="text-align:left;"><b>Photo</b></td>
				<td style="text-align:center;"><b>Purpose</b></td>	
<!-- 				<td id="tdd"><button type="button" width="150px" class="btn btn-danger btn-sm" id="markBttn"><span class="icon-pencil" aria-hidden="true"></span> Mark LWP For All</button>
 -->			 
 			</tr>
			</thead>
			
			<tbody>
			<c:forEach var="item" items="${EmpWithLwp}" varStatus="status">
				<tr class = "${item.color}" id="resultDataRow${item.empNo}">
					<td style="text-align:left;">${status.count}</td>
					<td class="data0" style="text-align:left;">${item.empNo}</td>
					<td class="data1" style="text-align:left;">${item.name}</td>
					<td class="data2" style="text-align:left;">&nbsp; ${item.designation}</td>
					<td class="data3" style="text-align:left;">&nbsp; ${item.department}</td>					
 					<td><img src="${pageContext.request.contextPath}/admin/getImageById?Id=${item.empNo}" width="24" height="24"></td>
 					<td class="data4">${item.purpose}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>	
	</section>

<%@include file="../common/footer.jsp"%>



</body>
</html>