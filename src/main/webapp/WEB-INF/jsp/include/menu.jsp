<%@ page contentType="text/html; charset=utf-8" %><%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!--Header-part-->
<div id="header">
    <h1><a href="dashboard.html">Maruti Admin</a></h1>
</div>
<!--close-Header-part--> 

<!--top-Header-messaages-->
<div class="btn-group rightzero"> <a class="top_message tip-left" title="Manage Files"><i class="icon-file"></i></a> <a class="top_message tip-bottom" title="Manage Users"><i class="icon-user"></i></a> <a class="top_message tip-bottom" title="Manage Comments"><i class="icon-comment"></i><span class="label label-important">5</span></a> <a class="top_message tip-bottom" title="Manage Orders"><i class="icon-shopping-cart"></i></a> </div>
<!--close-top-Header-messaages--> 

<!--top-Header-menu-->
<div id="user-nav" class="navbar navbar-inverse">
    <ul class="nav">
        <li class="" ><a title="" href="#"><i class="icon icon-user"></i> <span class="text">${userlogin.username}</span></a></li>
        <li class=" dropdown" id="menu-messages"><a href="#" data-toggle="dropdown" data-target="#menu-messages" class="dropdown-toggle"><i class="icon icon-envelope"></i> <span class="text">Messages</span> <span class="label label-important">5</span> <b class="caret"></b></a>
            <ul class="dropdown-menu">
                <li><a class="sAdd" title="" href="#">new message</a></li>
                <li><a class="sInbox" title="" href="#">inbox</a></li>
                <li><a class="sOutbox" title="" href="#">outbox</a></li>
                <li><a class="sTrash" title="" href="#">trash</a></li>
            </ul>
        </li>
        <li class=""><a title="" href="#"><i class="icon icon-cog"></i> <span class="text">Settings</span></a></li>
        <li class=""><a title="" href="<c:url value="/admin/logout"/>"><i class="icon icon-share-alt"></i> <span class="text">Logout</span></a></li>
    </ul>
</div>
<!--<div id="search">
    <input type="text" placeholder="Search here..."/>
    <button type="submit" class="tip-left" title="Search"><i class="icon-search icon-white"></i></button>
</div>-->
<!--close-top-Header-menu-->

<div id="sidebar"> 
    <a href="#" class="visible-phone"><i class="icon icon-signal"></i> Charts &amp; graphs</a>
    <ul>
        <li class="submenu"> <a href="<c:url value="/admin/index"/>"><i class="icon icon-th-list"></i> <span>Trang chủ</span> </a> </li>
        <li class="submenu"> <a href="<c:url value="/admin/airport/"/>"><i class="icon icon-th-list"></i> <span>Quản lý sân bay</span> <span class="label label-important">3</span></a>
            <ul>
                <li><a href="<c:url value="/admin/sys-account/"/>">Quản lý tài khoản Quản trị</a></li>
                <li><a href="<c:url value="/admin/module/"/>">Quản lý module SYS</a></li>
                <li><a href="<c:url value="/admin/group-account/"/>">Quản lý nhóm tài khoản</a></li>
            </ul>
        </li>
        <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>Quản trị hệ thống</span> <span class="label label-important">3</span></a>
            <ul>
                <li><a href="<c:url value="/admin/sys-account/"/>">Quản lý tài khoản Quản trị</a></li>
                <li><a href="<c:url value="/admin/module/"/>">Quản lý module</a></li>
                <li><a href="<c:url value="/admin/group-account/"/>">Quản lý nhóm tài khoản</a></li>
                <li><a href="<c:url value="/admin/config-category/"/>">Quản lý Config Category</a></li>
            </ul>
        </li>
        <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>Quản lý Công ty</span> <span class="label label-important">3</span></a>
            <ul>
                <li><a href="<c:url value="/admin/company"/>">Quản lý công ty</a></li>
                <li><a href="<c:url value="/admin/branch"/>">Quản lý chi nhánh</a></li>
                <li><a href="<c:url value="/admin/bank-info"/>">Quản lý Thông tin ngân hàng</a></li>
            </ul>
        </li>
        <li class="submenu"> <a href="#"><i class="icon icon-file"></i> <span>Kudo</span> <span class="label label-important">4</span></a>
            <ul>
                <li><a href="<c:url value="/admin/gender/"/>">Gender</a></li>
                <li><a href="<c:url value="/admin/marital_status/"/>">Marital_status</a></li>
                <li><a href="<c:url value="/admin/city/"/>">City</a></li>
                <li><a href="<c:url value="/admin/country/"/>">Country</a></li>
                <li><a href="<c:url value="/admin/countrylanguage/"/>">CountryLanguage</a></li>
                <li><a href="<c:url value="/admin/dateformat/"/>">cf_dateformat</a></li>
                <li><a href="<c:url value="/admin/timeformat/"/>">cf_timeformat</a></li>
                <li><a href="<c:url value="/admin/config_timezone/"/>">cf_timezone</a></li>
                <li><a href="<c:url value="/admin/timezones/"/>">timezones</a></li>
            </ul>
        </li>
        <li class="submenu"> <a href="#"><i class="icon icon-th-list"></i> <span>QLNV </span> <span class="label label-important">4</span></a>
            <ul>
                <li><a href="<c:url value="/admin/emp_termination/"/>">Danh sách nghỉ việc</a></li>
                <li><a href="<c:url value="/admin/emp_termination_reseason/"/>">Lý do nghỉ việc</a></li>
                <li><a href="<c:url value="/admin/user_action/"/>">Hành động của người dùng</a></li>
                <li><a href="<c:url value="/admin/user_type/"/>">Kiểu người dùng</a></li>
                <li><a href="<c:url value="/admin/nationality/"/>">Quốc tịch</a></li>
                <li><a href="<c:url value="/admin/job_title/"/>">Chức vụ</a></li>

            </ul>
        </li>
    </ul>
</div>
