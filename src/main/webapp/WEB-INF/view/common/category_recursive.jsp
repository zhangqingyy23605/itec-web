<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${not empty category.children}">
    <c:forEach items="${category.children}" var="category">
        <li>
            <a href="${page.url}?categoryName=${category.name}">
                <c:out value="${space}" escapeXml="false"/>${category.name}
            </a>
        </li>
        <c:set var="space" value="${space}&nbsp;" scope="request"/>
        <c:set var="category" value="${category}" scope="request"/>
        <jsp:include page="/WEB-INF/view/common/category_recursive.jsp"/>
    </c:forEach>
</c:if>
<c:set var="space" value="${fn:substring(space, 0, fn:length(space)-6)}" scope="request"/>