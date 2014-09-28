<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>  
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<jsp:include page="../../header.jsp" />

<div class="jumbotron" style="margin-top:-20px;">
	<div class="container-fluid">	
		<h1 class="text-info text-center">Thank You.</h1>
		<p class="text-info text-center">
			We are processing your order and will send you an email confirmation shortly.
		</p>
		<p class="text-info text-center">
			<a href="${ctx }/order/query" ><span class="glyphicon glyphicon-search"></span> Order Query</a>
		</p>
	</div>
</div>

<jsp:include page="../../footer.jsp" />
<jsp:include page="../../script.jsp" />
<jsp:include page="../../footer-end.jsp" />