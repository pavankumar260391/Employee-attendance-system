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
 
/* .ui-datepicker-calendar {
   
}  */
 
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
.markBttn{
  disabled:true;
}
</style>

<script>   
    $(document).ready(function(){   
    		$('#goBtn').click(function(){
    			var department = $('#depSelect').find('option:selected').val();
    		    var selectDate = $("#datepicker").val();
    		    ajaxFilterByDepartment(selectDate,department);
    		});
    		
    	$(document).on("click", ".markBttn", function(){
			var btnVal = $(this).attr('value');
			if(btnVal == "Mark LWP"){
    		  $(this).attr("value", "Unmark LWP");
    		  $("#danger").hide();
    		  $("#fine").hide();
    		  $(this).parent().parent().css("background-color", "#FFFCD7");
    		  var id=$(this).parent().parent().children('td.data0').text();
    		  var info;
    		  //$(this).closest('tr').children("td.data4").find("input").attr("disabled","true");
    		  $(this).closest('tr').children("td.data5").find("input").attr("disabled","true");
    		  $(this).closest('tr').find("input").each(function() {
    			  info = this.value;
    	      });
    		  ajaxCallForMarkLwp(id,info);
		   	}
			else{
				  $(this).closest('tr').children("td.data4").find("input").removeAttr("disabled");
				  $(this).closest('tr').children("td.data5").find("input").removeAttr("disabled");
				  $(this).attr("value", "Mark LWP");
	    		  $("#danger").hide();
	    		  $("#fine").hide();
	    		  $(this).parent().parent().css("background-color", "white");
	    		  var id=$(this).parent().parent().children('td.data0').text();
	    		  ajaxCallForUnMarkLwp(id);
			}			
    	});    	
    });
</script>
</head>
<body>

	<!--Header-->
	<%@include file="aheader.jsp"%>
	<!-- /header -->
<br><br><br>
	<section id="portfolio" class="container main">
		 <ff:form class="form-inline" commandName="departmentForm">
			<table border="0" style="width:100%;" align="left">

				<tbody>
					<tr style="color: #000000; font-weight: bold;">
						<td>							
								<b>Date: </b><input type="text" id="datepicker" value=""
									class="form-control">
						</td>
						<td style="padding-right: 5cm;"><label for="depSelection"
							id="depLabel" class="control-label" style="font-weight: bold;">&nbsp;&nbsp;Department:
						</label> 
						<ff:select path="departmentShortName" items="${depList}" id="depSelect" class="form-control">
						</ff:select>&nbsp;&nbsp;&nbsp;
						<input type="button" id="goBtn" value="Go"></td>
						<td style="text-align:left;"></td>

						<td style="text-align:right;"><span style="text-align:right; float:right;"> Total : ${totalEmp}</span></td>
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
				<td style="text-align:center;"><b>Comment</b></td>	
<!-- 				<td id="tdd"><button type="button" width="150px" class="btn btn-danger btn-sm" id="markBttn"><span class="icon-pencil" aria-hidden="true"></span> Mark LWP For All</button>
 -->			 
 				<td><a href="">Mark LWP For All</a></td>									
 			</tr>
			</thead>
			
			<tbody>
			<c:forEach var="item" items="${todayAttObj}" varStatus="status">
				<tr class = "${item.color}" id="resultDataRow${item.fid}">
					<td style="text-align:left;">${status.count}</td>
					<td class="data0" style="text-align:left;">${item.fid}</td>
					<td class="data1" style="text-align:left;">${item.name}</td>
					<td class="data2" style="text-align:left;">&nbsp; ${item.designation}</td>
					<td class="data3" style="text-align:left;">&nbsp; ${item.department}</td>					
 					<td><img src="${pageContext.request.contextPath}/admin/getImageById?Id=${item.fid}" width="24" height="24"></td>
  					<c:choose>
  					<c:when test="${item.color == 'lwpColor'}">
  						<td class="data4"><form><input type="textarea" rows="1" name="textArea" id="text" disabled></form></td>
  						<td id="tdd"><input type="button" id="markBttn${status.count}" class = "markBttn" value="Unmark LWP"></td>
					</c:when>
					<c:otherwise>
					  	<td class="data5"><form><input type="textarea" rows="1" name="textArea" id="text"></form></td>
						<td id="tdd"><input type="button" id="markBttn${status.count}" class = "markBttn" value="Mark LWP"></td>
					</c:otherwise>
					</c:choose>
				</tr><p style="color:red; font-size:large;" id="danger"></p> <p style="color:green; font-size:large;" id="fine"></p>
			</c:forEach>
			</tbody>
		</table>	
	</section>

<%@include file="../common/footer.jsp"%>

</body>
</html>
