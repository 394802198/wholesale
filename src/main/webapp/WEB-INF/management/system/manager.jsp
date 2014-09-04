<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<jsp:include page="../header.jsp" />
<jsp:include page="../alert.jsp" />

<div class="container">
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h4 class="panel-title">${panelheading }</h4>
				</div>
				<div class="panel-body">
					<form:form modelAttribute="manager" method="post" action="${ctx}${action }" class="form-horizontal">
						<div class="form-group">
							<label for="login_name" class="control-label col-md-4">Login Name</label>
							<div class="col-md-3">
								<form:input path="login_name" class="form-control" placeholder="Login Name" />
							</div>
							<p class="help-block">
								<form:errors path="login_name" cssErrorClass="error"/>
							</p>
						</div>
						<div class="form-group">
							<label for="password" class="control-label col-md-4">Password</label>
							<div class="col-md-3">
								<form:input path="password" class="form-control" placeholder="Password" />
							</div>
							<p class="help-block">
								<form:errors path="password" cssErrorClass="error"/>
							</p>
						</div>
						<div class="form-group">
							<label for="username" class="control-label col-md-4">User Name</label>
							<div class="col-md-3">
								<form:input path="username" class="form-control" placeholder="User Name" />
							</div>
							<p class="help-block">
								<form:errors path="username" cssErrorClass="error"/>
							</p>
						</div>
						<div class="form-group">
							<label for="role" class="control-label col-md-4">Manager Permission</label>
							<div class="col-md-3">
								<form:select path="role" class="form-control" data-name="manager_permission_selector">
									<form:option value="">--- Please assign a role ---</form:option>
									<form:option value="administrator">Administrator</form:option>
									<form:option value="system-developer">System Developer</form:option>
								</form:select>
							</div>
						</div>
						<div class="form-group">
							<label for="mobile" class="control-label col-md-4">Mobile</label>
							<div class="col-md-3">
								<form:input path="mobile" class="form-control" placeholder="Mobile" />
							</div>
							<p class="help-block">
								<form:errors path="mobile" cssErrorClass="error"/>
							</p>
						</div>
						<div class="form-group">
							<label for="email" class="control-label col-md-4">Email</label>
							<div class="col-md-3">
								<form:input path="email" class="form-control" placeholder="Email" />
							</div>
							<p class="help-block">
								<form:errors path="email" cssErrorClass="error"/>
							</p>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4">Manager Status</label>
							<div class="col-md-6">
								<p class="form-control-static">
									<label><form:radiobutton path="status" value="active" checked="checked"/>Active</label>
									<label><form:radiobutton path="status" value="disabled"/>Disabled</label>
									<label><form:radiobutton path="status" value="pending"/>Pending</label>
								</p>
							</div>
						</div>
						<hr/>
						<h4>Manager Authentication</h4>
						<div class="form-group" data-module="administrator">
							<div class="col-md-2" data-module="product">
								<ul class="list-unstyled">
									<li>
										<h3>Product</h3>
									</li>
									<li>
										<label> 
											<input type="checkbox" data-name="checkbox_all" data-type="checkbox_plan" /> All
										</label>
									</li>
									<li>
										<label> 
											<form:checkbox path="authArray" value="material/view" data-type="checkbox_plan" /> View Material
										</label>
									</li>
								</ul>
							</div>
						</div>
						<hr/>
						<div class="form-group">
							<div class="col-md-offset-5">
								<button type="submit" class="btn btn-primary">${wholesaler.id!=null ? 'Update' : 'Save'}</button>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../footer.jsp" />
<jsp:include page="../script.jsp" />
<script type="text/javascript" src="${ctx}/public/bootstrap3/js/icheck.min.js"></script>
<script type="text/javascript">
(function($){
	
	$('input[data-name="checkbox_all"]').on('ifChecked',function(){
		var type = $(this).attr("data-type");
		$('input[data-type='+type+']').iCheck("check");
	});
	$('input[data-name="checkbox_all"]').on('ifUnchecked',function(){
		var type = $(this).attr("data-type");
		$('input[data-type='+type+']').iCheck("uncheck");
	});
	
	$(':radio,:checkbox').iCheck({
		checkboxClass : 'icheckbox_square-blue',
		radioClass : 'iradio_square-blue'
	});
	
	$('select[data-name="manager_permission_selector"]').change(function(){
		var permission = $(this).find('option:selected').val();
		$('div[data-module="administrator"]').find('input').iCheck("uncheck");
		
		if("administrator"==permission){	// Administrator
			
			$('div[data-module="administrator"]').find('input').iCheck("check");
			
 		} else if("system-developer"==permission){	// System Developer
			
			$('div[data-module="administrator"]').find('input').iCheck("check");
			
		}
	});
	
	$(':radio[data-name="company_type"]').on('ifChecked',function(){
		if($(this).attr('data-type')=='new'){
			$(':radio[data-type="existed"]').iCheck("uncheck");
			$('select[data-type="company_select"]').css('display','none');
			$('select[data-type="company_select"]').attr('disabled','disabled');
			$('input[data-type="company_input"]').removeAttr('disabled');
			$('input[data-type="company_input"]').css('display','');
		} else {
			$(':radio[data-type="new"]').iCheck("uncheck");
			$('input[data-type="company_input"]').attr('disabled','true');
			$('input[data-type="company_input"]').css('display','none');
			$('select[data-type="company_select"]').removeAttr('disabled');
			$('select[data-type="company_select"]').css('display','');
		}
	});
	
})(jQuery);
</script>
<jsp:include page="../footer-end.jsp" />