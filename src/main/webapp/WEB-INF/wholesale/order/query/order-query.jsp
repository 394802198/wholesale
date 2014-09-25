<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>  
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<jsp:include page="../../header.jsp" />

<div class="container-fluid">
	
	<div id="order-query-page"></div>
	
</div>

<script type="text/html" id="order_query_page_tmpl" data-ctx="${ctx }">
<jsp:include page="order-query-page.html" />
</script>

<jsp:include page="../../footer.jsp" />
<jsp:include page="../../script.jsp" />
<script type="text/javascript" src="${ctx}/public/wholesale/order/order-query.js?ver=201493811"></script>
<jsp:include page="../../footer-end.jsp" />