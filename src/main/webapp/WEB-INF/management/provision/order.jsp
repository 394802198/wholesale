<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<jsp:include page="../header.jsp" />
<jsp:include page="../alert.jsp" />

<style>
.panel-default {
	border-top-color:transparent;
}
</style>


<div class="container">
	<div class="row">
		<div class="col-md-12">
		
			<!-- Nav tabs -->
			<ul class="nav nav-tabs">
				<li class="${ orderDetailActive!=null ? orderDetailActive : '' }"><a href="#order_detail" data-toggle="tab"><strong>Order Detail</strong></a></li>
				<li><a href="#invoice_detail" data-toggle="tab"><strong>Invoice Detail</strong></a></li>
				<li><a href="#transaction_detail" data-toggle="tab"><strong>Transaction Detail</strong></a></li>
				<li><a href="#customer_service_record_detail" data-toggle="tab"><strong>Customer Service Record</strong></a></li>
				<li><a href="#customer_ticket_record_detail" data-toggle="tab"><strong>Customer Ticket Record</strong></a></li>
			</ul>

			<!-- Tab panes -->
			<div class="tab-content panel panel-default">
				<div class="panel-body tab-pane fade in ${ orderDetailActive!=null ? orderDetailActive : '' }" id="order_detail" ></div>
				<div class="panel-body tab-pane fade" id="invoice_detail"></div>
				<div class="panel-body tab-pane fade" id="transaction_detail"></div>
				<div class="panel-body tab-pane fade" id="customer_service_record_detail"></div>
				<div class="panel-body tab-pane fade" id="customer_ticket_record_detail"></div>
			</div>
			
		</div>
	</div>
</div>

<!-- Order Detail Template -->
<script type="text/html" id="order_detail_table_tmpl">
<jsp:include page="order-detail.html" />
</script>

<jsp:include page="../footer.jsp" />
<jsp:include page="../script.jsp" />
<script>

	var ctx = '${ctx}';
	var o_id = '${o.id}';

</script>
<script type="text/javascript" src="${ctx}/public/management/provision/order.js"></script>
<jsp:include page="../footer-end.jsp" />