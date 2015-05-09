<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <%@ include file="/WEB-INF/view/common/head_tag_part.html" %>
    <link href="/frontend/css/item.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%@ include file="/WEB-INF/view/common/header_part.html" %>
<div class="frame">
    <div class="frame_head">
        <span class="frame_name">新闻内容</span>
    </div>
    <div class="frame_body">

    <c:choose>
    <c:when test="${newsItem == null}">
        <div style="text-align: center">
            新闻id不存在
        </div>
    </c:when>
    <c:otherwise>
        <div class="news_title">
                ${newsItem.heading}
        </div>
        <div class="news_info">
            日期：<fmt:formatDate value="${newsItem.addDate}" pattern="yyyy-M-d"/>&nbsp作者：${newsItem.editor}
        </div>
        <div class="news_content">
            <c:out value="${newsItem.content}" escapeXml="false"/>
        </div>
    </c:otherwise>
    </c:choose>
    </div>
</div>
<%@ include file="/WEB-INF/view/common/footer_part.html" %>
</html>