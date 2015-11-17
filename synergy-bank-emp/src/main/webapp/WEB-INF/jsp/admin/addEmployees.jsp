<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="ff"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />

<!-- Load jQuery JS -->
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<!-- Load jQuery UI Main JS  -->
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

<!-- Load SCRIPT.JS which will create datepicker for input field  -->
<script src="script.js"></script>

<link rel="stylesheet" href="runnable.css" />



<title>Add Employees| BAS</title>
<%@ include file="aimport.jsp"%>
<style>
.error {
	color: red;
	font-size: 12px;
}
</style>
<script>
      $(document).ready(
    		  
    		  /* This is the function that will get executed after the DOM is fully loaded */
    		  function () {
    		    $( "#datepicker" ).datepicker({
    		      changeMonth: true,//this option for allowing user to select month
    		      changeYear: true //this option for allowing user to select from year range
    		    });
    		  }

    		);
      
      </script>

<script>
   $(document).ready(function(){
	   
	   $('#springForm').validate({ // initialize the plugin
	        rules: {
	        	DesignationName: {
	                required: true,
	                minlength:2
	                
	            },
	            description: {
	                required: true,
	                
	            }
	        }
	    });
   });
   </script>
</head>
<body>

	<!--Header-->
	<%@ include file="aheader.jsp"%>
	<!-- /header -->

	<section class="title">
	<div class="container">
		<div class="row-fluid">
			<div class="span6">
				<h4>Admin Page</h4>
			</div>
			<div class="span6"></div>
		</div>
	</div>
	</section>

	<section id="portfolio" class="container main"> 
	
	<ff:form
		action="${pageContext.request.contextPath}/admin/addEmployees"
		method="post" id="springForm" commandName="facultyForm">
		<fieldset>
			<table width="630" border="0" align="left">

				<!-- <tr bgcolor="#66CCFF" style="color: #000000; font-weight: bold;">
								<td>Designation Id</td>
								<td><input  type="text" id="designationId" name="designationId"
										size="10" style="height: 15px"></input>
										  
										</td> -->
				<tr style="color: #000000; font-weight: bold;">
					<td colspan="2"><span
						style="color: red; font-weight: bold; font-size: 14px;">${error}</span></td>
				</tr>

				<tr style="color: #000000; font-weight: bold;">
					<td>First Name <span
						style="color: red; font-weight: bold; font-size: 16px;">*</span></td>
					<td><input type="text" id="firstName" placeholder="first name"
						class="error" name="firstName" size="10"
						style="height: 15px; width: 250px;"></input>
						<p id="designationError" style="display: none;">Please enter
							the first name</p>
				</tr>

				<tr style="color: #000000; font-weight: bold;">
					<td>Sur-Name</td>
					<td><input type="text" placeholder="surname" id="surname"
						class="error" name="surname" size="10"
						style="height: 15px; width: 250px;"></input>
						<p id="designationError" style="display: none;">Optional</p>
				</tr>


				<tr style="color: #000000; font-weight: bold;">
					<td>Gender <span
						style="color: red; font-weight: bold; font-size: 16px;">*</span></td>
					<td><select name="gender">
							<option value="Gender">Male</option>
							<option value="Gender">Female</option>
					</select></td>
				</tr>


				<!-- <tr  style="color: #000000; font-weight: bold;">
								<td>Birth Date</td>
								<td>
								<input  type="text" id="email" class="error" name="email"
										size="10" style="height: 15px;width: 250px;"></input>
								<p id="designationError" style="display: none;">Optional</p>		  
										 
							</tr> -->
				<tr style="color: #000000; font-weight: bold;">
					<td><p>
							Birth Date: <span
								style="color: red; font-weight: bold; font-size: 16px;">*</span></td>
					<td><input type="text" id="datepicker"
						placeholder="birth date" class="error" name="dob" size="10"
						style="height: 25px; width: 250px;" /></input>
					</p></td>
				</tr>
				<tr style="color: #000000; font-weight: bold;">
					<td>Email <span
						style="color: red; font-weight: bold; font-size: 16px;">*</span></td>
					<td><input type="text" placeholder="eg:abc@mail.com"
						id="email" class="error" name="email" size="10"
						style="height: 15px; width: 400px;"></input>
						<p style="display: none;">Please enter the email</p>
				</tr>
				<tr style="color: #000000; font-weight: bold;">
					<td>Mobile <span
						style="color: red; font-weight: bold; font-size: 16px;">*</span></td>
					<td><input type="text" id="mobile"
						placeholder="enter phone number" class="error" name="mobile"
						size="10" style="height: 15px; width: 250px;"></input>
						<p style="display: none;">Please Enter the Mobile number</p>
				</tr>
				<tr style="color: #000000; font-weight: bold;">
					<td>Type <span
						style="color: red; font-weight: bold; font-size: 16px;">*</span></td>
					<td><select name="employeeType">
							<option value="Employee">Employee</option>
							<option value="Supporting Staff">Supporting Staff</option>
					</select></td>

				</tr>

		 <tr style="color: #000000; font-weight: bold;">
					<td>Designation <span
						style="color: red; font-weight: bold; font-size: 16px;">*</span></td>
					<td><ff:select path="department" items="${desigList}">
					</ff:select></td>
				</tr>

			<tr style="color: #000000; font-weight: bold;">
					<td>Department <span
						style="color: red; font-weight: bold; font-size: 16px;">*</span></td>
					<td><ff:select path="department" items="${depList}">
					</ff:select></td>
				</tr>


			
<tr style="color: #000000; font-weight: bold;">
					<td>Reporting Manager <span
						style="color: red; font-weight: bold; font-size: 16px;">*</span></td>
					<td><ff:select path="department" items="${reportingManagerList}">
					</ff:select></td>
				</tr>

				<div class="mbm">

					<tr bgcolor="white" style="color: #000000; font-weight: bold;">
						<td colspan="2">&nbsp;</td>
					</tr>
					<tr bgcolor="white" style="color: #000000; font-weight: bold;">
						<td><input type="submit" class="btn btn-info" size="30"
							value="Create Account"></input></td>
						<td>&nbsp;</td>
					</tr>
			</table>
		</fieldset>
	</ff:form> </section>
	<br />
	<br />
	<br />
	<br />
	<br />



</body>




</html>


