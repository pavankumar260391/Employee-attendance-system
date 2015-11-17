
//Pk for Mark Lwp
	var cnt = 1;
	  $(function() {
	      $("#datepicker").datepicker({  maxDate: 0,
	    	  }).datepicker("setDate", new Date());
	    });
	  
function ajaxCallForMarkLwp(id,info){
	$.ajax({
		url : contextPath+"/admin/markLwpButtonClick",
		data : {"id":id, "description":info},
		type : 'GET',
		success : function(data){
			updateTable(data);
		}
	});
}

function ajaxCallForUnMarkLwp(id){
	$.ajax({
		url : contextPath+"/admin/unMarkLwpButtonClick",
		data : {"id":id},
		type : 'GET',
		success : function(data){
			if(data.equals("success"))
				alert("success");
			else
				alert("not successful");
		}
	});
}

function updateTable(data){
	if(data != "success"){
		$("#danger").text(data+"!").show();
	}
	else{
		$("#fine").text("LWP has been marked successfully for "+(cnt++)+" emplyee(s) !").show();
	}
}

function ajaxFilterByDepartment(date,department){
	$.ajax({
		url : contextPath+"/admin/getAllEmpByDept",
		data : {"department":department, "date":date},
		type : "GET",
		dataType : "json",
		success:function(data){
			$("#employeeTable tbody").remove();
            if(data){
                var len = data.length;
                var txt = "";
                var btnVal = "";
                if(len > 0){
                    for(var i=0;i<len;i++){
                    	if(data[i].color == 'lwpColor')
                    		btnVal="<td class=\"data4\"><form><input type=\"textarea\" rows=\"1\" name=\"textArea\" id=\"text\" disabled></form></td>"+"<td id=\"tdd\"><input type=\"button\" class = \"markBttn\" value=\"Unmark LWP\"></td>";
		                else
		                	btnVal="<td class=\"data5\"><form><input type=\"textarea\" rows=\"1\" name=\"textArea\" id=\"text\"></form></td>"+"<td id=\"tdd\"><input type=\"button\" class = \"markBttn\" value=\"Mark LWP\"></td>";
                    	txt += "<tr class = \""+data[i].color+"\"><td>"+(i+1)+"</td><td class=\"data0\" style=\"text-align:left;\">"+data[i].fid+"</td><td class=\"data1\" style=\"text-align:left;\">"+data[i].name+"</td><td class=\"data2\" style=\"text-align:left;\">"+data[i].designation+"</td><td class=\"data3\" style=\"text-align:left;\">"+data[i].department+"</td><td>"+"<img src= "+contextPath+"/admin/getImageById?Id="+data[i].fid+" height='24' width='24' alt=':-|'>"+"</td>"+btnVal+"</tr>";                     
                    }
                        if(txt != "")
                          $("#employeeTable").append(txt);
                }
            }    			
		}
	});
}
//End



function daysInMonth(month,year) {
    return new Date(year, month, 0).getDate();
}
/*
    $(function() {
      $( "#datepicker" ).datepicker({  maxDate: 0,
//    	  changeMonth: false,
//          changeYear: false,
//          showButtonPanel: true,
//          dateFormat: "yy-mm-dd",
          onClose: function(dateText, inst) { 
              var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
              var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
              //$(this).datepicker('setDate' ,new Date(year, month, day));
            //  $( ".datepicker" ).datepicker( "setDate", "dateFormat", "yy-mm-dd" );
              alert("wrong");
              ajaxSendDateDepartment(month,year,$( "#depSelect option:selected" ).text(), document.getElementById("employeeTable"));
              var totalDays = daysInMonth(month,year);              
          }
    	  }).datepicker("setDate", new Date());
    });*/
    
    function ajaxSendDateDepartment(month,year,department,table){
    	$.ajax({
    		url:contextPath+"/admin/empWorkingDaysByAjax",
    		data:{"month": month,"year":year,"department":department},
    		
    		type:"GET",
    		success:function(data){
    			$("#employeeTable tbody").remove();
                if(data){
                    var len = data.length;
                    var txt = "";
                    if(len > 0){
                        for(var i=0;i<len;i++){
                                txt += "<tr><td>"+data[i].id+"</td><td>"+data[i].name+"</td><td>"+data[i].designation+"</td><td>"+data[i].department+"</td><td>"+data[i].noleaves+"</td><td>"+"<img src= "+contextPath+"/admin/getImageById?Id="+data[i].id+" height='32' width='32' alt=':-|'>"+"</td><td>"+data[i].daysworked+"</td></tr>";
                        }                        
                        if(txt != ""){
                            $("#employeeTable").append(txt);
                        }
//                    	for(var i=0; i<len;i++){
////                    		var row = table.insertRow(2);
////                    		var cellid = row.insertCell(0); cellid.innerHTML = data[0].id;
//                    	}
                    }
                }    			
    		}
    	});
    }
    function show_image(src, width, height, alt) {
        var img = document.createElement("img");
        img.src = src;
        img.width = width;
        img.height = height;
        img.alt = alt;
        cell.appendChild(img);
    }

    function ajaxOnDepartmentChange(){
        var month = $("#ui-datepicker-div .ui-datepicker-month :selected").val();
        var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
//        alert(parseInt(month)+1);
//        alert(year);
//          alert($( "#depSelect option:selected" ).text());
        ajaxSendDateDepartment(month,year,$( "#depSelect option:selected" ).text(), document.getElementById("employeeTable"));

    }