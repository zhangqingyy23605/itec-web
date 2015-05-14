<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <%@ include file="/WEB-INF/view/common/head_tag_part.html" %>
    <link href="/frontend/css/list.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%@ include file="/WEB-INF/view/common/header_part.jsp" %>
<%@ include file="/WEB-INF/view/common/delete_form.html" %>
<%@ include file="/WEB-INF/view/common/category_part.jsp" %>
<div id="right">
    <div id="right_header">
        <img src="/image/column_news.png">
    </div>
    <div class="frame">
        <div class="frame_head">
            <span class="frame_name">${page.categoryName}</span>
        </div>
        <div class="frame_body bullet_right">
        <c:choose>
        <c:when test="${empty newsList}">
            <div style="text-align: center">暂无数据</div>
        </div>
        </c:when>
        <c:otherwise>
            <ul>
                <c:forEach items="${newsList}" var="news">
                    <li>
                        <table>
                            <tr>
                                <td class="item" width="600px">
                                    <ul>
                                        <li>
                                            <a class="item" target="_blank" href="/news/${news.id}">
                                                ${news.heading}
                                            </a>
                                        </li>
                                    </ul>
                                </td>
                                <td width="50px" align="right"><a href="/news/${news.id}/edit">[编辑]</a></td>
                                <td width="50px" align="right"><a href="/news/${news.id}" class="delete">[删除]</a></td>
                                <td width="100px" align="right">[<fmt:formatDate value="${news.createTime}" pattern="yyyy-M-d"/>]</td>
                            </tr>
                            <tr>
                                <td height="3px" colspan="10"
                                    style="background:url(/image/right_seperator.png)"></td>
                            </tr>
                        </table>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div id="pagination">
            共${page.numberOfRecords}条
            第${page.pageNumber}/${page.numberOfPages}页
            <span class="pagination_item"><a href="/news/add">添加</a></span>
            <span class="pagination_item"><a href="/news?pageAction=first">首页</a></span>
            <span class="pagination_item"><a href="/news?pageAction=previous">上一页</a></span>
            <c:forEach begin="1" end="${page.numberOfPages}" var="index">
                <c:choose>
                <c:when test="${index == page.pageNumber}">
                    <span class="pagination_item_selected">
                </c:when>
                <c:otherwise>
                    <span class="pagination_item">
                </c:otherwise>
                </c:choose>
                        <a href="/news?pageAction=${index}">${index}</a>
                    </span>
            </c:forEach>
            <span class="pagination_item"><a href="/news?pageAction=next">下一页</a></span>
            <span class="pagination_item"><a href="/news?pageAction=last">尾页</a></span>
        </div>
        </c:otherwise>
        </c:choose>
    </div>
</div>
<%@ include file="/WEB-INF/view/common/footer_part.html" %>
</body>
</html>
