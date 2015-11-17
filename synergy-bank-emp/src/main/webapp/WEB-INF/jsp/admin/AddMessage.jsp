<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="p"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
      <%@ include file="aimport.jsp" %>
</head>
<body>

<!--Header-->
      <%@ include file="aheader.jsp" %>
    <!-- /header -->

    <section class="title">
        <div class="container">
            <div class="row-fluid">
                <div class="span6">
                    <h3>Post a message</h3>
                </div>
                <!-- <div class="span6">
                    <ul class="breadcrumb pull-right">
                        <li><a href="index.html">Home</a> <span class="divider">/</span></li>
                        <li><a href="#">Pages</a> <span class="divider">/</span></li>
                        <li class="active">Portfolio</li>
                    </ul>
                </div> -->
            </div>
        </div>
    </section>

<br><br><br>
 <section id="portfolio" class="container main">  
 	<form action="PostMsg" method="post" commandName="msgCmd" enctype="multipart/form-data">
 	
 		Subject : <input type="text" length="50">
 		Category : <input type="text" length="50">
 		Message<br>
 					<input type="textarea">
 					
 	
 	</form>
 </section>

</body>
</html>