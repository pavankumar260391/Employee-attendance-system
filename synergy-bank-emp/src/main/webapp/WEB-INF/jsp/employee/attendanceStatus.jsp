 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="ff"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Attendance | LMS</title>    
     <%@include file="../admin/aimport.jsp" %>
     <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
 
 <meta charset="utf-8">
  <title>jQuery UI Datepicker - Default functionality</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script>
  $(function() {
    $( "#datepicker" ).datepicker();
  });
  </script>
  
 	<script src="${pageContext.request.contextPath}/js/admin/upAttd.js"></script>	
	
    <script type="text/javascript">
 
      $(document).ready(function(){ 
    	$('#datepicker').change(function(){
    		//var option = $(this).find('option:selected').val();
    		var date1 = $("#datepicker").val();
    		//ajaxCall(option);
    		alert("dfa");
    	});
    });  
 
 	
    
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
                    <h4>Attendence Status</h4>
                </div>
                <div class="span6">
                    <ul class="breadcrumb pull-right">
                        <li><a href="index.html">Home</a> <span class="divider">/</span></li>
                        <li><a href="#">${Pages}</a> <span class="divider">/</span></li>
                        <li class="active">Leave Balance</li>
                    </ul>
                </div>
            </div>
        </div>
    </section>
	


    <section id="portfolio" class="container main">  
    		
	<table id="tab" text-align="left" >
	<tbody>
	<tr>
	  <td> <p><input type="text" id="datepicker" class="form-control"></p>	</td>	
	</tr>
	</tbody>           
    </table>
        	<table  class="table table-bordered table-hover" id="employeeTable" width="100%">
								<tr height="30px"
									style="color:black; background-color: #F1F1F1; vertical-align: middle;"
									align="center">
									<td width="10px"><b>SNO.</b>
									</td>
									<td width="250px"><b>Att Date</b>
									</td>
									<td><b>In Time</b>
									</td>
									
									<td><b>Out Time</b>
									</td>
									<td><b>In Status</b>
									</td>
									<td><b>Out Status</b></td>
									<td><b>Status</b></td>
									<td><b>Present</b></td>
								</tr>
							
							 	<c:forEach var="item" items="${facultyAttendStatusVOlist}" varStatus="status">
									<tr height="25px" style="color: black; vertical-align: middle;">
										<td align="center"><b>${status.count}.</b>
										</td>
										<td>&nbsp;${item.cdate}
										<%-- <fmt:formatDate pattern="dd-MM-yyyy" value="${item.cdate}" /> --%>
										</td>
										<td>&nbsp;${item.intime}</td>
												<td>&nbsp;${item.outtime}</td>
										<td align="center">&nbsp;${item.intimestatus}</td>
										<td align="center">&nbsp;
										${item.outtimestatus}
										</td>
										<td align="center">
												${item.status}
										</td>
											<td align="center">
												${item.present}
										</td>
									</tr>
							</c:forEach>

							</table>
        
    </section>

<!--Footer-->
<footer class="footer navbar-fixed-bottom" id="footer">
    <div class="container">
        <div class="row-fluid">
            <div class="span5 cp">
                &copy; 2013 <a target="_blank" href="http://shapebootstrap.net/" title="Free Twitter Bootstrap WordPress Themes and HTML templates">ShapeBootstrap</a>. All Rights Reserved.
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
                <a id="gototop" class="gototop pull-right" href="#"><i class="icon-angle-up"></i></a>
            </div>
            <!--/Goto Top-->
        </div>
    </div>
</footer>
<!--/Footer-->

</body>
</html>
