<%@ taglib uri="http://www.springframework.org/tags/form" prefix="ff"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>jQuery TE | Downloaded Demo | v.1.4.0</title>
<%@ include file="aimport.jsp"%> 
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/demo.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-te-1.4.0.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-te-1.4.0.min.js" charset="utf-8"></script>
</head>
<body>
<!--Header-->
	<%@ include file="aheader.jsp"%>
	<!-- /header -->
	
	<section id="portfolio" class="container main" style="background-color:#EDEDE5;">
		<ff:form  method="post" enctype="multipart/form-data" commandName="messageBoardVO">
		<fieldset>
			<table width="930"  border="0" align="left" style="margin-left:20px;">
				<tr style="color: #000000; font-weight: bold;">
					<td colspan="2"><span
						style="color: red; font-weight: bold; font-size: 14px;">${error}</span></td>
				</tr>
				<tr style="color: #000000;">
					<td align="left" style="color: black; font-size: 14px;" colspan="2">Subject : <input type="text"  style="width:335px;" name="subject"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Category : <input type="text"  style="width:335px;" name="category"/></td>
				</tr>
				<tr style="color: #000000; font-weight: bold;">
					 <td colspan="2">
					 <textarea name="message" class="jqte-test"></textarea>
					</td>
				</tr>
				
				<tr style="color: #000000; font-weight: bold;">
					 <td colspan="1">
					 <input type="file"  value="Post Message"	 name="image" style="background-color: #EEEEEE">
					</td>
					
					<td colspan="1" align="right">
					 <input type="submit" id="okButton" value="Post Message"
						class="btn btn-primary">
					</td>
				</tr>
			</table>
		</fieldset>
		</ff:form>
	</section>
<!------------------------------------------------------------ jQUERY TEXT EDITOR ------------------------------------------------------------>
<script>
	$('.jqte-test').jqte();
	
	// settings of status
	var jqteStatus = true;
	$(".status").click(function()
	{
		jqteStatus = jqteStatus ? false : true;
		$('.jqte-test').jqte({"status" : jqteStatus})
	});
</script>

<!------------------------------------------------------------ jQUERY TEXT EDITOR ------------------------------------------------------------>
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
	<script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>

</html>