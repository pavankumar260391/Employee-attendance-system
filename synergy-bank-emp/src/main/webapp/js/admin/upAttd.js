resultRowId="";
globalDate1="";
globalDate2="";

$(function() {
    $( "#datepicker1" ).datepicker({maxDate:0,
    	onSelect:function(dateText){
  		  globalDate1 = dateText;
    	}
    }).datepicker("setDate", new Date());
  });

$(function() {
    $( "#datepicker2" ).datepicker({maxDate:0,
    	onSelect:function(dateText){
    	  globalDate2 = dateText;
    	}
    }).datepicker("setDate", new Date());
  });

function ajaxCallForDateFilter(eid,date1,date2){
	$.ajax({
		url : contextPath+"/admin/filter",
		data : {"empId":eid, "date1":date1, "date2":date2},
		dataType : "json",
		type : "GET",
		success : function(data){
			showFilteredData(data);
		}
	});
}

function showFilteredData(data){
		$('#chgTable').empty();
	    var trHTML ="";
	    var inStatusCount = 0;
		var outStatusCount = 0;
	    trHTML += '<tr height="30px" style="color: black; background-color: #F1F1F1; vertical-align: middle;" align="center"><td id="td"><b>SNO.</b></td><td id="td"><b>Date from</b></td><td id="td"><b>Date to</b></td><td id="td"><b>Total day(s)</b></td><td id="td"><b>Leave type</b></td><td id="td"><b>Leave category</b></td><td id="td"><b>Leave status</b></td><td id="td"><b>Purpose</b></td></tr>';			
	    
	    $.each(data, function (i, item) {
	    	var counter = 0;    	        	       	
	    	var leaveFrom = formatDate(new Date(getDateFromFormat(item.leaveFrom,"yyyy-MM-dd")),"dd-MM-yyyy");
	    	var leaveTo = formatDate(new Date(getDateFromFormat(item.leaveTo,"yyyy-MM-dd")),"dd-MM-yyyy");
	    	//var t1 = dateFormat(item.leaveFrom,"mm/dd/yyyy");
	    	//var d = item.leaveFrom;
	    	//var date = new Date(parseInt(d.substr(6)));
	    	//alert(date);
	    	//alert(item.leaveFrom+" "+item.leaveTo);
	    	//var x = [ {"id":1,"start":"\/Date(item.leaveFrom)\/"}, {"id":2,"start":"\/Date(item.leaveTo)\/"} ];
	    	//var myDate = new Date(x[0].start.match(/\d+/)[0] * 1);
	       //alert(myDate);
	    	//trHTML += '<tr height="25px" id="resultDataRow'+i+'" style="color: black;"><td class="data'+counter+'"><b>' + (counter+1) + '</b></td><td class="data'+(counter+1)+'">' + leaveFrom + '</td><td class="data'+(counter+2)+'">' + leaveTo + '</td><td class="data'+(counter+3)+'">' + item.totalDays + '</td><td class="data'+(counter+4)+'">' + item.leaveType + '</td><td class="data'+(counter+5)+'">' + item.leaveCategory + '</td><td class="data'+(counter+8)+'">' + item.purpose + '</td></tr>';
	       	trHTML += '<tr height="25px" style="color: black;"><td id="td"><b>' + (counter+1) + '</b></td><td text-align="left">' + leaveFrom + '</td><td id="td">' + leaveTo + '</td><td id="td">' + item.totalDays + '</td><td id="td">' + item.leaveType + '</td><td id="td">' + item.leaveCategory + '</td><td id="td">' + item.lstatus + '</td><td id="td">' + item.purpose + '</td></tr>';


	       });
	    
	    $('#chgTable').append(trHTML);
	}


    $(function() {
      $("#datepicker").datepicker({  maxDate: 0,
    	  onSelect: function(dateText)    	  
		    { 	
    		  var department = $('#depSelect').find('option:selected').val();
    		  if(department=="All"){
		        	ajaxCallByDate(dateText);
    		  }else{    			     		 	
    		    ajaxCallForPastDateAndDep(dateText,department)
    		  }
		    }
    	  }).datepicker("setDate", new Date());
    });
    
    
    
    function popupOpen(rowId){	
    	resultRowId = $("#"+rowId).closest('tr').attr("id");
    	$("#empId").val($("#"+resultRowId).children("td.data0").text());
    	$("#empName").val($("#"+resultRowId).children("td.data1").text());
    	$("#attdDate").val($("#"+resultRowId).children("td.data2").text());
    	$("#inTime").val($("#"+resultRowId).children("td.data3").text());
    	$("#outTime").val($("#"+resultRowId).children("td.data4").text());    	
    	$("#department").val($("#"+resultRowId).children("td.data5").text());
    	$('#inStatus option[value='+$("#"+resultRowId).children("td.data6").text()+']').prop('selected', true);	
    	$('#outStatus option[value='+$("#"+resultRowId).children("td.data7").text()+']').prop('selected', true);
    	$('#status option[value='+$("#"+resultRowId).children("td.data8").text()+']').prop('selected', true);	
    	$('#present option[value='+$("#"+resultRowId).children("td.data9").text()+']').prop('selected', true);	
    	$("#dialog-form").dialog("open");
    }
    
    /**
     * 
     * PK
     */
    
    function updateAjaxCall(idArray){
		$.ajax({
    		url:contextPath+"/admin/adminAttendanceTodays",
    		data:{"checkedIds": idArray},
    		dataType:"json",
    		type:"GET",
    		success:function(data){
    			showUpdatedData(data);
    		}
    	});
	}

function showUpdatedData(data){
	$('#employeeTable').empty();
    var trHTML ="";
    var inStatusCount = 0;
	var outStatusCount = 0;
    trHTML += '<tr height="30px" style="color: black; background-color: #F1F1F1;" align="center"><td width="50px"><b>Emp ID</b></td><td><b>Name</b></td><td width="200px"><b>Att Date</b></td><td><b>In Time</b></td><td><b>Out Time</b></td><td><b>Department</b></td><td><b>In Status</b></td><td><b>Out Status</b></td><td><b>Status</b></td><td><b>Present</b></td><td></td></tr>';			
    
    $.each(data, function (i, item) {
    	var counter = 0;
    	var instatus = $.trim(item.intimestatus);
    	var outstatus = $.trim(item.outtimestatus);    	        	       	
    	var date = formatDate(new Date(getDateFromFormat(item.cdate,"yyyy-MM-dd")),"dd-MM-yyyy");
    	
    	var ot = "";
    	if(item.outtime == null) item.outtime = ot; 
    	var os = "";
    	if(item.outstatus == null) item.outstatus = os; 
        
    	trHTML += '<tr height="25px" id="resultDataRow'+i+'" style="color: black;"><td class="data'+counter+'"><b>' + item.fid + '</b></td><td class="data'+(counter+1)+'">' + item.name+' '+item.fatherName + '</td><td class="data'+(counter+2)+'">' + date + '</td><td class="data'+(counter+3)+'">' + item.intime + '</td><td class="data'+(counter+4)+'">' + item.outtime + '</td><td class="data'+(counter+5)+'">' + item.department + '</td>'+instatus+''+outstatus+'<td class="data'+(counter+8)+'">' + item.status + '</td><td class="data'+(counter+9)+'">' + item.present + '</td><td class="data'+(counter+10)+'"><input type="checkbox" name="check" id="btnId${status.count}" class="checkbox" checked="true"></td></tr>';
    });
    
    $('#employeeTable').append(trHTML);
}


function ajaxCallForDep(department){
	    	$.ajax({
	    		url:contextPath+"/admin/adminAttendanceTodayByDep",
	    		data:{"depName":department},
	    		dataType:"json",
	    		type:"GET",
	    		success:function(data){
	    			showUpdatedData(data)
	    		}
	    	});
	    }


    //End
    
    
  function tableProcessing(data){
    	
    	$('#employeeTable').empty();
    	
        var trHTML ="";
        var inStatusCount = 0;
    	var outStatusCount = 0;
        trHTML += '<tr height="30px" style="color: black; background-color: #F1F1F1;" align="center"><td width="50px"><b>Emp ID</b></td><td><b>Name</b></td><td width="200px"><b>Att Date</b></td><td><b>In Time</b></td><td><b>Out Time</b></td><td><b>Department</b></td><td><b>In Status</b></td><td><b>Out Status</b></td><td><b>Status</b></td><td><b>Present</b></td><td></td></tr>';			
        
        $.each(data, function (i, item) {
        	var counter = 0;
        	var instatus = $.trim(item.intimestatus);
        	var outstatus = $.trim(item.outtimestatus);    	        	
/*         	var parsedDate = new Date(parseInt(item.cdate));
        	var month = parsedDate.getMonth() + 1;
        	var day = parsedDate.getDate();
        	var year = parsedDate.getFullYear();
        	var date = day + "-" + month + "-" + year;  */        	
        	var date = formatDate(new Date(getDateFromFormat(item.cdate,"yyyy-MM-dd")),"dd-MM-yyyy");
        	
        	if(instatus == "Late"){
        		inStatusCount += 1;
        		instatus = '<td class="data'+(counter+6)+'" style="background-color: #F2DEDE;">' + item.intimestatus +'</td>';    		
        	}else{
        		instatus ='<td class="data'+(counter+6)+'">' + item.intimestatus + '</td>';
        	}
        	
        	if(outstatus == "Early"){
        		outStatusCount += 1;
        		outstatus= '<td class="data'+(counter+7)+'" style="background-color: #F2DEDE;">' + item.outtimestatus + '</td>';
        	}else{
        		outstatus = '<td class="data'+(counter+7)+'">' + item.outtimestatus + '</td>';
        	}
        	
            trHTML += '<tr height="25px" id="resultDataRow'+i+'" style="color: black;"><td class="data'+counter+'"><b>' + item.fid + '</b></td><td class="data'+(counter+1)+'">' + item.name+' '+item.fatherName + '</td><td class="data'+(counter+2)+'">' + date + '</td><td class="data'+(counter+3)+'">' + item.intime + '</td><td class="data'+(counter+4)+'">' + item.outtime + '</td><td class="data'+(counter+5)+'">' + item.department + '</td>'+instatus+''+outstatus+'<td class="data'+(counter+8)+'">' + item.status + '</td><td class="data'+(counter+9)+'">' + item.present + '</td><td class="data'+(counter+10)+'"><button id="btnId'+i+'" class="btn btn-primary" onClick="popupOpen(this.id)"><span class="icon-pencil" aria-hidden="true"></span>Edit</button></td></tr>';
        });
        
        $('#employeeTable').append(trHTML);
    }

 
    function ajaxCallByDate(dateObject){
    	$.ajax({
    		url:contextPath+"/admin/adminAttendanceByPastDate",
    		data:{"pastDate": dateObject},
    		dataType:"json",
    		type:"GET",
    		success:function(data){
    			tableProcessing(data)
    		}
    	});
    }
   
    function ajaxCallForPastDateAndDep(dateObject,department){
    	$.ajax({
    		url:contextPath+"/admin/adminAttendanceByPastDateAndDep",
    		data:{"pastDate": dateObject, "depName":department},
    		dataType:"json",
    		type:"GET",
    		success:function(data){
    			tableProcessing(data)
    		}
    	});
    }
    
    function deleteAttendus(){    	
    	var sendMonth = $('#attdDate').val();
    	var fid = $('#empId').val();
    	$.ajax({    				
    		url:contextPath+'/admin/deleteAttendus',	
    		data:{"fid":fid,"attndDate": sendMonth,"employeeName":null},    		
    		type:"get",
    		success: function(data){    			
        		$("#dialog-form").dialog('close');    		
        		$('#updation-success').show();
        		$('#updation-success').text(data);
        		$(function() {
        		    $( "#dialog-success-message" ).dialog({
        		      /* open: function(event, ui) { $(".ui-dialog-titlebar-close").hide(); }, */
        		      modal: true,
        		      buttons: {
        		        Ok: function() {
        		          $( this ).dialog( "close" );
        		        }
        		      }
        		    });
        		  });        
        		$('#'+resultRowId).remove();
        		
    		}    				
    	});    
    }
    
    function updateEmployee(){    	
    	var myForm = $("#employeeForm").serialize();
    	ajaxCallForUpdateEmployee(myForm,"updateEmployee");
    }
    
    function ajaxCallForUpdateEmployee(formObject,controller){
/*     	var outStatus="";
    	var inStatus="";
    	var Status="";
    	var present="";
    	var inTime="";
    	var outTime=""; */
    	$.ajax({
        	url:contextPath+"/admin/"+controller,	        	
        	type: "post",        	
        	data:formObject,        	       	
        	success: function(data){       
/*         		outStatus = $('#outStatus').find('option:selected').val();
        		inStatus = $('#inStatus').find('option:selected').val();
        		status = $('#status').find('option:selected').val();
        		present = $('#present').find('option:selected').val();
        		inTime = $('#inTime').text();
        		outTime = $('#outTime').text();
        		alert(present+" "+status+" "+inStatus+" "+outStatus+" "+inTime+" "+ outTime); */
        		$("#dialog-form").dialog('close');    		
        		$('#updation-success').show();
        		$('#updation-success').text(data);
        		$(function() {
        		    $( "#dialog-success-message" ).dialog({
        		      /* open: function(event, ui) { $(".ui-dialog-titlebar-close").hide(); }, */
        		      modal: true,
        		      buttons: {
        		        Ok: function() {
        		          $( this ).dialog( "close" );
        		        }
        		      }
        		    });
        		  });
        	/* 	$('#'+resultRowId).children('td.data3').text(inTime);
        		$('#'+resultRowId).children('td.data4').text(outTime);
        		$('#'+resultRowId).children('td.data8').text(status);
        		$('#'+resultRowId).children('td.data9').text(present);
        		$('#'+resultRowId).children('td.data6').text(inStatus);
        		if(inStatus=="Late"){        			        			
        			$('#'+resultRowId).children('td.data6').attr('style',"background-color: #F2DEDE;");
        		}
        		$('#'+resultRowId).children('td.data7').text(outStatus);
        		if(outStatus=="Early"){        			        			
        			$('#'+resultRowId).children('td.data7').attr('style',"background-color: #F2DEDE;");
        		}
        		 */
        		 var department = $('#depSelect').find('option:selected').val();
            	var selectDate = $("#datepicker").val();
            	ajaxCallForPastDateAndDep(selectDate,department); 
        	} });  
    }