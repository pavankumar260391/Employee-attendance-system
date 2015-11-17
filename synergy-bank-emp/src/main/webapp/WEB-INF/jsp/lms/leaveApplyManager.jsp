
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="ff"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>

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
<title>LMS |Leave Apply Manager</title>

<script src="//code.jquery.com/jquery-1.11.3.js" type="text/javascript"></script>

<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"
	type="text/javascript"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/tooltipster/3.3.0/js/jquery.tooltipster.js"
	type="text/javascript"></script>
	
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script> 
	
<!--include jQuery -->
<!-- <script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"
	type="text/javascript"></script> -->

<!-- <script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"
	type="text/javascript"></script> -->

<!--include jQuery Validation Plugin-->
<!-- <script
	src="//ajax.aspnetcdn.com/ajax/jQuery.validate/1.11.1/jquery.validate.js"
	type="text/javascript"></script> -->

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

<script
	src="${pageContext.request.contextPath}/js/vendor/bootstrap.min.js"></script>
<!--<script src="${pageContext.request.contextPath}/js/main.js"></script>
<script src="${pageContext.request.contextPath}/js/BeatPicker.js"></script>-->

<!-- <script
	src="http://jqueryvalidation.org/files/dist/additional-methods.min.js"></script> -->

<!-- <script
	src="http://ajax.microsoft.com/ajax/jquery.validate/1.7/additional-methods.js"></script> -->
<!-- <script
	src="http://ajax.microsoft.com/ajax/jquery.validate/1.7/jquery.validate.js"></script> -->

<%@include file="eimport.jsp"%>

<style type="text/css">
html {
	-webkit-font-smoothing: antialiased;
}
</style>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/tooltipster/3.3.0/css/tooltipster.css">

<script type="text/javascript">
	$(document)
			.ready(
					function() {

						$("#description").hide();
						$("#reason").hide();

						$("#lvmeeting").hide();

						$
								.ajax({
									url : "${pageContext.request.contextPath}/admin/facultyidAndNames.htm",
									type : "GET",
									success : function(msg) {
										var data = new Array();
										data = msg.split(',');
										//alert(data);
										AutoComplete_Create('searchFiled', data
												.sort(), 6);
									} //end of result
								});

					});
</script>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						//			$('.datepicker').datepicker();
						$('#leaveFrom1')
								.datepicker(
										{
											showOn : "button",
											buttonText : 'Show Date',
											buttonImage : "http://www.triconsole.com/php/calendar/calendar/images/iconCalendar.gif",
											buttonImageOnly : true
										});

						$(".ui-datepicker-trigger").mouseover(function() {

							$(this).css('cursor', 'pointer');

						});
					});
</script>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						//	$('.datepicker').datepicker();
						$('#leaveTo1')
								.datepicker(
										{
											showOn : "button",
											buttonText : 'Show Date',
											buttonImage : "http://www.triconsole.com/php/calendar/calendar/images/iconCalendar.gif",
											buttonImageOnly : true
										});

						$(".ui-datepicker-trigger").mouseover(function() {

							$(this).css('cursor', 'pointer');

						});

					});
</script>

<script type="text/javascript">
	function leaveCategoryCheck() {
		if (document.getElementById('lc1').checked) {
			$("#lvmeeting").show();
		} else {
			$("#lvmeeting").hide();
			$('input[name=leaveMeeting]:checked').val("NA");
		}
	}
</script>

<script type="text/javascript">
	function checkType() {
		$("#description").hide();
		if (document.getElementById("leaveType1").value == "Others") {
			$("#description").show();

		} else {
			$("#description").hide();

		}
	}
</script>

<script type="text/javascript">
	function checkReason() {
		$("#reason").hide();
		if (document.getElementById("purpose").value == "Others") {
			$("#reason").show();

		} else {
			$("#reason").hide();

		}
	}
</script>

<script type="text/javascript">
	function totalLeaveCheck() {

		$("#leaveFrom1").datepicker({
			dateFormat : 'mm/dd/yyyy'
		});
		$("#leaveTo1").datepicker({
			dateFormat : 'mm/dd/yyyy'
		});

		$("#leaveTo1").change(
				function() {

					var leaveTo = $("#leaveTo1").val();
					if (leaveTo.trim().length == 0) {
						//	alert("Leave to date cannot be blank.");
						$("#leaveTo1").focus();
						return;
					}
					var leaveFrom = $("#leaveFrom1").val();
					if (leaveFrom.trim().length == 0) {
						//	alert("Leave From date cannot be blank.");
						$("#leaveFrom1").focus();
						return;
					}

					if ($("#leaveFrom1").val() == $("#leaveTo1").val()) {
						$("#totalDays").val(1);
						return;
					}

					else if ($("#leaveFrom1").val() != ''
							|| $("#leaveTo1").val() != '') {

						var diff = ($("#leaveTo1").datepicker("getDate") - $(
								"#leaveFrom1").datepicker("getDate"))
								/ 1000 / 60 / 60 / 24; // days
						if (diff > 0) {
							$("#totalDays").val(diff);
						} /* else {
																						alert("Invalid date inputs")
																						$("#leaveFrom1").focus();
																						$("#leaveFrom1").clear();
																						return false;
																					} */
					}

				});

		$("#leaveFrom1").change(
				function() {

					if ($("#leaveFrom1").val() == $("#leaveTo1").val()) {
						$("#totalDays").val(1);
						return;
					}

					else if ($("#leaveFrom1").val() != ''
							&& $("#leaveTo1").val() != '') {

						var diff = ($("#leaveTo1").datepicker("getDate") - $(
								"#leaveFrom1").datepicker("getDate"))
								/ 1000 / 60 / 60 / 24; // days
						if (diff > 0) {
							$("#totalDays").val(diff);
						} /* else {
																						alert("Invalid date inputs")
																						$("#leaveFrom1").focus();
																						$("#leaveFrom1").clear();
																						return false;
																					} */
					}

				});

	}
</script>

<!--  <script type="text/javascript">
$(document).ready(function() {
	
/*     $('input[name="mobile"]').focusout(function(){
    
    	var phone = $('input[name="mobile"]').val(),
        intRegex = ;/\(?[\d\s]{3}\)[\d\s]{3}-[\d\s]{4}$/
    if((phone.length != 10) || (!intRegex.test(phone)))
    {
    	$("#mobMsg").show();
    	$('#mobMsg').css("color","red");
    	$("#mobMsg").html(" Invalid!!");
         return false;
    } else{
    	
    	$("#mobMsg").hide();
    }
    });
 */
    jQuery.validator.addMethod("phoneUS", function (phone_number, element) {
    phone_number = phone_number.replace(/\s+/g, "");

    return this.optional(element) || phone_number.length != 10 &&
          phone_number.match(/[0-9 -()+]+$/);
}, "Invalid phone number");  

 
	 $("#leaveForm").validate(
	        {
	            rules: { mobile: { required: true, phoneUS: true } },
	            messages: { mobile: { required: "required ", phoneUS: "US format " } }
	        });   
});
</script> -->

<script type="text/javascript">
	$(document).ready(function() {

		// initialize tooltipster on text input elements
		$('#leaveForm ff:input[type="text"]').tooltipster({
			trigger : 'custom',
			onlyOne : false,
			position : 'right',
			updateAnimation : false,
			animation : 'grow'
		});

		/* // initialize tooltipster on radio input elements
		$('#leaveForm ff:input[type="radio"]').tooltipster({
			trigger : 'custom',
			onlyOne : false,
			position : 'top',
			updateAnimation : false,
			animation : 'grow'
		}); */

		// initialize validate plugin on the form
		$('#leaveForm').validate({
			errorPlacement : function(error, element) {
				//$(element).tooltipster('update', $(error).text());
				$(element).tooltipster('content', $(error).text());
				$(element).tooltipster('show');
			},
			success : function(label, element) {
				$(element).tooltipster('hide');
			},
			rules : {
				leaveType : {
					required : true
				},
				purpose : {
					required : true
				},
				reportingManager : {
					required : true
				},
				leaveCategory : {
					required : true
				},
				address : {
					required : true,
					rangelength : [ 10, 50 ]
				},
				mobile : {
					required : true,
					phoneUS : true

				}
			}
		/* ,
							        submitHandler: function (form) { // for demo
							            alert('valid form');
							            return false;
							        } */
		});

		/* 				function validateDonotSelect(value, element, param) {
		 if (value == param) {
		 return false;
		 } else {
		 return true;
		 }
		 }
		 jQuery.validator.addMethod("do_not_select",
		 validateDonotSelect, "Select an option");

		 jQuery.validator.addMethod("phoneUS", function(phone_number,
		 element) {
		 phone_number = phone_number.replace(/\s+/g, "");

		 return this.optional(element) || phone_number.length != 10
		 && !phone_number.match(/[0-9 -()+]+$/);
		 }, "Invalid phone number");

		 $.validator.addMethod("dateHigher", function(value,
		 element) {

		 //If false, the validation fails and the message below is displayed

		 var myDate = value;

		 return Date.parse(myDate) > $("#leaveTo1").val();

		 }, "Invalid request");
		
		 $.validator.addMethod("dateLower", function(value,
		 element) {

		 //If false, the validation fails and the message below is displayed

		 var myDate = value;

		 return Date.parse(myDate) < $("#leaveFrom1").val();

		 }, "Invalid request");

		 $('#leaveForm').validate({
		 rules : {
		 leaveType : {
		 do_not_select : 'seLT'
		 },
		 purpose : {
		 do_not_select : 'purp'
		 },
		 reportingManager : {
		 do_not_select : 'rM'
		 },
		 mobile : {
		 required : true,
		 phoneUS : true

		 },
		 leaveCategory : {
		 required : true

		 },
		 address : {
		 required : true,
		 rangelength : [ 50, 100 ]
		 }
		 },
		 messages : {
		 leaveType : {
		 do_not_select : "Select a leave type"
		 },
		 purpose : {
		 do_not_select : "Select a purpose"
		 },
		 reportingManager : {
		 do_not_select : "Select a reporting manager"
		 },
		 mobile : {
		 required : "required ",
		 phoneUS : "Invalid  "

		 },
		 leaveCategory : {
		 required : "Select a leave category"

		 }
		 },
		 errorPlacement : function(error, element) {
		 if (element.is(":radio")) {
		 error.appendTo(element.parents('.containers'));
		 } else { // This is the default behavior 
		 error.insertAfter(element);
		 }
		 }
		
		 /* errorPlacement: $.datepicker.errorPlacement, 
		 rules: { 
		 validBeforeDatepicker: { 
		 dpCompareDate: ['before', '#validAfterDatepicker'] 
		 }, 
		 validAfterDatepicker: { 
		 dpCompareDate: {after: '#validBeforeDatepicker'} 
		 } 
		 } //
		 }); */
	});
</script>

</head>

<body>

	<!--Header-->
	<%@include file="eheader.jsp"%>
	<!-- /header -->

	<section class="title">
		<div class="container">
			<div class="row-fluid">
				<div class="span6">
					<h3>Leave Apply</h3>
				</div>
				<div class="span6">
					<ul class="breadcrumb pull-right">
						<li><a href="index.html">Home</a> <span class="divider">/</span></li>
						<li><a href="#">Leave Management</a> <span class="divider">/</span></li>
						<li class="active">Leave Apply</li>
					</ul>
				</div>
			</div>
		</div>
	</section>
	<!-- / .title -->

	<section id="portfolio" class="container main">

		<%-- <div class="alert alert-info fade in" id="myAlert">
			<a href="#" class="close" data-dismiss="alert">&times;</a> <strong></strong>
			${successMessage}
		</div> --%>

		<ff:form
			action="${pageContext.request.contextPath}/employee/leaveApplyManager"
			method="post" commandName="faculityLeaveMasterVO" id="leaveForm">

			<table>
				<tr>
					<td style="color: blue; font-weight: bold;" align="left"><font
						style="color: black">Welcome </font>${faculityLeaveMasterVO.getName()}
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font
						style="color: black">Department:</font>
						${faculityLeaveMasterVO.getDepartment()}
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font
						style="color: black">Designation:</font>${faculityLeaveMasterVO.getDesignation()}
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <font
						style="color: black">Type:</font>
						${faculityLeaveMasterVO.getType()}</td>
				</tr>
			</table>
			<table border="0" style="width: 100%" class="table">
 					<tr height = "25px">
 					<td colspan="1" style="width: 10%; align: left;"><b>Employees
							 &nbsp; : </b> <ff:select path="name"
							style="background-color:#FFFFFF">
							<ff:options items="${employees_list}" />
						</ff:select></td>
					</tr>
			</table>

			<table>
				<tr>
					<td style="color: blue;" align="left"><font
						style="color: black"><b>Available Leaves:</b></font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font style="color: black"><b>EL: </b></font>${faculityLeaveMasterVO.totalEL}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font style="color: black"><b>CL: </b></font>
						${faculityLeaveMasterVO.totalCL}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font style="color: black"><b>SL: </b></font>
						${faculityLeaveMasterVO.totalSL}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font style="color: black"><b>OD: </b></font>${faculityLeaveMasterVO.od}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font style="color: black"><b>Status: </b></font><font
						style="color: black">New</font>&nbsp;&nbsp;<font
						style="color: black"><b>Created on: </b></font><font
						style="color: black"><%=new java.util.Date()%></font></td>
				</tr>
			</table>
			<%-- class="datepicker" --%>
			<table border="0" style="width: 100%" class="table">

				<tr height="25px">
					<td colspan="1" style="width: 30%; align: left;"><b>Leave
							From </b><font color="red">*</font> <ff:input type="text"
							path="leaveFrom" id="leaveFrom1" style="width: 30%"
							onchange="javascript:totalLeaveCheck();"
							class="required date" /><span id="errorFroMsg"
						style="color: red; font-size: 12px; display: none;"> Invalid</span></td>

					<td colspan="1" style="width: 30%; align: center;"><b>Leave
							To </b><font color="red">*</font> <ff:input type="text"
							path="leaveTo" id="leaveTo1" style="width: 30%"
							onchange="javascript:totalLeaveCheck();"
							class="required date" /><span id="errorToMsg"
						style="color: red; font-size: 12px; display: none;"> Invalid</span></td>
					<td colspan="1" style="width: 30%; align: right;"><b>Total
							day(s) &nbsp; :</b> <ff:input type="text" id="totalDays"
							path="totalDays" style="width: 10%" readonly="true" /></td>
				</tr>

				<tr height="25px">
					<td colspan="1" style="width: 10%; align: left;"><b>Leave
							Type </b><font color="red">*</font> <ff:select path="leaveType"
							name="leaveType" id="leaveType1"
							onchange="javascript:checkType();"
							style="background-color:#FFFFFF">
							<option value=''>(Select an option)</option>
							<option value='cl'>CL</option>
							<option value='el'>EL</option>
							<option value='od'>OD</option>
							<option value='comp'>Compensatory Leave</option>
							<option>Others</option>
						</ff:select> <ff:input type="text" path="description" id="description"
							style="background-color:white;" placeholder="Description"
							size="65" /></td>
					<!-- <span id="lTypeMsg" style="color: red;"></span> -->
					<td colspan="1" style="width: 10%; align: center;"><b>Purpose
					</b><font color="red">*</font> <ff:select path="purpose" name="purpose"
							style="background-color:white" id="purpose"
							onchange="javascript:checkReason();">
							<option value=''>(Select an option)</option>
							<option value='nfw'>Not feeling well</option>
							<option value='urg'>I have some urgent work at home</option>
							<option value='app'>I have appointment with doctor</option>
							<option>Others</option>
						</ff:select> <ff:input type="text" path="reason" id="reason"
							style="background-color:white;" placeholder="Description"
							size="65" /></td>
					<!-- <span id="purpMsg" style="color: red;"></span> -->
					<td><b>Mobile:</b> <ff:input type="text" name="mobile"
							path="mobile" id="mobile" style="height: 15px" /></td>
				</tr>
				<%-- onkeydown="return ValidateMobNumber('mobile')" --%>
				<tr height="25px">
					<td class="groupwrap container" colspan="1"
						style="width: 10%; align: left;"><b>Leave Category </b><font
						color="red">*</font> &nbsp;<ff:radiobutton path="leaveCategory"
							name="leaveCategory" onclick="javascript:leaveCategoryCheck();"
							id="lc1" value="HalfDay" />Half Day &nbsp; <ff:radiobutton
							onclick="javascript:leaveCategoryCheck();" name="leaveCategory"
							id="lc2" path="leaveCategory" value="FullDay" />Full Day</td>

					<td id="lvmeeting" colspan="1" style="width: 10%; align: center;"><b>Leave
							Meeting &nbsp; : </b> &nbsp;<ff:radiobutton name="leaveMeeting"
							path="leaveMeeting" id="fh" value="FirstHalf" />First Half
						&nbsp;<ff:radiobutton name="leaveMeeting" id="sh"
							path="leaveMeeting" value="SecondHalf" />Second Half</td>

					<td colspan="1" style="width: 10%; align: right;"><b>Address:</b>
						<ff:textarea type="text" path="address"
							style="background-color:white;" placeholder="(type here)"
							rows="2" /></td>

				</tr>

				<tr height="25px">
					<td colspan="1" style="width: 10%; align: left;"><b>Reporting
							Manager &nbsp; : </b> <ff:select path="reportingManager"
							name="reportingManager" style="background-color:#FFFFFF">
							<option value=''>(Select an option)</option>
							<ff:options items="${managerList}" />
						</ff:select></td>

					<td colspan="1" style="width: 10%; align: center;"><b>CC
							To &nbsp; : </b> <ff:select path="ccTo" name="ccTo"
							style="background-color:#FFFFFF" multiple="true">
							<ff:options items="${ccToList}" />
						</ff:select></td>
				</tr>

				<tr height="25px">
					<td colspan="1" style="width: 10%; align: right;"><input
						type="submit" value="Submit" class="btn btn-info" id="submit" />
						<input type="reset" value="Clear" class="btn btn-info" style="" /></td>
				</tr>
			</table>
		</ff:form>

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

</body>
</html>
