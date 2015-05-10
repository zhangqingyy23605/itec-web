<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Add jQuery library -->
<script type="text/javascript" src="/frontend/lib/jquery-1.10.1.min.js"></script>
<!-- Add fancyBox -->
<link rel="stylesheet" href="/frontend/fancybox/jquery.fancybox.css" type="text/css" media="screen" />
<script type="text/javascript" src="/frontend/fancybox/jquery.fancybox.pack.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $(".fancybox").fancybox();
    });
</script>

<div id="header">
    <div id="header_img">
        <div>
            <img src="/image/bg_header.png" />
        </div>
    </div>
    <div id="header_menu">
        <span class="header_menu_item">
            <a href="/">首页</a>
        </span>
        <span class="header_menu_item">
            <a href="/news">新闻中心</a>
        </span>
        <span class="header_menu_item">
            <a href="/teacherAction!about">关于我们</a>
        </span>
        <span class="header_menu_item">
            <a href="/projectAction!project">研究项目</a>
        </span>
        <span class="header_menu_item">
            <a href="/communicateAction!item?item=0">学术交流</a>
        </span>
        <span class="header_menu_item">
            <a href="/outputAction!listAllTypical">成果展示</a>
        </span>
        <span class="header_menu_item">
            <a href="/libraryAction!library">图书馆</a>
        </span>
            <span class="header_menu_item">
                <a href="/auth">
                    <c:choose>
                        <c:when test="${user == null}">登录</c:when>
                        <c:otherwise>注销${user.username}</c:otherwise>
                    </c:choose>
                </a>
            </span>
        <span id="thx">
            <a href="#thx_message" class="fancybox"><img src="/image/thx.png" /></a>
            <div id="thx_message">
                ITEC v4.0：电信学院许世豪<br />
                ITEC v3.0：电信系邱东生<br />
                ITEC v2.2：电信系张翔、陈骁<br />
                ITEC v2.1：电信系黄雨辰、曾梦露<br />
                ITEC V2.0：电信系吴俊、刘德志<br />
                ITEC V1.0：电信系宋道远、陈鹏<br /><br />
                感谢他们在开发和维护过程中所作的探索和努力<br />
                感谢互联网中心ELWG实验室的谢健芬同学对此网站进行了详尽的前期需求调研<br /><br />
                若有技术问题请联系：<br />
                邮件：itec@hust.edu.cn<br />
                电话：027-87544704
            </div>
        </span>
    </div>
</div>