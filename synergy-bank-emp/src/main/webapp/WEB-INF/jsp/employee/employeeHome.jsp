<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Home | Nova</title>
   <%@include file="eimport.jsp" %>
</head>

<body>

    <!--Header-->
     <%@include file="eheader.jsp" %>
    <!-- /header -->

    <section class="title">
        <div class="container">
            <div class="row-fluid">
                <div class="span6">
                    <h4>Employee Home</h4>
                </div>
                <div class="span6">
                 
                </div>
            </div>
        </div>
    </section>
    <!-- / .title -->     

    <section id="portfolio" class="container main">    
        <ul class="gallery col-4">
            <!--Item 1-->
            <li>
                <div class="preview">
                    <img alt=" " src="${pageContext.request.contextPath}/images/portfolio/thumb/attendace.jpg">
                    <div class="overlay">
                    </div>
                    <div class="links">
                        <a data-toggle="modal" href="#modal-1"><i class="icon-eye-open"></i></a><a href="#"><i class="icon-link"></i></a>                                
                    </div>
                </div>
                <div class="desc">
                    <h5>View Attendance</h5>
                </div>
                <div id="modal-1" class="modal hide fade">
                    <a class="close-modal" href="javascript:;" data-dismiss="modal" aria-hidden="true"><i class="icon-remove"></i></a>
                    <div class="modal-body">
                        <img src="${pageContext.request.contextPath}/images/portfolio/full/attendace.jpg" alt=" " width="100%" style="max-height:400px">
                    </div>
                </div>                 
            </li>
            <!--/Item 1--> 

            <!--Item 2-->
            <li>
                <div class="preview">
                    <img alt=" " src="${pageContext.request.contextPath}/images/portfolio/thumb/leave.png">
                    <div class="overlay">
                    </div>
                    <div class="links">
                        <a data-toggle="modal" href="#modal-1"><i class="icon-eye-open"></i></a><a href="#"><i class="icon-link"></i></a>                                
                    </div>
                </div>
                <div class="desc">
                    <h5>Apply for leave</h5>
                </div>
                <div id="modal-1" class="modal hide fade">
                    <a class="close-modal" href="javascript:;" data-dismiss="modal" aria-hidden="true"><i class="icon-remove"></i></a>
                    <div class="modal-body">
                        <img src="${pageContext.request.contextPath}/images/portfolio/full/leave.png" alt=" " width="100%" style="max-height:400px">
                    </div>
                </div>                 
            </li>
            <!--/Item 2-->

            <!--Item 3-->
            <li>
                <div class="preview">
                    <img alt=" " src="${pageContext.request.contextPath}/images/portfolio/thumb/leaveBalance.jpg">
                    <div class="overlay">
                    </div>
                    <div class="links">
                        <a data-toggle="modal" href="#modal-3"><i class="icon-eye-open"></i></a><a href="#"><i class="icon-link"></i></a>                                
                    </div>
                </div>
                <div class="desc">
                    <h5>Leave Balance</h5>
                </div>
                <div id="modal-3" class="modal hide fade">
                    <a class="close-modal" href="javascript:;" data-dismiss="modal" aria-hidden="true"><i class="icon-remove"></i></a>
                    <div class="modal-body">
                        <img src="${pageContext.request.contextPath}/images/portfolio/full/leaveBalance.jpg" alt=" " width="100%" style="max-height:400px">
                    </div>
                </div>                 
            </li>
            <!--/Item 3--> 

            <!--Item 4-->
            <li>
                <div class="preview">
                    <img alt=" " src="${pageContext.request.contextPath}/images/portfolio/thumb/holiday.png">
                    <div class="overlay">
                    </div>
                    <div class="links">
                        <a data-toggle="modal" href="#modal-4"><i class="icon-eye-open"></i></a><a href="#"><i class="icon-link"></i></a>                                
                    </div>
                </div>
                <div class="desc">
                    <h5>Worked Days</h5>
                </div>
                <div id="modal-4" class="modal hide fade">
                    <a class="close-modal" href="javascript:;" data-dismiss="modal" aria-hidden="true"><i class="icon-remove"></i></a>
                    <div class="modal-body">
                        <img src="${pageContext.request.contextPath}/images/portfolio/full/holiday.png" alt=" " width="100%" style="max-height:400px">
                    </div>
                </div>                 
            </li>
            <!--/Item 4--> 

            <!--Item 5-->
            <li>
                <div class="preview">
                    <img alt=" " src="${pageContext.request.contextPath}/images/portfolio/thumb/timesheet.gif">
                    <div class="overlay">
                    </div>
                    <div class="links">
                        <a data-toggle="modal" href="#modal-5"><i class="icon-eye-open"></i></a><a href="#"><i class="icon-link"></i></a>                                
                    </div>
                </div>
                <div class="desc">
                    <h5>Time Sheet</h5>
                </div>
                <div id="modal-5" class="modal hide fade">
                    <a class="close-modal" href="javascript:;" data-dismiss="modal" aria-hidden="true"><i class="icon-remove"></i></a>
                    <div class="modal-body">
                        <img src="${pageContext.request.contextPath}/images/portfolio/full/timesheet.gif" alt=" " width="100%" style="max-height:400px">
                    </div>
                </div>                 
            </li>
            <!--/Item 5--> 

            <!--Item 6-->
            <li>
                <div class="preview">
                    <img alt=" " src="${pageContext.request.contextPath}/images/portfolio/thumb/hcalendar.jpg">
                    <div class="overlay">
                    </div>
                    <div class="links">
                        <a data-toggle="modal" href="#modal-6"><i class="icon-eye-open"></i></a><a href="#"><i class="icon-link"></i></a>                                
                    </div>
                </div>
                <div class="desc">
                    <h5>Holiday Calendar</h5>
                </div>
                <div id="modal-6" class="modal hide fade">
                    <a class="close-modal" href="javascript:;" data-dismiss="modal" aria-hidden="true"><i class="icon-remove"></i></a>
                    <div class="modal-body">
                        <img src="${pageContext.request.contextPath}/images/portfolio/full/hcalendar.jpg" alt=" " width="100%" style="max-height:400px">
                    </div>
                </div>                 
            </li>
            <!--/Item 6-->                 

            <!--Item 7-->
            <li>
                <div class="preview">
                    <img alt=" " src="${pageContext.request.contextPath}/images/portfolio/thumb/profile.jpg">
                    <div class="overlay">
                    </div>
                    <div class="links">
                        <a data-toggle="modal" href="#modal-7"><i class="icon-eye-open"></i></a><a href="#"><i class="icon-link"></i></a>                                
                    </div>
                </div>
                <div class="desc">
                    <h5>Profile</h5>
                </div>
                <div id="modal-1" class="modal hide fade">
                    <a class="close-modal" href="javascript:;" data-dismiss="modal" aria-hidden="true"><i class="icon-remove"></i></a>
                    <div class="modal-body">
                        <img src="${pageContext.request.contextPath}/images/portfolio/full/profile.jpg" alt=" " width="100%" style="max-height:400px">
                    </div>
                </div>                 
            </li>
            <!--/Item 7-->

            <!--Item 8-->
            <li>
                <div class="preview">
                    <img alt=" " src="${pageContext.request.contextPath}/images/portfolio/thumb/messages.png">
                    <div class="overlay">
                    </div>
                    <div class="links">
                        </a><a href="${pageContext.request.contextPath}/employee/messageBoard"><i class="icon-link"></i></a>                                
                    </div>
                </div>
                <div class="desc">
                    <h5>Message Board<font style="color:red">(10)</font></h5>
                </div>
                <div id="modal-8" class="modal hide fade">
                    <a class="close-modal" href="javascript:;" data-dismiss="modal" aria-hidden="true"><i class="icon-remove"></i></a>
                    <div class="modal-body">
                        <img src="${pageContext.request.contextPath}/images/portfolio/full/messages.png" alt=" " width="100%" style="max-height:400px">
                    </div>
                </div>                 
            </li>
        </ul>
        
    </section>

<!--Footer-->
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
        <a href="#">Forgot your password?</a>
    </div>
    <!--/Modal Body-->
</div>
<!--  /Login form -->

<script src="${pageContext.request.contextPath}/js/vendor/jquery-1.9.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/vendor/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>

</body>
</html>
