<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
        <form:form action="/news" method="post" modelAttribute="news">
            类别<form:select path="category.id" items="${categoryList}" itemLabel="name" itemValue="id"/><br/>
            标题<form:input path="heading" /><br/>
            内容<form:textarea path="content" rows="10" cols="30" ></form:textarea><br/>
            编辑<form:input path="editor" value="${sessionScope.user.id}"/><br/>
            <input type="submit" value="提交">
        </form:form>
    </div>
</div>
<%@ include file="/WEB-INF/view/common/footer_part.html" %>
</html>