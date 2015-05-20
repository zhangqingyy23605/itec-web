<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<%@ include file="/WEB-INF/view/common/head_tag_part.html" %>
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&amp;subset=all" rel="stylesheet" type="text/css"/>
<link href="/frontend/lib/metronic3.6/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="/frontend/lib/metronic3.6/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="/frontend/lib/metronic3.6/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="/frontend/lib/metronic3.6/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<link href="/frontend/lib/metronic3.6/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
<!-- END GLOBAL MANDATORY STYLES -->
<!-- BEGIN PAGE LEVEL STYLES -->
<link href="/frontend/lib/metronic3.6/global/plugins/bootstrap-fileinput/bootstrap-fileinput.css" rel="stylesheet" type="text/css"/>
<link href="/frontend/lib/metronic3.6/admin/pages/css/profile-old.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE LEVEL STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="/frontend/lib/metronic3.6/global/css/components.css" id="style_components" rel="stylesheet" type="text/css"/>
<link href="/frontend/lib/metronic3.6/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="/frontend/lib/metronic3.6/admin/layout/css/layout.css" rel="stylesheet" type="text/css"/>
<link id="style_color" href="/frontend/lib/metronic3.6/admin/layout/css/themes/darkblue.css" rel="stylesheet" type="text/css"/>
<link href="/frontend/lib/metronic3.6/admin/layout/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="favicon.ico"/>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<!-- DOC: Apply "page-header-fixed-mobile" and "page-footer-fixed-mobile" class to body element to force fixed header or footer in mobile devices -->
<!-- DOC: Apply "page-sidebar-closed" class to the body and "page-sidebar-menu-closed" class to the sidebar menu element to hide the sidebar by default -->
<!-- DOC: Apply "page-sidebar-hide" class to the body to make the sidebar completely hidden on toggle -->
<!-- DOC: Apply "page-sidebar-closed-hide-logo" class to the body element to make the logo hidden on sidebar toggle -->
<!-- DOC: Apply "page-sidebar-hide" class to body element to completely hide the sidebar on sidebar toggle -->
<!-- DOC: Apply "page-sidebar-fixed" class to have fixed sidebar -->
<!-- DOC: Apply "page-footer-fixed" class to the body element to have fixed footer -->
<!-- DOC: Apply "page-sidebar-reversed" class to put the sidebar on the right side -->
<!-- DOC: Apply "page-full-width" class to the body element to have full width page without the sidebar menu -->
<body class="page-header-fixed page-quick-sidebar-over-content page-quick-sidebar-open">

<jsp:include page="/WEB-INF/view/admin/topmenu.jsp"/>

<!-- BEGIN CONTAINER -->
<div class="page-container">
    <jsp:include page="/WEB-INF/view/admin/sidebar.jsp"/>
	<!-- BEGIN CONTENT -->
	<div class="page-content-wrapper">
		<div class="page-content">
			<!-- BEGIN PAGE HEADER-->
			<h3 class="page-title">
                个人中心
			</h3>
			<!-- END PAGE HEADER-->
			<!-- BEGIN PAGE CONTENT-->
			<div class="row profile">
				<div class="col-md-12">
                    <div class="tab-pane" id="tab_1_3">
                        <div class="row profile-account">
                            <div class="col-md-3">
                                <ul class="ver-inline-menu tabbable margin-bottom-10">
                                    <li class="active">
                                        <a data-toggle="tab" href="#tab_1-1">
                                            <i class="fa fa-cog"></i>基本信息</a>
												<span class="after">
												</span>
                                    </li>
                                    <li>
                                        <a data-toggle="tab" href="#tab_2-2">
                                            <i class="fa fa-picture-o"></i>更换头像</a>
                                    </li>
                                    <li>
                                        <a data-toggle="tab" href="#tab_3-3">
                                            <i class="fa fa-lock"></i>更改密码</a>
                                    </li>
                                </ul>
                            </div>
                            <div class="col-md-9">
                                <div class="tab-content">
                                    <div id="tab_1-1" class="tab-pane active">
                                        <form:form role="form" action="/admin/user" modelAttribute="user" method="post">
                                            <input type="hidden" name="_method" value="put"/>
                                            <form:hidden path="id"/>
                                            <div class="form-group">
                                                <label class="control-label">类型</label>
                                                <form:input path="type" class="form-control" readonly="true"/>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">权限</label>
                                                <form:input path="privilege" class="form-control" readonly="true"/>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">姓名</label>
                                                <form:input path="fullname" class="form-control"/>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">电子邮箱</label>
                                                <form:input path="email" class="form-control"/>
                                            </div>
                                            <c:if test="${user.type == 'TEACHER'}">
                                                教师可见
                                            <div class="form-group">
                                                <label class="control-label">个人网站</label>
                                                <form:input path="website" class="form-control"/>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">个人简介</label>
                                                <form:textarea path="introduction" class="form-control" rows="3"/>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">研究组</label>
                                                <form:input path="researchGroup" class="form-control"/>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">研究方向</label>
                                                <form:textarea path="researchAreas" class="form-control" rows="3"/>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">教授课程</label>
                                                <form:textarea path="teachingCourses" class="form-control" rows="3"></form:textarea>
                                            </div>
                                            </c:if>
                                            <c:if test="${user.type == 'STUDENT'}">
                                                <div class="form-group">
                                                    <label class="control-label">导师</label>
                                                    <form:select path="mentor.id" items="${teachers}" itemLabel="fullname" itemValue="id"/>
                                                </div>
                                            </c:if>
                                            <div class="margiv-top-10">
                                                <input type="submit" value="提交修改" class="btn green"/>
                                            </div>
                                        </form:form>
                                    </div>
                                    <div id="tab_2-2" class="tab-pane">
                                        <form action="#" role="form">
                                            <div class="form-group">
                                                <div class="fileinput fileinput-new" data-provides="fileinput">
                                                    <div class="fileinput-new thumbnail" style="width: 200px; height: 150px;">
                                                        <img src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image" alt=""/>
                                                    </div>
                                                    <div class="fileinput-preview fileinput-exists thumbnail" style="max-width: 200px; max-height: 150px;">
                                                    </div>
                                                    <div>
																<span class="btn default btn-file">
																<span class="fileinput-new">
																Select image </span>
																<span class="fileinput-exists">
																Change </span>
																<input type="file" name="...">
																</span>
                                                        <a href="#" class="btn default fileinput-exists" data-dismiss="fileinput">
                                                            Remove </a>
                                                    </div>
                                                </div>
                                                <div class="clearfix margin-top-10">
															<span class="label label-danger">
															NOTE! </span>
															<span>
															Attached image thumbnail is supported in Latest Firefox, Chrome, Opera, Safari and Internet Explorer 10 only </span>
                                                </div>
                                            </div>
                                            <div class="margin-top-10">
                                                <a href="#" class="btn green">
                                                    Submit </a>
                                                <a href="#" class="btn default">
                                                    Cancel </a>
                                            </div>
                                        </form>
                                    </div>
                                    <div id="tab_3-3" class="tab-pane">
                                        <form action="#">
                                            <div class="form-group">
                                                <label class="control-label">Current Password</label>
                                                <input type="password" class="form-control"/>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">New Password</label>
                                                <input type="password" class="form-control"/>
                                            </div>
                                            <div class="form-group">
                                                <label class="control-label">Re-type New Password</label>
                                                <input type="password" class="form-control"/>
                                            </div>
                                            <div class="margin-top-10">
                                                <a href="#" class="btn green">
                                                    Change Password </a>
                                                <a href="#" class="btn default">
                                                    Cancel </a>
                                            </div>
                                        </form>
                                    </div>
                                    <div id="tab_4-4" class="tab-pane">
                                        <form action="#">
                                            <table class="table table-bordered table-striped">
                                                <tr>
                                                    <td>
                                                        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus..
                                                    </td>
                                                    <td>
                                                        <label class="uniform-inline">
                                                            <input type="radio" name="optionsRadios1" value="option1"/>
                                                            Yes </label>
                                                        <label class="uniform-inline">
                                                            <input type="radio" name="optionsRadios1" value="option2" checked/>
                                                            No </label>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        Enim eiusmod high life accusamus terry richardson ad squid wolf moon
                                                    </td>
                                                    <td>
                                                        <label class="uniform-inline">
                                                            <input type="checkbox" value=""/> Yes </label>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        Enim eiusmod high life accusamus terry richardson ad squid wolf moon
                                                    </td>
                                                    <td>
                                                        <label class="uniform-inline">
                                                            <input type="checkbox" value=""/> Yes </label>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        Enim eiusmod high life accusamus terry richardson ad squid wolf moon
                                                    </td>
                                                    <td>
                                                        <label class="uniform-inline">
                                                            <input type="checkbox" value=""/> Yes </label>
                                                    </td>
                                                </tr>
                                            </table>
                                            <!--end profile-settings-->
                                            <div class="margin-top-10">
                                                <a href="#" class="btn green">
                                                    Save Changes </a>
                                                <a href="#" class="btn default">
                                                    Cancel </a>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <!--end col-md-9-->
                        </div>
                    </div>
				</div>
			</div>
			<!-- END PAGE CONTENT-->
		</div>
	</div>
	<!-- END CONTENT -->
</div>
<!-- END CONTAINER -->
<!-- BEGIN FOOTER -->
<div class="page-footer">
	<div class="page-footer-inner">
        湖北省智能互联网技术重点实验室
	</div>
</div>
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="/frontend/lib/metronic3.6/global/plugins/respond.min.js"></script>
<script src="/frontend/lib/metronic3.6/global/plugins/excanvas.min.js"></script> 
<![endif]-->
<script src="/frontend/lib/metronic3.6/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="/frontend/lib/metronic3.6/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="/frontend/lib/metronic3.6/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
<script src="/frontend/lib/metronic3.6/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="/frontend/lib/metronic3.6/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="/frontend/lib/metronic3.6/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="/frontend/lib/metronic3.6/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="/frontend/lib/metronic3.6/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="/frontend/lib/metronic3.6/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="/frontend/lib/metronic3.6/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script type="text/javascript" src="/frontend/lib/metronic3.6/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="/frontend/lib/metronic3.6/global/scripts/metronic.js" type="text/javascript"></script>
<script src="/frontend/lib/metronic3.6/admin/layout/scripts/layout.js" type="text/javascript"></script>
<%--<script src="/frontend/lib/metronic3.6/admin/layout/scripts/quick-sidebar.js" type="text/javascript"></script>--%>
<script src="/frontend/lib/metronic3.6/admin/layout/scripts/demo.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
jQuery(document).ready(function() {       
   // initiate layout and plugins
   Metronic.init(); // init metronic core components
Layout.init(); // init current layout
//QuickSidebar.init(); // init quick sidebar
Demo.init(); // init demo features
});
</script>
<!-- END JAVASCRIPTS -->
</body>

<!-- END BODY -->
</html>