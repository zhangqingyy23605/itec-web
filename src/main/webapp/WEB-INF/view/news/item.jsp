<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <%@ include file="/WEB-INF/view/common/head_tag_part.html" %>
    <link href="/frontend/css/item.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%@ include file="/WEB-INF/view/common/header_part.jsp" %>
<%@ include file="/WEB-INF/view/common/delete_form.html" %>
<div class="frame">
    <div class="frame_head">
        <span class="frame_name">新闻内容</span>
    </div>
    <div class="frame_body">

    <c:choose>
    <c:when test="${empty news}">
        <div style="text-align: center">
            新闻id不存在
        </div>
    </c:when>
    <c:otherwise>
        <div class="news_title">
                ${news.heading}
        </div>
        <div class="news_info">
            <span>日期：<fmt:formatDate value="${news.createTime}" pattern="yyyy-M-d"/>&nbsp;</span>
            <span>作者：${news.editor}&nbsp;</span>
            <span>操作：</span>
            <span href="/news/${news.id}/edit" class="edit">[编辑]</span>
            <span href="/news/${news.id}" class="delete">[删除]</span>
        </div>
        <div class="news_content">
            <c:out value="${news.content}" escapeXml="false"/>
        </div>
    </c:otherwise>
    </c:choose>
    </div>
</div>
<%@ include file="/WEB-INF/view/common/footer_part.html" %>
</html>
