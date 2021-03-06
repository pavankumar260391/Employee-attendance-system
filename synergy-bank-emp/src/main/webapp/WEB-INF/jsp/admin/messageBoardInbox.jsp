<%@ taglib uri="http://www.springframework.org/tags/form" prefix="ff"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>jQuery TE | Downloaded Demo | v.1.4.0</title>
<%@ include file="aimport.jsp"%>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/demo.css">
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/jquery-te-1.4.0.css">
<script type="text/javascript"
	src="http://code.jquery.com/jquery.min.js" charset="utf-8"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-te-1.4.0.min.js"
	charset="utf-8"></script>
</head>
<body>
	<!--Header-->
	<%@ include file="aheader.jsp"%>
	<!-- /header -->

	<section id="portfolio" class="container main"
		style="background-color: #EDEDE5;">
		<ff:form method="post" enctype="multipart/form-data"
			commandName="messageBoardVO">
			<fieldset>
				<table class="table table-bordered table-hover" style="margin-bottom:-60px;margin-top:-10px">
					<thead>
						<tr>
						<th>Sno</th>
							<th>Subject</th>
							<th>Message</th>
							<th>Category</th>
							<th>Image</th>
							<th>Dom</th>
							<th>Delete</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach  items="${messageBoardList}" var="item" varStatus="tt">
						<tr>
							<td>${tt.count}</td>
							<td style="text-align: left;">${item.subject}</td>
							<td style="text-align: left;"><a href="#" style="color: darkblue;">View Message</a></td>
							<td style="text-align: left;">${item.category}</td>
							<td><img src="${pageContext.request.contextPath}/admin/findImageByMessageId?mid=${item.mid}" width="70" height="50" ></td>
							<td style="text-align: left;">${item.domessage}</td>
							<td style="text-align: left;">
							 		<input type="button" id="okButton" value="Delete"	class="btn btn-danger" style="height: 30px;">
							</td>
						</tr>
						</c:forEach>
					</tbody>
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
					Remember me
				</label>
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