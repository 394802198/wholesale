<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>  
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<jsp:include page="../../header.jsp" />

<style>
.panel-default {
	border-top-color:transparent;
}
</style>

<div class="container-fluid">
	
	<div class="panel panel-info">
		<div class="panel-body">
		
			<!-- Nav tabs -->
			<ul class="nav nav-tabs" id="order-nav">
				<li class="active"><a href="#order-information" data-toggle="tab" ><strong>Order Information</strong></a></li>
				<li><a href="#order-log" data-toggle="tab" ><strong>Order Log</strong></a></li>
				<li><a href="#order-invoice" data-toggle="tab" ><strong>Order Invoice</strong></a></li>
				<li><a href="#order-transactions" data-toggle="tab" ><strong>Order Transactions</strong></a></li>
			</ul>

			<div class="tab-content">
				<div class="tab-pane fade in active" id="order-information"></div>
				<div class="tab-pane fade" id="order-log"></div>
				<div class="tab-pane fade" id="order-invoice"></div>
				<div class="tab-pane fade" id="order-transactions"></div>
			</div>

		</div>
	</div>
	
</div>

<script type="text/html" id="order_information_tmpl" data-ctx="${ctx }" data-orderid="${orderid }">
<jsp:include page="order-information.html" />
</script>
<script type="text/html" id="order_log_tmpl" >
<jsp:include page="order-log.html" />
</script>
<script type="text/html" id="order_invoice_tmpl" >
<jsp:include page="order-invoice.html" />
</script>
<script type="text/html" id="order_transactions_tmpl" >
<jsp:include page="order-transactions.html" />
</script>

<jsp:include page="../../footer.jsp" />
<jsp:include page="../../script.jsp" />
<script type="text/javascript" src="${ctx}/public/wholesale/order/order.js?ver=201493811"></script>
<jsp:include page="../../footer-end.jsp" />