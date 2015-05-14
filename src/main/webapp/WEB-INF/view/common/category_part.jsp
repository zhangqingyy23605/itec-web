<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="left">
    <div class="frame">
        <div class="frame_head">
            <span class="frame_name"><a href="${page.url}?categoryName=${rootCategory.name}">${rootCategory.name}</a></span>
        </div>
        <div class="frame_body bullet_left">
            <ul>
                <c:set var="space" value="&nbsp;" scope="request"/>
                <c:set var="category" value="${rootCategory}" scope="request"/>
                <jsp:include page="/WEB-INF/view/common/category_recursive.jsp"/>
            </ul>
        </div>
    </div>
</div>