<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!-- BEGIN SIDEBAR -->
<div class="page-sidebar-wrapper">
    <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
    <!-- DOC: Change data-auto-speed="200" to adjust the sub menu slide up/down speed -->
    <div class="page-sidebar navbar-collapse collapse">
        <!-- BEGIN SIDEBAR MENU -->
        <!-- DOC: Apply "page-sidebar-menu-light" class right after "page-sidebar-menu" to enable light sidebar menu style(without borders) -->
        <!-- DOC: Apply "page-sidebar-menu-hover-submenu" class right after "page-sidebar-menu" to enable hoverable(hover vs accordion) sub menu mode -->
        <!-- DOC: Apply "page-sidebar-menu-closed" class right after "page-sidebar-menu" to collapse("page-sidebar-closed" class must be applied to the body element) the sidebar sub menu mode -->
        <!-- DOC: Set data-auto-scroll="false" to disable the sidebar from auto scrolling/focusing -->
        <!-- DOC: Set data-keep-expand="true" to keep the submenues expanded -->
        <!-- DOC: Set data-auto-speed="200" to adjust the sub menu slide up/down speed -->
        <ul class="page-sidebar-menu" data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
            <li class="heading">
                <h3 class="uppercase">管理后台</h3>
            </li>
            <%--<c:set var="category" value="${rootCategory}" scope="request"/>--%>
            <%--<jsp:include page="/WEB-INF/view/admin/sidebar_recursive.jsp"/>--%>
            <li>
                <a href="/admin/profile">
                    <i class="icon-home"></i>
                    <span class="title">个人中心</span>
                </a>
            </li>
            <li>
                <a href="/admin/user">
                    <i class="icon-user"></i>
                    <span class="title">用户管理</span>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a href="">
                            <i class="icon-eyeglasses"></i>
                            教师</a>
                    </li>
                    <li>
                        <a href="">
                            <i class="icon-graduation"></i>
                            学生</a>
                    </li>
                </ul>
            </li>
        </ul>
        <!-- END SIDEBAR MENU -->
    </div>
</div>
<!-- END SIDEBAR -->