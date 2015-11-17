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
    $(document).ready(function() {
        $( "#dialog-form" ).dialog({
          autoOpen: false,
          height: 500,
          width: 500,
          modal: true,
          show: {
            effect: "blind",
            duration: 500
          },
          hide: {
            effect: "explode",
            duration: 500
          }
        });   
      });
    
   
    $(document).ready(function(){
    $('#depSelect').change(function(){    	
    	var department = $('#depSelect').find('option:selected').val();
    	var selectDate = $("#datepicker").val();
    	ajaxCallForPastDateAndDep(selectDate,department)
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
					<h3>Welcome Name</h3>
				</div>
				<div class="span6">
					<ul class="breadcrumb pull-right">
						<li><a href="index.html">Home</a> <span class="divider">/</span></li>
						<li><a href="#">Pages</a> <span class="divider">/</span></li>
						<li class="active">Portfolio</li>
					</ul>
				</div>
			</div>
		</div>
	</section>
	<!-- / .title -->

	<section id="portfolio" class="container main">
		<ff:form class="form-inline" commandName="departmentForm">
			<table width="1150" border="0" align="left">

				<tbody>
					<tr style="color: #000000; font-weight: bold;">
						<td>
							<p>
								<b>Date: </b><input type="text" id="datepicker" value=""
									class="form-control">
							</p>
						</td>
						<td style="padding-right: 12cm;"><label for="depSelection"
							id="depLabel" class="control-label" style="font-weight: bold;">Department:
						</label> <ff:select path="departmentShortName" items="${depList}"
								id="depSelect" class="form-control">

							</ff:select></td>
						<!-- <td style=" padding-left:4cm;"><span 
									id="montlylateInfo" style="display: none; font-weight: bold;"></span>
						<span 
									id="montlyEarlyInfo" style="display: none; font-weight: bold;"></span></td> -->
					</tr>
				</tbody>

			</table>
		</ff:form>

		<table class="table table-bordered table-hover" id="employeeTable"
			width="100%">
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

			<c:forEach var="item" items="${faculityDailyAttendanceReportVOs}"
				varStatus="status">
				<tr id="resultDataRow${status.count}" height="25px"
					style="color: black; ">
					<td align="center" class="data0"><b>${item.fid}</b></td>
					<td class="data1">${item.name}&nbsp;${item.fatherName}</td>
					<td class="data2">&nbsp; <fmt:formatDate pattern="dd-MM-yyyy"
							value="${item.cdate}" />
					</td>
					<td class="data3">&nbsp;${item.intime}</td>
					<td class="data4">&nbsp;${item.outtime}</td>
					<td class="data5">&nbsp;${item.department}</td>
					<td class="data6" align="center">&nbsp;${item.intimestatus}</td>
					<td class="data7" align="center">&nbsp;${item.outtimestatus}</td>
					<td class="data8" align="center">${item.status}</td>
					<td class="data9" align="center">${item.present}</td>
					<td class="data10">
						<button id="btnId${status.count}" class="btn btn-primary"
							onClick="popupOpen(this.id)">
							<span class="icon-pencil" aria-hidden="true"></span>Edit
						</button>
					</td>
				</tr>
			</c:forEach>
		</table>

		<div id="dialog-form" title="Change Employee Information"
			class="container">

			<table width="100%">
				<ff:form method="post" role="form" class="form-horizontal" id="employeeForm"
					commandName="EmptyfacultyAttendStatusVO">

					<div class="control-group">
						<div class="controls controls-row">
							<tr>
								<td><label for="name" class="control-label">Name</label> <input
									type="text" name="name" id="empName" class="form-control"
									value="" readonly></input></td>
									<td><label for="id" class="control-label">Id</label> <input
									type="text" name="fid" id="empId" class="form-control"
									value="" readonly></input></td>
								
							</tr>
						</div>
					</div>

					<div class="control-group">
						<div class="controls controls-row">
							<tr>
							<td><label for="instatus" class="control-label">In
										Status</label> <select name="intimestatus" id="inStatus"
										class="form-control" value="">
										<option value="Normal">Normal</option>
										<option value="Late">Late</option>
									</select></td>
								<td><label for="attendus" class="control-label">Attendus
										Date</label> <input type="text" name="cdate" id="attdDate"
									class="form-control" value="" readonly></input></td>
								
							</tr>
						</div>
					</div>
					<div class="control-group">
						<div class="controls controls-row">
							<tr></tr>
							<tr>
							<td><label for="outstatus" class="control-label">Out
										Status</label> <select name="outtimestatus" id="outStatus"
										class="form-control">
										<option value="Normal">Normal</option>
										<option value="Early">Early</option>
									</select></td>
								<td><label for="intime" class="control-label">In
										Time</label> <input type="text" name="intime" id="inTime"
									class="form-control" value=""></input></td>
								
							</tr>
						</div>
					</div>
					<div class="control-group">
						<div class="controls controls-row">
							<tr></tr>
							<tr>
							<td><label for="status" class="control-label">Status</label>

									<select name="status" id="status" class="form-control">
										<option value="Normal">Normal</option>
										<option value="Late">Late</option>
									</select></td>
								<td><label for="outtime" class="control-label">Out
										Time</label> <input type="text" name="outtime" id="outTime"
									class="form-control" value=""></input></td>
								
							</tr>
						</div>
					</div>
					<div class="control-group">
						<div class="controls controls-row">
							<tr></tr>
							<tr>
							<td><label for="present" class="control-label">Present</label>

									<select name="present" class="form-control" id="present"
										value="">
										<option value="Yes">Yes</option>
										<option value="No">No</option>
									</select></td>
								<td><label for="department" class="control-label">Department</label>

									<input type="text" name="department" id="department"
									class="form-control" value="" readonly></input></td>
								
							</tr>
						</div>
					</div>
					<div class="control-group">
					<td><input type="button" onclick="updateEmployee()"
									value="Update" class="btn btn-primary"></input> <input
									type="button" onclick="deleteAttendus()" value="Delete"
									class="btn btn-danger"></input></td>


						</div>

					</div>
				</ff:form>
			</table>
			<div id="dialog-success-message" title="Updation Status">
				<span id="updation-success"
					style="display: none; font-weight: bold;"></span>
			</div>
	</section>

	<!--Footer-->
	<footer id="footer">
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
