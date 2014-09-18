<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>  
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<jsp:include page="../header.jsp" />

<style>
</style>

<div class="container-fluid">
	
	<ul class="nav nav-pills nav-wizard" style="">
		<li><a href="javascript:void(0);"><span class="glyphicon glyphicon-search"></span> Check Address</a><div class="nav-arrow"></div></li>
		<li><div class="nav-wedge"></div><a href="javascript:void(0);"><span class="glyphicon glyphicon-star"></span> Select a Combo</a><div class="nav-arrow"></div></li>
		<li class="active"><div class="nav-wedge"></div><a href="javascript:void(0);"><span class="glyphicon glyphicon-pencil"></span> Fill Customer Information</a><div class="nav-arrow"></div></li>
		<li><div class="nav-wedge"></div><a href="javascript:void(0);"><span class="glyphicon glyphicon-eye-open"></span> Review Order</a></li>
	</ul>
	<hr>

	<div id="information-content"></div>
	
</div>

<script type="text/html" id="information_content_tmpl" data-ctx="${ctx }">
<jsp:include page="information-content.html" />
</script>

<jsp:include page="../footer.jsp" />
<jsp:include page="../script.jsp" />
<script type="text/javascript" src="${ctx}/public/wholesale/order/information.js?ver=201493811"></script>
<jsp:include page="../footer-end.jsp" />