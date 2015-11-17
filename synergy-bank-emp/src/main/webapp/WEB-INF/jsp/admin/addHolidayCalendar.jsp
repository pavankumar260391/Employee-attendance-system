 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="ff"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Attendance | LMS</title>
     <%@include file="aimport.jsp" %>
</head>

<body>

    <!--Header-->
     <%@include file="aheader.jsp" %>
    <!-- /header -->

    <section class="title">
        <div class="container">
            <div class="row-fluid">
                <div class="span6">
                    <h3>Holiday Calendar</h3>
                </div>
                <div class="span6">
                    <ul class="breadcrumb pull-right">
                        <li><a href="index.html">Home</a> <span class="divider">/</span></li>
                        <li><a href="#">Pages</a> <span class="divider">/</span></li>
                        <li class="active">Holiday Calendar</li>
                    </ul>
                </div>
            </div>
        </div>
    </section>
    <!-- / .title -->     

    <section id="portfolio" class="container main">    
    
    	  <span style="float: right;">
         
          </span>
          	<ff:form action="${pageContext.request.contextPath}/admin/holidayEntry"	method="post" commandName="holidayEntryForm" id="holidayForm" enctype="multipart/form-data">
        		<table border="0"  style="background-repeat:no-repeat;width: 100%;background-image: url('${pageContext.request.contextPath}/img/calendar_icon.jpg');"  class="table">
      			 	 <tr height="25px">
					     <td  style="width:30%;text-align: right;">
					       <b>Date Of Holiday <font color="red">*</font>&nbsp; :</b> 
					     </td>
					     <td colspan="1" style="text-align: left">
					        <ff:input  type="text"  path="holidayDate"  style="width: 16%"  class="datepicker"/>
					     </td>
					      <td colspan="1" align="left" valign="top" rowspan="9">
					    <!--   <img src="${pageContext.request.contextPath}/images/userReg.png"/> -->
					     </td>
					     
					   </tr>
					   
					    <tr height="25px" >
					     <td colspan="1" style="width: 30%;text-align: right;vertical-align:middle;">
					       <b>Working&nbsp; :</b> 
					     </td>
					     <td style="text-align: left;">
					         <select name="working" style="width: 20%" class="form-control">
					       			<option>Yes</option>  
					       			<option selected="selected">No</option>
					         </select>
					           <b style="margin-left:64px;">Holiday <font color="red">*</font>&nbsp; :</b> 
					         <select name="holiday" style="width: 20%" class="form-control">
					       			<option>Yes</option>  
					       			<option>No</option>
					         </select>
					     </td>
					   </tr>
					   
					     <tr height="25px" >
					     <td colspan="1"  style="width: 30%;text-align:right;">
					       <b>Weekend&nbsp; :</b> 
					     </td>
					     <td colspan="1" style="text-align: left;">
					       <select name="weekend" style="width: 20%" class="form-control">
					       			<option>Yes</option>  
					       			<option>No</option>
					         </select>
					            <b style="margin-left:40px;">Holiday Type&nbsp; :</b> 
					            <select name="holidayType" style="width: 56%" class="form-control">
					       			<option>National Holiday</option>  
					       			<option>Regional Holiday</option>
					       			<option>Not a Public Holiday</option>
					         </select>
					     </td>
					   </tr>
					   
					    <tr height="25px" >
					     <td colspan="1"  style="width: 10%;text-align:right;">
					       <b>Description <font color="red">*</font>&nbsp; :</b> 
					     </td>
					     <td colspan="1" style="text-align: left;">
					         	<textarea rows="2" cols="200"  class="form-control" name="description" style="width:400px;background-color: #FDFD99;"></textarea>
					     </td>
					   </tr>
					   
					     <tr height="25px" >
					     <td colspan="1"  style="width: 10%;text-align:right;">
					       <b>Comment <font color="red">*</font>&nbsp; :</b> 
					     </td>
					     <td colspan="1" style="text-align: left;">
					         	<textarea rows="2" cols="200"  class="form-control" name="comment" style="width:400px;background-color: #FDFD99;"></textarea>
					     </td>
					   </tr>
					   
					    <tr height="25px" >
					     <td colspan="1"  style="width: 30%;text-align:right;">
					       <b>Image&nbsp; :</b> 
					     </td>
					     <td colspan="1" style="text-align: left;">
					       		<input type="file" name="image"/>
					     </td>
					   </tr>
					    <tr height="25px"  >
					     <td colspan="2" style="text-align: right;">
					        <input type="submit" value="Add Holiday"  class="btn btn-info" id="userRegistrationBt"/>
					     </td>
					   </tr>
					 </table>
       </ff:form>
    </section>

	<%@include file="../common/footer.jsp" %>
<%-- <script src="${pageContext.request.contextPath}/js/vendor/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/vendor/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
<script src="${pageContext.request.contextPath}/js/BeatPicker.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script> --%>

<script type="text/javascript">
	$(document).ready(function() {
		$('.datepicker').datepicker();
		$("input[type='text'][id='doh']").click(function(){
				$("#myAlert").alert();
		});
	});
</script>

</body>
</html>
