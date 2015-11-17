<%@ taglib uri="http://www.springframework.org/tags/form" prefix="ff"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html><%@ taglib
	uri="http://www.springframework.org/tags/form" prefix="ff"%>
<%@ page isELIgnored="false"%>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Leave Approval Manager| Nova</title>
   <%@include file="eimport.jsp" %>
   


<!-- <script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"
	type="text/javascript"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/tooltipster/3.3.0/js/jquery.tooltipster.js"
	type="text/javascript"></script>
	
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/tooltipster/3.3.0/css/tooltipster.css">

 -->
<script>

function ajaxCall(buttonId){
	var rowId = $('#'+buttonId).closest('tr').attr('id');
	var reqId = $('#'+rowId).children('td#requestID').text();
		
	var sendVal = $('#'+buttonId).val();
		
	$.ajax({
		url:"${pageContext.request.contextPath}/employee/updateLeaveManagerApprovalbyAjax",
		data:{"approvalStatus":sendVal,"requestID":reqId},
		type:"get",
		success:function(data){
			$("#"+rowId).remove();
			
		}
	});
	
}
/* 	$(document).ready(function(){
	
	$('#managerApproval').click(function(){
	var requestID = $("#requestID").val
	var 
		$.ajax({
			url:"${pageContext.request.contextPath}/employee/updateLeaveManagerApprovalbyAjax",
			data:{"requestID":requestID},
			type:"post",
			success:fuction(managerApproval)
			{
				
		

			}
});
	});
	});
 *//* function approveLeave(buttonId){
	   
	   var rowId = $("#"+buttonId).closest('tr').attr("id");
	   /* alert(rowId); */
/* 	   var name = $("#"+rowId).children("td.data0").text();
	   
	   var selVal = $("#"+rowId).children(this).find('option:selected').val();
	   $.ajax({
		   url:"${pageContext.request.contextPath}/employee/updateApproval",
		   data:{"empName":name,"decision":selVal},
		   type:"post",
		   success:function(data){
 */			  // alert(data);
			 /*   var empName = $(this).find('option:selected').text();
			   ajaxCall(empName); */
			   //another ajax call which empties the table rows
		 //  }
	  // }); */

</script>
</head>

<body>

    <!--Header-->
     <%@include file="eheader.jsp" %>
    <!-- /header -->

    <section class="title">
        <div class="container">
            <div class="row-fluid">
                <div class="span6">
                    <h3>Leave Balance</h3>
                </div>
                <div class="span6">
                    <ul class="breadcrumb pull-right">
                        <li><a href="index.html">Home</a> <span class="divider">/</span></li>
                        <li><a href="#">Pages</a> <span class="divider">/</span></li>
                        <li class="active">Leave Balance</li>
                    </ul>
                </div>
            </div>
        </div>
    </section>
	<!-- / .title -->
		<!-- <div class="col-xs-12">
             				<select name="selectedGroupName" class="form-control"  style="width: 300px;">
								<option>JAN</option>   
								<option>FEB</option>
								<option>MAR</option>
								<option>APR</option>
							</select> 
							&nbsp;&nbsp;&nbsp;&nbsp;
							<input
								type="button" value="Show Attendance"
								id="loadUsersId"  class="btn btn-info" style="height:30px;margin-top: -6px;"/>
            	</div> -->
 <ff:form
					action="${pageContext.request.contextPath}/employee/managerApproveLeave"
					method="post" commandName="facultyAttendStatusVO">
    <section id="portfolio" class="container main"> 
    <legend style = "font-size: 14px; text-align: left;"><b>Leave Apply Manager Approval</b></legend>   
       <table class="table table-bordered table-hover"
			style="width: 70%; margin-left: 1px; margin-right: auto;">
			<thead>
				<tr height="30px"
					style="color: black; background-color: #F1F1F1; vertical-align: middle;"
					align="center">
						<td><b>Request ID</b></td>
						<td><b>Name</b></td>
						<td><b>Leave Form</b></td>
						<td><b>Leave To</b></td>
						<td><b>Total Days</b></td>
						<td><b>Leave Type</b></td>
						<td><b>Approve Leave</b></td>
				</tr>
			</thead>
                        <tbody>
                        <c:forEach var= "empLeaveData" items = "${empLeaveDataList}" varStatus="status">
                            <tr bgcolor="#FFFFFF" style="color: black;" id="rowId${status.count}">
                            	<td id ="requestID">${empLeaveData.requestID}</td>
                            	<td>${empLeaveData.name}</td>
                            	<td><fmt:formatDate pattern="dd-MMM-yyyy"
												value="${empLeaveData.leaveFrom}"/></td>
								<td><fmt:formatDate pattern="dd-MMM-yyyy"
												value="${empLeaveData.leaveTo}"/></td>				
                            	<td>${empLeaveData.totalDays}</td>
                            	<td>${empLeaveData.leaveType}</td>
                            	<td>
                            	<input type="button" class="btn btn-primary" value="Yes" id="approve${status.count }" onclick="ajaxCall(this.id)"/>
                            	<input type="button" class="btn btn-primary" value="No" id="reject${status.count }" onclick="ajaxCall(this.id)"/>
                            	</td>
                            	<%-- <ff:radiobutton path="manager_approval"
							name="manager_approval" value="Yes" />Yes&nbsp; 
							<ff:radiobutton	name="manager_approval"
							path="manager_approval" value="No" />No
                            	</td> --%>
				<%--  <td colspan="1" style="width: 10%; align: right;">
					<select name = "managerApproval">
					<option value="${empLeaveData.requestID}">Yes</option>
					<option value="No">No</option>
					</select>
					</td>  --%>
						</tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    
                    <!-- <input type="submit" value="Submit" class="btn btn-info" id="submit"/> -->
               </section>
               </ff:form>



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

<script src="${pageContext.request.contextPath}/js/vendor/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/vendor/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>
