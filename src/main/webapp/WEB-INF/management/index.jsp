<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<jsp:include page="header.jsp" />
<jsp:include page="alert.jsp" />

<div class="container-fluid">
	<div class="row">
	
		<!-- System -->
		<div class="col-md-3">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title text-info">System</h3>
				</div>
				<div class="panel-body">
					<ul class="list-unstyled">
						<li>
							<span class="glyphicon glyphicon-list"></span>&nbsp;
							<a href="${ctx }/management/system/manager/view">View Manager</a>
						</li>
						<li>
							<span class="glyphicon glyphicon-plus"></span>&nbsp;
							<a href="${ctx }/management/system/manager/create">Create Manager</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		
		<!-- Product -->
		<div class="col-md-3">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title text-info">Product</h3>
				</div>
				<div class="panel-body">
					<ul class="list-unstyled">
						<li>
							<span class="glyphicon glyphicon-plus" ></span>&nbsp;
							<a href="${ctx }/management/product/material/create">Create Material</a>
						</li>
						<li>
							<span class="glyphicon glyphicon-list" ></span>&nbsp;
							<a href="${ctx }/management/product/material-combo/view">View Material & Combo</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		
		<!-- Wholesale -->
		<div class="col-md-3">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title text-info">Wholesale</h3>
				</div>
				<div class="panel-body">
					<ul class="list-unstyled">
						<li>
							<span class="glyphicon glyphicon-list"></span>&nbsp;
							<a href="${ctx }/management/wholesale/wholesaler/view">View Wholesaler</a>
						</li>
						<li>
							<span class="glyphicon glyphicon-plus"></span>&nbsp;
							<a href="${ctx }/management/wholesale/wholesaler/create">Create Wholesaler</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		
		<!-- Provision -->
		<div class="col-md-3">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title text-info">Provision</h3>
				</div>
				<div class="panel-body">
					<ul class="list-unstyled">
						<li>
							<span class="glyphicon glyphicon-list"></span>&nbsp;
							<a href="${ctx }/management/provision/view">View Provision</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	
	<div class="row">
	
		<!-- Billing -->
		<div class="col-md-3">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title text-info">Billing</h3>
				</div>
				<div class="panel-body">
					<ul class="list-unstyled">
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="footer.jsp" />
<jsp:include page="script.jsp" />
<jsp:include page="footer-end.jsp" />