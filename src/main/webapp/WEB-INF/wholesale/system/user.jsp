<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<jsp:include page="../header.jsp" />
<jsp:include page="../alert.jsp" />

<div class="container-fluid">
	<div class="panel panel-info">
		<div class="panel-heading">
			<div class="row">
				<div class="col-md-6">
					<h3 class="panel-title" id="heading"></h3>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<form class="form-horizontal">
				<%-- <div class="form-group">
			    	<label for="id" class="col-md-2 control-label">ID</label>
				    <div class="col-md-3">
				    	<p class="form-control-static">${wholesalerSession.id }</p>
				    </div>
			  	</div> --%>
			  	<div class="form-group">
			    	<label for="company_name" class="col-md-2 control-label">Company Name</label>
				    <div class="col-md-3">
				      	<p class="form-control-static">${wholesalerSession.company_name }</p>
				    </div>
			  	</div>
			  	<div class="form-group">
			    	<label for="name" class="col-md-2 control-label">Name <span class="text-danger">(*)</span></label>
				    <div class="col-md-3">
				      	<input type="text" class="form-control" id="name" placeholder="" data-error-field>
				    </div>
			  	</div>
			  	<div class="form-group">
			    	<label for="login_name" class="col-md-2 control-label">Login Name <span class="text-danger">(*)</span></label>
				    <div class="col-md-3">
				      	<input type="text" class="form-control" id="login_name" placeholder="" data-error-field>
				    </div>
			  	</div>
			  	<div class="form-group">
			    	<label for="login_password" class="col-md-2 control-label">Password <span class="text-danger">(*)</span></label>
				    <div class="col-md-3">
				      	<input type="password" class="form-control" id="login_password" placeholder="" data-error-field>
				    </div>
			  	</div>
			  	<div class="form-group">
			    	<label for="cellphone" class="col-md-2 control-label">Cell phone <span class="text-danger">(*)</span></label>
				    <div class="col-md-3">
				      	<input type="text" class="form-control" id="cellphone" placeholder="" data-error-field>
				    </div>
			  	</div>
			  	<div class="form-group">
			    	<label for="email" class="col-md-2 control-label">Email <span class="text-danger">(*)</span></label>
				    <div class="col-md-3">
				      	<input type="email" class="form-control" id="email" placeholder="" data-error-field>
				    </div>
			  	</div>
			  	<div class="form-group">
			    	<label for="role" class="col-md-2 control-label">Role <span class="text-danger">(*)</span></label>
				    <div class="col-md-3">
				      	<select class="form-control selectpicker" id="role" data-error-field>
				      		<option value="Administrator">Administrator</option>
				      		<option value="Accountant">Accountant</option>
				      		<option value="Provisioner">Provisioner</option>
				      	</select>
				    </div>
			  	</div>
			  	<div class="form-group">
			    	<label for="memo" class="col-md-2 control-label">Memo</label>
				    <div class="col-md-3">
				      	<input type="text" class="form-control" id="memo" placeholder="">
				    </div>
			  	</div>
			  	<div class="form-group">
			    	<label for="status" class="col-md-2 control-label">Status <span class="text-danger">(*)</span></label>
				    <div class="col-md-3">
				      	<select class="form-control selectpicker" id="status" data-error-field>
				      		<option value="active">Active</option>
				      		<option value="disable">Disable</option>
				      	</select>
				    </div>
			  	</div>
			  	<div class="form-group">
				    <div class="col-md-3 col-md-offset-2">
				    	<button type="submit" data-style="zoom-in" class="btn btn-info btn-lg ladda-button" id="actionBtn" data-action="${action}" data-ctx="${ctx }" data-id="${id }"></button>
				    </div>
			  	</div>
			</form>
		</div>
	</div>
</div>

<jsp:include page="../footer.jsp" />
<jsp:include page="../script.jsp" />
<script type="text/javascript" src="${ctx }/public/wholesale/system/user.js?ver=2014921127"></script>
<jsp:include page="../footer-end.jsp" />