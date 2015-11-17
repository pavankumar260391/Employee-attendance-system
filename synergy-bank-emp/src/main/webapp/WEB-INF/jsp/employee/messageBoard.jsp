<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="ff"%>
<!-- PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" 
xmlns="http://www.w3.org/1999/xhtml"-->
<%@ page isELIgnored="false"%>
<!DOCTYPE html >
<html class="no-js">
<head>
<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title>Log-in | BAS</title> <%@ include file="eimport.jsp"%>
		<style>
.error {
	color: red;
	font-size: 12px;
}
</style>

		<script type="text/javascript">
			$(document).ready(function() {
				// this initializes the dialog (and uses some common options)
				$("#dialog-form").dialog({
					resizable : false,
					autoOpen : false,
					height : 500,
					width : 500,
					modal : true,
					show : {
						effect : "blind",
						duration : 500
					},
					hide : {
						effect : "explode",
						duration : 500
					}
				});
			});

			function showManagers() {
				$("#managerSelect").show();
				$("#removeButton").show();
				$("#okButton").show();
			}

			function removeManagers() {
				$("#managerSelect").hide();
				$("#removeButton").hide();
				$("#okButton").hide();
			}
			
			function confirmPopUp() {
				$("#dialog-form").html("Confirm Dialog Box");
				$("#dialog-form").dialog('open');
				return false;
			}
		</script>
</head>
<body>

	<!--Header-->
	<%@ include file="eheader.jsp"%>
	<!-- /header -->

	<section class="title">
	<div class="container">
		<div class="row-fluid">
			<div class="span6">
				<h4>Message Board</h4>
			</div>
			<div class="span6"></div>
		</div>
	</div>
	</section>

	<section id="portfolio" class="container main">
	
		<fieldset>

			<table width="900" border="0" align="left">

				<tr style="color: #000000; font-weight: bold;">
					<td colspan="2"><span
						style="color: red; font-weight: bold; font-size: 14px;">${error}</span></td>
				</tr>

				<tr style="color: #000000; font-weight: bold;">
					<td align="left" style="color: red; font-weight: bold; font-size: 16px;">Subject : ${messageBoardVO.subject} </td>
					<td align="right">Total Messages :${messageBoardVO.totalMessage} <img src="${pageContext.request.contextPath}/img/message.png"/></td>
				</tr>
				<tr style="color: #000000; font-weight: bold;">
					<td >&nbsp;</td>
					<td align="right">&nbsp;
					
					<c:if test="${messageBoardVO.mid==1}">
					<a href="javascript:void(0)" style="color:black;">
						<img src="${pageContext.request.contextPath}/img/left.png"/>
					</a>
					</c:if>
					<c:if test="${messageBoardVO.mid!=1}">
					<a href="${pageContext.request.contextPath}/employee/previousMessageBoard?mid=${messageBoardVO.mid-1}">
					<img src="${pageContext.request.contextPath}/img/left.png"/>
					</a>
					</c:if>
					&nbsp;&nbsp;&nbsp;
					
					<c:if test="${messageBoardVO.mid==messageBoardVO.totalMessage}">
					<a href="javascript:void(0)" style="color:black;">
							<img src="${pageContext.request.contextPath}/img/right.png"/>
					</a>
					</c:if>
					<c:if test="${messageBoardVO.mid<messageBoardVO.totalMessage}">
					<a href="${pageContext.request.contextPath}/employee/nextMessageBoard?mid=${messageBoardVO.mid+1}">
					<img src="${pageContext.request.contextPath}/img/right.png"/>
					</a>
					</c:if>
					</td>
					
					</tr>
					
				
				<tr style="color: #000000; font-weight: bold;">
					 <td colspan="2">
					<div  style="width: 100%;height:300px;border-style: solid; border-color: black;border-width: 1px;">&nbsp;&nbsp;${messageBoardVO.message}</div>
					</td>
				</tr>
				
			</table>

		</fieldset>
	<div id="dialog-form" title="Search Employees" class="container">

	</div>

	</section>
	<footer id="footer">
	<div class="container">
		<div class="row-fluid">
			<div class="span5 cp">
				&copy; 2013 <a target="_blank" href="http://shapebootstrap.net/"
					title="Free Twitter Bootstrap WordPress Themes and HTML templates">ShapeBootstrap</a>.
				All Rights Reserved.
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
				<a id="gototop" class="gototop pull-right" href="#"><i
					class="icon-angle-up"></i></a>
			</div>
			<!--/Goto Top-->
		</div>
	</div>
	</footer>
	<!--/Footer-->

	<!--  Login form -->
	<div class="modal hide fade in" id="loginForm" aria-hidden="false">
		<div class="modal-header">
			<i class="icon-remove" data-dismiss="modal" aria-hidden="true"></i>
			<h4>Login Form</h4>
		</div>
		<!--Modal Body-->
		<div class="modal-body">
			<form class="form-inline" action="index.html" method="post"
				id="form-login">
				<input type="text" class="input-small" placeholder="Email">
					<input type="password" class="input-small" placeholder="Password">
						<label class="checkbox"> <input type="checkbox">
								Remember me </label>
						<button type="submit" class="btn btn-primary">Sign in</button>
			</form>
			<a href="">Forgot your password?</a>
		</div>
		<!--/Modal Body-->
	</div>
	<!--  /Login form -->
	<script
		src="${pageContext.request.contextPath}/js/vendor/jquery-1.9.1.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/vendor/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>

</html>

