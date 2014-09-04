<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<jsp:include page="header.jsp" />
<jsp:include page="alert.jsp" />

<div class="container">
	<div class="row">
		<div class="col-md-4 col-md-offset-4">
			<div class="panel panel-info">
				<div class="panel-heading">Wholesale Portal System Sign-in</div>
				<div class="panel-body">
					<form id="loginForm">
						<div class="form-group">
							<label for="login_name">Account Name</label>
							<input type="text" id="login_name" class="form-control" placeholder="Account Name" data-error-field/>
						</div>
						<div class="form-group">
							<label for="login_password">Password</label>
							<input type="password" id="login_password" class="form-control" data-error-field/>
						</div>
						<button type="submit" data-style="zoom-in" class="btn btn-info btn-lg btn-block ladda-button" id="signin-btn">Sign in</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="footer.jsp" />
<jsp:include page="script.jsp" />
<script type="text/javascript">
(function($){
	
	$('#signin-btn').on("click", function(e){
		e.preventDefault();
		var data = {
			login_name: $('#login_name').val()
			, login_password: $('#login_password').val()
		};
		var l = Ladda.create(this); l.start();
		$.post('${ctx}/sign-in', data, function(json){
			if (!$.jsonValidation(json, 'right')) window.location.href = '${ctx}' + json.url;
		}, 'json').always(function () { l.stop(); });
	});
	
})(jQuery);
</script>
<jsp:include page="footer-end.jsp" />