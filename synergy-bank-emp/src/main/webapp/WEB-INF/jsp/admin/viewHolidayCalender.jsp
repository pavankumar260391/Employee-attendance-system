<%@ taglib uri="http://www.springframework.org/tags/form" prefix="ff"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

 
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>View Holiday Calender | BAS</title>
      <%@ include file="aimport.jsp" %>
      <%@ include file="aheader.jsp" %>
      <style>
      
      .container .twelve.columns {
  width: 700px;
}
.container .column, .container .columns {
  float: left;
  display: inline;
  margin-left: 10px;
  margin-right: 10px;
}
table.list-table th:last-child {
  border-right: 1px solid #ddd;
}
table.list-table th {
  border: 1px solid #dddddd;
  background-color: #F7F7F7;
  background-image: -moz-linear-gradient(center bottom,#F7F7F7 0%,#FFFFFF 100%);
  text-align: left;
  padding: 5px 5px;
  color: #404040;
  vertical-align: top;
  font-size: 14px;
  font-weight: bold;
}
.holiday {
  background-color: #E9F7FE;
  border: 1px solid #B6D7E8;
}
.publicholiday {
  background-color: #FBFADD;
}

element.style {
  white-space: nowrap;
}
table.list-table td {
  padding: 5px 5px;
  border: #e0e0e0 1px solid;
}
table.list-table td:last-child {
  border-right: 1px solid #ddd;
}
table.list-table td {
  padding: 5px 5px;
  border: #e0e0e0 1px solid;
}
@media only screen and (max-width: 479px)
th.remarks, td.remarks {
  display: none;
}
th.remarks, td.remarks {
  display: table-cell;
}
.regional {
  background-color: #EBF6E0;
  border: 1px solid #B3DC82;
}
.headline {
  background: url(../images/headline-bg.png) 0 50% repeat-x;
  display: block;
  margin: 30px 0 8px 0;
}
//.demo-6 .main h4
.heading{
	margin: 1em 0 0.5em 0;
	color: #343434;
	font-size: 18px;
	line-height: 20px;
	font-weight: bold;
	font-family: 'Josefin Sans', sans-serif;
}	
      
      </style>

 
 
      
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  

<section class="title">
        <div class="container">
            <div class="row-fluid">
                 <div class="span6">
                    <h3>Holiday Calender</h3>  
                </div>
                <div class="span6">
                    <ul class="breadcrumb pull-right">
                        <li><a href="index.html">Home</a> <span class="divider">/</span></li>
                        <li><a href="#">Action</a> <span class="divider">/</span></li>
                        <li class="active">View Holiday Calender</li>
                    </ul>
                </div> 
            </div>
        </div>
    </section>
    

</head>
<body>
<section class="container main">
<h4 align="left" style="margin-top:-30px;">Upcoming Holidays</h4>
<table style="margin-left:0px;">
  <tr>
  <c:forEach items="${listOfHolidays}" var="eachObject">
  <td style="margin-right: 30px;">
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <img  alt="" src="${pageContext.request.contextPath}/admin/showImage?imageId=<fmt:formatDate pattern='dd-MM-yyyy'	value='${eachObject.holidayDate}'/>" />
  <p style="color:black;font-weight: bold;'">
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <fmt:formatDate pattern="dd-MM-yyyy"
							value="${eachObject.holidayDate}" />
	
						 &nbsp;&nbsp; -&nbsp;&nbsp;${eachObject.description}
    	</p>
  </td>
 </c:forEach>
  </tr>
  </table>
<h4>List of Company Holidays</h4>
  <table class="list-table" style="width: 90%;">
  <tbody><tr>
  <th>Day</th>
  <th>Date</th>
  <th>Holiday</th>
  <th class="remarks">Comments</th>
  </tr>
 
 <c:forEach items="${holidayEntryList}"  var="item"> 
  <tr class="${item.color}">
  <td>
  <fmt:formatDate pattern="EE"  type="both"  dateStyle="full" timeStyle="short" value="${item.holidayDate}" />
  </td>
	<td style="white-space: nowrap">
	  <span class="ad_head_728">
	  <fmt:formatDate type="both" 
            dateStyle="full" timeStyle="short"
            value="${item.holidayDate}" />
	  </span>
	   <span class="mobile_ad"></span>
  </td>
  
  <td>
  <a href="/countries/global/new_years_day.php" rel="tooltip" title="">${item.description}</a>  </td>
  <td class="remarks">
        ${item.comment}
    </td>
  </tr>
    </c:forEach>

  <tr class="holiday">
    <td>Monday</td>
	<td style="white-space: nowrap">
  <span class="ad_head_728">January 19</span>
   <span class="mobile_ad">Jan 19</span>
  </td>
  
  <td>
  <a href="/countries/usa/mlk.php" rel="tooltip" title="">Martin Luther King Day </a>  </td>
  <td class="remarks">
  Third Monday in January  </td></tr>
    <tr class="holiday"><td>Friday</td>

<td style="white-space: nowrap">
  <span class="ad_head_728">December 25</span>
   <span class="mobile_ad">Dec 25</span>
  </td>
  <td>
  <a href="/countries/global/christmas_day.php" rel="tooltip" title="">Christmas Day </a>  </td>
  <td class="remarks">
    </td></tr>
        
  </tbody></table>
  
<div class="headline">
	  <h3>Key</h3>
	  </div>
<table width="160" class="info-table">
      <tbody><tr class="holiday">
        <td>National Holiday</td>
      </tr>
            <tr class="regional">
        <td>Regional Holiday</td>
      </tr>
            <tr class="publicholiday">
        <td>Not a Public Holiday</td>
      </tr>
           </tbody></table>
           </section>
	 <%@include file="../common/footer.jsp" %>

</body>
</html>