<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="ff"%>
<!-- PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd" 
xmlns="http://www.w3.org/1999/xhtml"-->
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>jQuery TE | Downloaded Demo | v.1.4.0</title>

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/demo.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-te-1.4.0.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-te-1.4.0.min.js" charset="utf-8"></script>
</head>
<body>
<h1>jQuery TE</h1>

<div class="navigation">
<a href="http://jqueryte.com" target="_blank">Home</a>
<a href="http://jqueryte.com/demos" target="_blank">Demos</a>
<a href="http://jqueryte.com/documentation" target="_blank">Documentation</a>
<a href="http://jqueryte.com/comments" target="_blank">Comments</a>
<a href="http://jqueryte.com/about" target="_blank">About</a>
<a href="http://jqueryte.com/license" target="_blank">License</a>
</div>

<h2>Demo | v.1.4.0</h2>

<!------------------------------------------------------------ Toggle jQTE Button ------------------------------------------------------------>
<button class="status">Toggle jQTE</button>

<!------------------------------------------------------------ jQUERY TEXT EDITOR ------------------------------------------------------------>

<textarea name="textarea" class="jqte-test">
<b>My contents are from <u><span style="color:rgb(0, 148, 133);">TEXTAREA</span></u></b>
</textarea>

<input name="input" type="text" value="<b>My contents are from <u><span style=&quot;color:rgb(0, 148, 133);&quot;>INPUT</span></u></b>" class="jqte-test">

<span name="span" class="jqte-test"><b>My contents are from <u><span style="color:rgb(0, 148, 133);">SPAN</span></u></b></span>

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


<hr>

<div class="footer">
<b>Please <a href="https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=U49X5YEFRPMSL" target="_blank">donate</a> us or <a href="http://jqueryte.com" target="_blank">click on advertisements</a> to improve more than.</b>
</div>

<p>Thanks for using!</p>

</body>
</html>