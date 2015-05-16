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
<script src="/frontend/lib/ckeditor/ckeditor.js"></script>
<script type="application/javascript">
    $(function(){
        CKEDITOR.replace('ckeditor');
    })
</script>
<div class="frame">
    <div class="frame_head">
        <span class="frame_name">
            <c:if test="${news.id == null}">添加新闻</c:if>
            <c:if test="${news.id != null}">编辑新闻</c:if>
        </span>
    </div>
    <div class="frame_body">
        <form:form action="/news/${news.id}" method="post" modelAttribute="news">
            标题<form:input path="heading" /><form:errors path="heading" cssClass="error"/><br/>
            &nbsp;&nbsp;类别<form:select path="category.id" items="${categoryList}" itemLabel="name" itemValue="id"/>

            <form:textarea path="content" rows="10" cols="30" id="ckeditor"></form:textarea>
            <form:errors path="content" cssClass="error"/>
            <br/>
            <c:if test="${news.id == null}">
                作者<form:input path="editor" value="${sessionScope.user.id}"/>
                <form:errors path="editor" cssClass="error"/>
                <br/>
            </c:if>
            <c:if test="${news.id != null}">
                <form:hidden path="id"/>
                <input type="hidden" name="_method" value="put"/>
            </c:if>
            <input type="submit" value="<c:if test="${news.id == null}">添加</c:if><c:if test="${news.id != null}">修改</c:if>">
        </form:form>
        <form action="/news/uploadfile" method="post" enctype="multipart/form-data">
            文件<input type="file" name="file">
            <input type="submit" value="上传"/>
        </form>

    </div>
</div>
<%@ include file="/WEB-INF/view/common/footer_part.html" %>
</html>