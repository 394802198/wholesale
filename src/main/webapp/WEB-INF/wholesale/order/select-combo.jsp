<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>  
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<jsp:include page="../header.jsp" />

<style>
.btn.dropdown-toggle.selectpicker.btn-default {
	padding: 0;
}
.table tr {
	height: 49px;
}
</style>

<div class="container-fluid">
	
	<ul class="nav nav-pills nav-wizard" style="">
		<li><a href="javascript:void(0);"><span class="glyphicon glyphicon-search"></span> Check Address</a><div class="nav-arrow"></div></li>
		<li class="active"><div class="nav-wedge"></div><a href="javascript:void(0);"><span class="glyphicon glyphicon-star"></span> Select a Combo</a><div class="nav-arrow"></div></li>
		<li><div class="nav-wedge"></div><a href="javascript:void(0);"><span class="glyphicon glyphicon-pencil"></span> Fill Customer Information</a><div class="nav-arrow"></div></li>
		<li><div class="nav-wedge"></div><a href="javascript:void(0);"><span class="glyphicon glyphicon-eye-open"></span> Review Order</a></li>
	</ul>
	<hr>

	<div class="panel panel-info">
		<div class="panel-body">
			<div class="row">
				<div class="col-md-6">
					<h2 class="text-info">Please Select One Combo</h2>
					<hr>
					<div id="combo-list"></div>
				</div>
				<div class="col-md-6"></div>
			</div>
		</div>
	</div>
	
	<div id="order_for_wholesale_and_enduser"></div>
	
</div>

<script type="text/html" id="combo_list_tmpl" data-ctx="${ctx }">
<jsp:include page="combo-list.html" />
</script>
<script type="text/html" id="order_for_wholesale_and_enduser_tmpl" >
<jsp:include page="order-for-wholesale-and-enduser.html" />
</script>
<script type="text/html" id="order_detail_tmpl" >
<jsp:include page="order-detail.html" />
</script>
<script type="text/html" id="order_detail_total_tmpl" >
<jsp:include page="order-detail-total.html" />
</script>


<jsp:include page="../footer.jsp" />
<jsp:include page="../script.jsp" />
<script type="text/javascript" src="${ctx}/public/wholesale/order/select-combo.js?ver=201493811"></script>
<jsp:include page="../footer-end.jsp" />