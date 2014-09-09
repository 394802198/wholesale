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
					<h3 class="panel-title" id="heading">Change Password</h3>
				</div>
			</div>
		</div>
		<div class="panel-body">
			<form class="form-horizontal">
			  	<div class="form-group">
			    	<label for="old_password" class="col-md-2 control-label">Old Password <span class="text-danger">(*)</span></label>
				    <div class="col-md-3">
				      	<input type="password" class="form-control" id="old_password" placeholder="" data-error-field>
				    </div>
			  	</div>
			  	<div class="form-group">
			    	<label for="login_password" class="col-md-2 control-label">New Password <span class="text-danger">(*)</span></label>
				    <div class="col-md-3">
				      	<input type="password" class="form-control" id="login_password" placeholder="" data-error-field>
				    </div>
			  	</div>
			  	<div class="form-group">
			    	<label for="confirm_password" class="col-md-2 control-label">Confirm Password <span class="text-danger">(*)</span></label>
				    <div class="col-md-3">
				      	<input type="password" class="form-control" id="confirm_password" placeholder="" data-error-field>
				    </div>
			  	</div>
			  	<div class="form-group">
				    <div class="col-md-3 col-md-offset-2">
				    	<button type="submit" data-style="zoom-in" class="btn btn-info btn-lg ladda-button" id="actionBtn">Change</button>
				    </div>
			  	</div>
			</form>
		</div>
	</div>
</div>

<jsp:include page="../footer.jsp" />
<jsp:include page="../script.jsp" />
<script type="text/javascript">
(function($){
	$('#actionBtn').click(function(){
		var w = {
			old_password: $('#old_password').val()
			, login_password: $('#login_password').val()
			, confirm_password: $('#confirm_password').val()
		};
		
		var l = Ladda.create(this); l.start();
		$.post('${ctx}/system/user/change-password', w, function(json){
			if (!$.jsonValidation(json, 'right')) window.location.href = '${ctx}' + json.url;
		}, 'json').always(function () { l.stop(); });
		
	});
})(jQuery);
</script>
<jsp:include page="../footer-end.jsp" />