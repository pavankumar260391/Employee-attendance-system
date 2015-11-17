<header class="navbar navbar-fixed-top">
        <div class="navbar-inner">
            <div class="container">
                <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
                  <a id="logo" class="pull-left" href="index.html"></a>
                   <img alt="" src="${pageContext.request.contextPath}/images/bg.jpg" style="margin-top:10px;"/>
                <div class="nav-collapse collapse pull-right">
                    <ul class="nav">
                        <li><a href="index.html">Home</a></li>
                       <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Attendance <i class="icon-angle-down"></i></a>
                            <ul class="dropdown-menu">
                                <li><a href="career.html">Manual Attendance</a></li>
                                <li><a href="blog-item.html">Delete Attendance</a></li>
                                <li><a href="${pageContext.request.contextPath}/admin/adminAttendance">View Attendance</a></li>
                                <li><a href="${pageContext.request.contextPath}/admin/adminUpdateAttendance">Update Attendance</a></li>
                                <li><a href="${pageContext.request.contextPath}/admin/adminAttendanceToday">Today's Attendance</a></li>
                             	<li><a href="${pageContext.request.contextPath}/admin/showMonthlyAttendance">Show Monthly Attendance</a></li>
                                
                            </ul>
                        </li>
                        <li class="dropdown">
                        	<a href="#" class="dropdown-toggle" data-toggle="dropdown">Services<i class="icon-angle-down"></i></a>
                        	<ul class="dropdown-menu">
                        		<li><a href="${pageContext.request.contextPath}/admin/showLeaveBalance">Show Leave Balance</a></li>
                        		<li><a href="${pageContext.request.contextPath}/admin/adminApproveLeave">Leave Approval</a></li> 
                        	    <li><a href="${pageContext.request.contextPath}/admin/adminViewLwp">View LWP</a></li>
                        		<li><a href="${pageContext.request.contextPath}/admin/adminMarkLwp">Mark LWP</a></li>
                        	</ul>
                        </li> 
                        
                        <li><a href="services.html">Services</a></li>
                        <li><a href="${pageContext.request.contextPath}/admin/adminUpdateAttendance">Update Attendance</a></li>
                        <li class="active"><a href="portfolio.html">Gallery</a></li>
                          <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Action <i class="icon-angle-down"></i></a>
                            <ul class="dropdown-menu">
                                <li><a href="${pageContext.request.contextPath}/admin/addHolidayCalendar">Add Holiday Calendar</a></li>
                                <li><a href="${pageContext.request.contextPath}/admin/addDepartment">Add Department</a></li>
                                <li><a href="${pageContext.request.contextPath}/admin/addDesignation">Add Designation</a></li>
                                <li><a href="${pageContext.request.contextPath}/admin/addLeaveReason">Add Leave Reason</a></li>
                                <li><a href="${pageContext.request.contextPath}/admin/addLeaveType">Add Leave Type</a></li>
                                <li><a href="${pageContext.request.contextPath}/admin/LeaveReason">Leave Reason</a></li>
                               <%--  <li><a href="${pageContext.request.contextPath}/admin/registerpage">Register</a></li> --%>
                             <li><a href="${pageContext.request.contextPath}/admin/addHolidayCalender">Add Holiday Calendar</a></li>
                                <li><a href="${pageContext.request.contextPath}/admin/viewHolidayCalender">View Holiday calender</a></li>
                                 <li><a href="${pageContext.request.contextPath}/admin/showAllFaculty">Show Employees</a></li>
                                 <li><a href="${pageContext.request.contextPath}/admin/addEmployees">Add Employees</a></li>
                                  <li><a href="${pageContext.request.contextPath}/admin/reporteeManagement">Reportee Management</a></li>
                                  <li><a href="${pageContext.request.contextPath}/admin/showAllFacultyWithleaveHistory">Leave History</a></li>
                                  
                            </ul>
                        </li>
                        
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Message Board <i class="icon-angle-down"></i></a>
                            <ul class="dropdown-menu">
                                <li><a href="${pageContext.request.contextPath}/admin/uploadMessageBoard">Post Message</a></li>
                                   <li class="divider"></li>	
                                 <li><a href="${pageContext.request.contextPath}/admin/messageBoardInbox">Message Inbox</a></li>
                            </ul>
                        </li>
                        <li><a href="contact-us.html">Contact</a></li>
                          <li><a href="${pageContext.request.contextPath}/common/logout">Logout</a></li>
                       <li class="login">
                           <img alt="" src="${pageContext.request.contextPath}/images/user.jpg" width="40" height="40"  class="img-circle">
                           Hello Amit!
                        </li>
                    </ul>        
                </div><!--/.nav-collapse -->
            </div>
        </div>
           <section class="title" >
		<div class="container">
			<div class="row-fluid">
				  <div class="span6">
                    <h4>${pageTitle}</h4>
                </div>
                <div class="span6">
                 
                </div>
			</div>
		</div>
	</section>
    </header>