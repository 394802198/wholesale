<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>  
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<jsp:include page="../header.jsp" />

<div class="container-fluid">
	
	<ul class="nav nav-pills nav-wizard" style="">
		<li class="active"><a href="javascript:void(0);"><span class="glyphicon glyphicon-search"></span> Check Address</a><div class="nav-arrow"></div></li>
		<li><div class="nav-wedge"></div><a href="javascript:void(0);"><span class="glyphicon glyphicon-start"></span> Select a Combo</a><div class="nav-arrow"></div></li>
		<li><div class="nav-wedge"></div><a href="javascript:void(0);"><span class="glyphicon glyphicon-pencil"></span> Fill Customer Information</a><div class="nav-arrow"></div></li>
		<li><div class="nav-wedge"></div><a href="javascript:void(0);"><span class="glyphicon glyphicon-eye-open"></span> Review Order</a></li>
	</ul>
	<hr>

	<h2>
		Address Verification Matches
	</h2>
	
	
	<form action="">
	
	<div class="row" style="margin-top:30px;">
		<div class="col-md-6">
		
			
			<div class="input-group">
				<input id="address" value="" type="text" class="form-control input-lg" placeholder="Put address here" /> 
				<span class="input-group-btn">
					
					<button class="btn btn-success btn-lg ladda-button" data-style="zoom-in" type="submit" id="goCheck">
						<span class="ladda-label glyphicon glyphicon-search"></span>
					</button>
					</form>
				</span>
			</div>
			
		</div>
	</div>
	
	</form>
	
	<div id="address-check-result" style="min-height:600px;margin-top:15px;"></div>
	
</div>

<div id="map_canvas" style="width:720px;height:600px;display:none;"></div>
<script type="text/html" id="address_check_result_tmpl" data-ctx="${ctx }">
<jsp:include page="address-check-result.html" />
</script>

<jsp:include page="../footer.jsp" />
<jsp:include page="../script.jsp" />
<script type="text/javascript" src="${ctx}/public/wholesale/order/address-check.js?ver=201493811"></script>
<script src="https://maps.google.com/maps/api/js?sensor=false&libraries=places&region=NZ" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/public/bootstrap3/js/autoCompleteAddress.js"></script>
<jsp:include page="../footer-end.jsp" />