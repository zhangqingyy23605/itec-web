<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <%@ include file="/WEB-INF/view/common/head_tag_part.html" %>
    <link href="/frontend/css/index.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%@ include file="/WEB-INF/view/common/header_part.jsp" %>
<div id="left">
    <div class="frame">
        <div class="frame_head">
            <span class="frame_name">通知公告</span>
            <span class="frame_more"><a href="/news?categoryName=通知公告"><img src="/image/more_left.png" /></a></span>
        </div>
        <div class="frame_body bullet_left_head">
            <ul>
                <c:forEach items="${newsListLeft}" var="news">
                    <li>
                        <a target="_blank" href="/news/${news.id}">
                                ${news.heading}
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <div class="frame" style="border-top:0;">
        <div class="frame_head">
            <span class="frame_name">友情链接</span>
        </div>
        <div class="frame_body bullet_left_bottom">
            <ul>
                <li><a target="_blank" href="http://www.moe.edu.cn/">教育部</a></li>
                <li><a target="_blank" href="http://www.sipo.gov.cn/">国家专利局</a></li>
                <li><a target="_blank" href="http://www.nsfc.gov.cn/">国家自然科学基金委员会</a></li>
                <li><a target="_blank" href="http://www.863.gov.cn">863中国高技术研究发展计划</a></li>
                <li><a target="_blank" href="http://www.rosettanet.org/">RosettaNet联盟</a></li>
                <li><a target="_blank" href="http://www.hust.edu.cn">华中科技大学</a></li>
            </ul>
        </div>
    </div>
</div>
<div id="right">
    <div class="frame">
        <div class="frame_head">
            <span class="frame_name">热点新闻</span>
            <span class="frame_more"><a href="/news?categoryName=热点新闻"><img src="/image/more_right.png" /></a></span>
        </div>
        <div>
            <table>
                <tr>
                    <td valign="middle">
                        <link href="/frontend/lib/fadefocus/fadefocus.css" rel="stylesheet" type="text/css" />
                        <script type="text/javascript" src="/frontend/lib/fadefocus/fadefocus.js"></script>
                        <div id="fade_focus">
                            <div class="load">
                                <img src="/image/loading.gif" width="100" height="100" />
                            </div>
                            <ul class="fade_ul">
                                <li>
                                    <a href="">
                                        <img src="/upfiles/rollpic/rollpic_20110608150611_1936.jpg" width="210" height="160" alt="音乐数字化项目启动暨实施方案论证会召开" />
                                    </a>
                                </li>
                                <li>
                                    <a href="">
                                        <img src="/upfiles/rollpic/rollpic_20110608150601_9816.jpg" width="210" height="160" alt="SC36工作会议在宾州州立学院召开" />
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </td>
                    <td valign="top" align="right">
                        <div class="frame_body bullet_right">
                            <c:forEach items="${newsListRight}" var="news">
                                <table>
                                    <tr>
                                        <td width="350px">
                                            <ul>
                                                <li>
                                                    <a  target="_blank" href="/news/${news.id}">
                                                            ${news.heading}
                                                    </a>
                                                </li>
                                            </ul>
                                        </td>
                                        <td width="100px" align="right">[<fmt:formatDate value="${news.addDate}" pattern="yyyy-M-d"/>]</td>
                                    </tr>
                                    <tr>
                                        <td height="3px" colspan="2"
                                            style="background:url(/image/right_seperator.png)"></td>
                                    </tr>
                                </table>
                            </c:forEach>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div style="background:url(/image/bg_right.png)">
        <table>
            <tr>
                <td height="152">
                    <table width="95%" align="center" cellspacing="0">
                        <tr>
                            <td id="about_heading">
                                <a href="">
                                    湖北省智能互联网技术重点实验室（华中科技大学）<br />
                                    Internet Technology and Engineering R&amp;D Center
                                </a>
                            </td>
                        </tr>
                        <tr>
                            <td id="about_content">
                                <span class="bottomtext">&nbsp;&nbsp互联网技术是目前通信学科的研究热点之一，智能互联网技术湖北省重点实验室瞄准互联网领域的第四代移动通信、主动网络、服务质量等方面的若干热点和难点问题开展了一系列研究，使该学科研究水平紧跟国际学术前沿；同时，我们注重通信系统和网络应用的学科融合，是国内较早开创电子商务、远程教育等交叉研究领域的实验室之一。我实验室的研究工作对于提升我省通信学科的水平，带动相关学科的发展起到了积极的作用。</span><br />
                            </td>
                        </tr>
                    </table>
                </td>
                <td width="177">
                    <img src="/image/building.png" width="177px" height="152px"/>
                </td>
            </tr>
        </table>
    </div>
</div>
<%@ include file="/WEB-INF/view/common/footer_part.html" %>
</html>
