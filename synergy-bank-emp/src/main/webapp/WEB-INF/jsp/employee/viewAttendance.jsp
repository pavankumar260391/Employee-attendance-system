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
<script src="${pageContext.request.contextPath}/js/vendor/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/vendor/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>	
<meta charset="utf-8">
  <title>jQuery UI Datepicker - Default functionality</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  
  <script>
  $(function() {
      $( "#datepicker" ).datepicker({  maxDate: 0,
    	  changeMonth: true,
          changeYear: true,
          showButtonPanel: false,
          dateFormat: 'MM yy',
          onClose: function(dateText, inst) { 
              var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
              var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
              $(this).datepicker('setDate', new Date(year, month, 1));                    
          }
    	  }).datepicker("setDate", new Date());
    });
  </script>

<%@include file="eimport.jsp"%>
  <script src="${pageContext.request.contextPath}/js/employee/emp-attd-status.js"></script>

<script>  
    $(document).ready(function(){
    $("#dateButton").click(function(){
    	process();
    	var date = $("#datepicker").val();
    	var dArr = date.split(" ");
	    var month = dArr[0];
	    var year = dArr[1];
	    ajaxCallForTableResults(month,year);
    });
    
    function ajaxCallForComputingDays(month,year,computeDays){	
    	//var computeDays = "computeDays";
    	$.ajax({
    	    	url : contextPath+"/employee/viewAttendenceByDate",
    	    	data : {"month":month ,"year":year, "computeDays":computeDays},
    	    	dataType : "json",
    	    	type : "GET",
    	    	success : function(data){
    	    				showUpdatedData1(data);
    	    	}
    	    });
    }
    function showUpdatedData1(data){
    	$("#det").empty();
    	
    	$.each(data,function(i,item){
    	  $("#noOfWorkedDays").html(item.noOfDaysWorked);
    	  $("#noOfHolidays").html(item.noOfHolidays);
    	  $("#noOfLeaves").html(item.noOfApprovedLeaves);
    	  $("#noOfLwp").html(item.noOfLwp);
    	});
    }

    function ajaxCallForTableResults(month,year){	
    	var computeDays = "tableResults";
    	ajaxCallForComputingDays(month,year,"computeDays");
    	$.ajax({
    	    	url : contextPath+"/employee/viewAttendenceByDate",
    	    	data : {"month":month ,"year":year, "computeDays":computeDays},
    	    	dataType : "json",
    	    	type : "GET",
    	    	success : function(data){
    	    				showUpdatedData(data);
    	    	}
    	    });
    }

    function showUpdatedData(data){
    	
    	$('#employeeTable').empty();
    	        	
    	            var trHTML ="";
    	   trHTML += '<tr height="30px" style="color: black; background-color: #F1F1F1;" align="center"><td width="200px"><b>Att Date</b></td><td><b>In Time</b></td><td><b>Out Time</b></td><td><b>In Status</b></td><td><b>Out Status</b></td><td><b>Present</b></td></tr>';
    	            $.each(data, function (i, item) {
    	            	var counter = 0;	            	
    	            	var date = formatDate(new Date(getDateFromFormat(item.cdate,"yyyy-MM-dd")),"dd-MM-yyyy");
    	            	
    	    trHTML += '<tr class='+item.color+' id="resultDataRow${status.count}" height="25px" style="color: black; "><td class="data2">' +item.cdate+'</td><td class="data3">'+item.intime+'</td><td class="data4">'+item.outtime+'</td><td class="data6" align="center">'+item.intimestatus+'</td><td class="data7" align="center">'+item.outtimestatus+'</td><td class="data" align="center">&nbsp;'+item.present+'</td></tr>';
    	     });
    	            $('#employeeTable').append(trHTML);
    }
    function process(){
        var feb;
   	    var temp = $("#datepicker").val();
   	    var dArr = temp.split(" ");
   	    var month = dArr[0];
   	    var year = dArr[1];	
   	    if(year%4 != 0)
   	    	feb = 28;
   	    else
   	    	feb = 29;
 	   var monthEnum={
    	January : 31,February : feb,March : 31,April : 30,May : 31,June : 30,
    	July : 31,August : 31,Septempber : 30,October : 31,November : 30,December : 31
    	} 
 	   var noOfDays;
 	   switch(month){  
 	   case 'January' : noOfDays = monthEnum.January; break;
 	   case 'February' : noOfDays = monthEnum.February;break;
 	   case 'March' : noOfDays = monthEnum.March;break;
 	   case 'April' : noOfDays = monthEnum.April;break;
 	   case 'May' : noOfDays = monthEnum.May;break;
 	   case 'June' : noOfDays = monthEnum.June;break;
 	   case 'July' : noOfDays = monthEnum.July;break;
 	   case 'August' : noOfDays = monthEnum.August;break;
 	   case 'September' : noOfDays = monthEnum.September;break;
 	   case 'October' : noOfDays = monthEnum.October;break;
 	   case 'November' : noOfDays = monthEnum.November;break;
 	   case 'December' : noOfDays = monthEnum.December;break;
 	   }
   	  $("#nod").html(noOfDays);
    }
    });
</script>
<style>
  .ui-datepicker-calendar {
    display: none;
    } 
    .ui-datepicker {
width: 22em; 
}

.data{
	padding : 18px;
}
.normal_color{
	background-color : #FFFFE0;
}
.late_in_color{
	background-color : #E0FFFF;
}
.both{
	background-color : #90EE90;
}
.early_out_color{
	background-color : #EEE8AA;
}
.holiday_color{
	background-color : #F9E9ED;
}

.normal {
  background-color: #FFFFE0;
  border: 1px solid #FFFFE0;
}
.late_in {
  background-color: #E0FFFF;
  border: 1px solid #E0FFFF;
  	padding : 0px;
}
.both {
  background-color: #90EE90;
  border: 1px solid #90EE90;
}
.early_out {
  background-color: #EEE8AA;
  border: 1px solid #EEE8AA;
}
.holiday {
  background-color: #FFB6C1;
  border: 1px solid #FFB6C1;
}
.leave_color{
background-color: #FFB6C1;
  border: 1px solid #FFB6C1;
}
</style>
</head>

<body>

	<!--Header-->
	<%@include file="eheader.jsp"%>
	<!-- /header -->


		

	<!-- / .title -->
	<section id="portfolio" class="container main">						
			<table align="left" id="dateTable">
					<tr style="color: #000000;">
						
						<td class="data">
							Department : <b>${empDetails.department}</b>
						</td>
						<td class="data" id="desig">
							Designation : <b>${empDetails.designation}</b> 
						</td>
						<td class="data"> 
							No of Days Worked : <span id="noOfWorkedDays"><b>${noOfDaysWorked}</b></span>
						</td>
						<td class="data">
							 Working Days : <span id="nod"><b>${noOfWorkingDays}</b></span>
						</td>
						<td class="data">
							 LWP : <span id="noOfLwp"><b>${noOfLwp}</b></span>
						</td>
						<td class="data">
							 No of Holidays : <span id="noOfHolidays"><b>${noOfHolidays}</b></span>
						</td>
						<td class="data">
							 No of Leaves(Approved) : <span id="noOfLeaves"><b>${noOfApprovedLeaves}</b></span>
						</td>
						</tr>
						<tr style="color: #000000; font-weight: bold;">
						    <td class="data">
								<b>Date </b><input type="text" id="datepicker" class="form-control">
							</td>
							<td class="data">
							<button type="button" id="dateButton" class="btn btn-primary">Go</button>
							</td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td style="text-align:right;"><button type="button" id="persist" class="btn btn-primary"><a href="${pageContext.request.contextPath}/employee/computeNoOfDaysForSalaryInAMonth">Save</button></td>
					    </tr>
			</table>

 <table class="table table-bordered table-hover" border="1" id="employeeTable"
			width="100%">
			<tr height="30px"
				style="color: black; background-color: #F1F1F1; font-size:20;"
				align="center">
				<td width="200px"><b>Att Date</b></td>
				<td><b>In Time</b></td>
				<td><b>Out Time</b></td>
				<td><b>In Status</b></td>
				<td><b>Out Status</b></td>
				<td><b>Present</b></td>
				<td><b>Description</b></td>
			</tr>

			<c:forEach var="item" items="${faculityDailyAttendanceReportVOs}"
				varStatus="status">
				<tr class="${item.color}" id="resultDataRow${status.count}" height="25px"
					style="color: black; ">
					<td class="data">&nbsp; ${item.cdate}</td>
					<td class="data">&nbsp;${item.intime}</td>
					<td class="data">&nbsp;${item.outtime}</td>
					<td class="data" align="center">&nbsp;${item.intimestatus}</td>
					<td class="data" align="center">&nbsp;${item.outtimestatus}</td>
					<td class="data" align="center">&nbsp;${item.present}</td>
					<td class="data" align="center">&nbsp;${item.description}</td>
				</tr>
			</c:forEach>
		</table>
		
	<div class="headline">
	  <h3>Key</h3>
	</div>
	<table height="100" width="170" class="info-table">
     <tbody>
      <tr class="normal">
        <td>Normal</td>
      </tr>
      <tr class="late_in">
        <td>Late In Status</td>
      </tr>
      <tr class="holiday">
        <td>Holiday</td>
      </tr>
      <tr class="early_out">
        <td>Early Out Status</td>
      </tr>
      <tr class="both">
        <td>Late In & Early Out</td>
      </tr>
     </tbody>
    </table>
	</section>

<%@include file="../common/footer.jsp"%>
</body>
</html>
