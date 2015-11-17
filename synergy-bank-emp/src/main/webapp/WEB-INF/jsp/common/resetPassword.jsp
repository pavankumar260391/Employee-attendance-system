<%@ taglib uri="http://www.springframework.org/tags/form" prefix="ff"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Log-in | BAS</title>
      <%@ include file="aimport.jsp" %>
      <style>
      .error {
   color: red;
   font-size: 12px;
}
      </style>
      
 <script>
   $(document).ready(function(){
	   
	   $('#springForm').validate({ // initialize the plugin
	        rules: {
	        	"email": {
	                required: true,
	                minlength:2,
	            },
	            password: {
	                required: true,
	                
	            }
	        }
	    });
   });
   </script>
</head>
<body>

<!--Header-->
      <%@ include file="aheader.jsp" %>
    <!-- /header -->

     <section class="title">
        <div class="container">
            <div class="row-fluid">
                <div class="span6">
                    <h4>Admin Page</h4>
                </div>
                <div class="span6">
                 
                </div>
            </div>
        </div>
    </section>

 <section id="portfolio" class="container main">   

	<ff:form action="${pageContext.request.contextPath}/common/resetPassword"
					method="post"   id="springForm">
					<fieldset>
						<legend style="color: blue;" align="left">
							<font style="font-size: 14px;font-weight: bold;color:darkblue;">Reset Password Page</font>
						</legend>
						<br />
						
						<table width="630" border="0" align="left">
							
							<!-- <tr bgcolor="#66CCFF" style="color: #000000; font-weight: bold;">
								<td>Designation Id</td>
								<td><input  type="text" id="designationId" name="designationId"
										size="10" style="height: 15px"></input>
										  
										</td> -->
								<tr  style="color: #000000; font-weight: bold;">
								<td colspan="2"><span style="color: red;font-weight: bold;font-size: 14px;">${error}</span></td>
							</tr>		
										
										<tr  style="color: #000000; font-weight: bold;">
								<td>Email ID <span style="color: red;font-weight: bold;font-size: 16px;">*</span></td>
								<td>
								<input  type="text" id="email" class="error" name="email"
										size="10" style="height: 15px;width: 400px;"></input>
								<p id="designationError" style="display: none;">Please Enter the Email</p>		  
										</td>
										<td rowspan="3">
												  <img alt="" src="${pageContext.request.contextPath}/img/forgetPassword.jpg" style="margin-top:2px;"/>
										</td>
							</tr>
							 <tr bgcolor="white" style="color: #000000; font-weight: bold;">
								<td colspan="2">&nbsp;</td>
							</tr>
							 <tr bgcolor="white" style="color: #000000; font-weight: bold;">
								<td>
								<input type="submit" id="addDesignation" class="btn btn-info" size="30" value="Reset Password" ></input>
								</td>
								<td>
								&nbsp;
								</td>
							</tr>
						</table>
					</fieldset>
				</ff:form>
</section>
<br/><br/><br/><br/><br/>
<footer id="footer">
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

<!--  Login form -->
<div class="modal hide fade in" id="loginForm" aria-hidden="false">
    <div class="modal-header">
        <i class="icon-remove" data-dismiss="modal" aria-hidden="true"></i>
        <h4>Login Form</h4>
    </div>
    <!--Modal Body-->
    <div class="modal-body">
        <form class="form-inline" action="index.html" method="post" id="form-login">
            <input type="text" class="input-small" placeholder="Email">
            <input type="password" class="input-small" placeholder="Password">
            <label class="checkbox">
                <input type="checkbox"> Remember me
            </label>
            <button type="submit" class="btn btn-primary">Sign in</button>
        </form>
        <a href="">Forgot your password?</a>
    </div>
    <!--/Modal Body-->
</div>
<!--  /Login form -->
<%-- 
<script src="${pageContext.request.contextPath}/js/vendor/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/vendor/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
 --%>
</body>




</html>

