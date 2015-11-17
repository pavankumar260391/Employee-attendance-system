 function ajaxCall(option){
        $.ajax({
        	url: contextPath+"/employee/retreiveEmployeeInfo",	        	
        	type: "POST",
        	data:{"dateInfo":option},
        	dataType:'json',
        	success: function(result){	     	        		
        		$('#employeeTable').empty();	
        	        var trHTML ="";
        	        trHTML += "<tr height=\"30px\" style=\"color:black; background-color: #F1F1F1; vertical-align: middle;\" align=\"center\"> <td width=\"10px\"><b>SNO.</b></td><td width=\"250px\"><b>Att Date</b></td><td><b>In Time</b></td><td><b>Out Time</b></td><td><b>In Status</b></td><td><b>Out Status</b></td><td><b>Status</b></td><td><b>Present</b></td></tr>";
        	        $.each(result, function (i, item) {	        	        	
        	        	/* var parsedDate = new Date(parseInt(item.cdate));
        	        	var month = parsedDate.getMonth() + 1;
        	        	var day = parsedDate.getDate();
        	        	var year = parsedDate.getFullYear();
        	        	var date = day + "-" + month + "-" + year; */
        	        	var date = formatDate(new Date(getDateFromFormat(item.cdate,"yyyy-MM-dd")),"dd-MM-yyyy");
        	            trHTML += '<tr height="25px" style="color: black; vertical-align: middle;"><td><b>' + i + '.</b></td><td>'+ date +'</td><td>' + item.intime + '</td><td>' + item.outtime + '</td><td>' + item.intimestatus + '</td><td>' + item.outtimestatus + '</td><td>' + item.status + '</td><td>' + item.present + '</td></tr>';
        	        });
        	        $('#employeeTable').append(trHTML);	        	    
        		
        	}});
}
/* 
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
}*/

function ajaxCallForTableResults(month,year){	
	//var computeDays = "tableResults";
	//ajaxCallForComputingDays(month,year,"computeDays");
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
 
 
   