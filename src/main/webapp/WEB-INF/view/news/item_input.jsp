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
        <span class="frame_name">添加/编辑新闻</span>
    </div>
    <div class="frame_body">
        <form action="/news" method="post">
            类别<input type="text" name="category.id" /><br/>
            标题<input type="text" name="heading" /><br/>
            内容<textarea name="content" rows="10" cols="30" ></textarea><br/>
            作者<input type="text" name="editor" /><br/>
            <input type="submit" value="提交">
        </form>
    </div>
</div>
<%@ include file="/WEB-INF/view/common/footer_part.html" %>
</html>
