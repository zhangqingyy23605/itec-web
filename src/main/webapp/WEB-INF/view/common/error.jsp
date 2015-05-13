<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <%@ include file="/WEB-INF/view/common/head_tag_part.html" %>
    <link href="/frontend/css/item.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%@ include file="/WEB-INF/view/common/header_part.jsp" %>
<div class="frame">
    <div class="frame_head">
        <span class="frame_name">异常信息</span>
    </div>
    <div class="frame_body">
        <div class="news_title">
                ${exception}
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/view/common/footer_part.html" %>
</html>
