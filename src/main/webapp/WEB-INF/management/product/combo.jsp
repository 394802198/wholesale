<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<jsp:include page="../header.jsp" />
<jsp:include page="../alert.jsp" />

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h4 class="panel-title">Combo Update</h4>
				</div>
				<div class="panel-body">
					<form:form modelAttribute="c" class="form-horizontal">
						<h4 class="text-success">Essential Detail</h4>
						<hr />
						<div class="form-group">
							<label for="id" class="control-label col-md-2">Id</label>
							<div class="col-md-2">
								<p class="form-control-static">${c.id}</p>
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="control-label col-md-2">Name</label>
							<div class="col-md-4">
								<input type="text" name="name" value="${c.name}" class="form-control input-sm"/>
							</div>
						</div>
						<div class="form-group">
							<label for="description" class="control-label col-md-2">Description</label>
							<div class="col-md-6">
								<textarea name="description" rows="6" class="form-control">${c.description}</textarea>
							</div>
						</div>
						<hr/>
						<div class="form-group">
							<c:forEach var="mc" items="${mcs}">
								<div class="col-md-2" data-module="${mc}">
									<ul class="list-unstyled">
										<li>
											<h3>${mc}</h3>
										</li>
										<li>
											<label> 
												<input type="checkbox" data-name="checkbox_all" data-type="checkbox_${mc}" /> All
											</label>
										</li>
										<c:forEach var="m" items="${ms}">
											<c:if test="${m.material_category==mc}">
												<li>
													<label>
														<form:checkbox path="midArr" value="${m.id}" data-name="checkbox_ms" data-type="checkbox_${mc}" /> ${m.name}
													</label>
												</li>
											</c:if>
										</c:forEach>
									</ul>
								</div>
							</c:forEach>
						</div>
						
						<!-- button -->
						<div class="form-group">
							<div class="col-md-4"></div>
							<div class="col-md-3">
								<a href="javascript:void(0);" class="btn btn-primary btn-xs btn-block" id="updateComboBtn">
									Update Material
								</a>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>


<!-- Update Combo Modal -->
<div class="modal fade" id="updateComboModal" tabindex="-1" role="dialog" aria-labelledby="updateComboModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				<div class="row">
					<div class="panel panel-info">
						<div class="panel-heading">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h3 class="panel-title">
								<strong>UPDATE CURRENT COMBO</strong>
							</h3>
						</div>
						<div class="panel-body">
							<p>
								Update Current Combo?
							</p>
						</div>
						<div class="panel-footer">
							<div class="form-group">
								<button id="update_combo_btn" type="button" class="btn btn-xs btn-primary col-md-2 col-md-offset-5" data-dismiss="modal">Update</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="../footer.jsp" />
<jsp:include page="../script.jsp" />
<script type="text/javascript" src="${ctx}/public/bootstrap3/js/bootstrap-select.min.js"></script>
<script type="text/javascript" src="${ctx}/public/bootstrap3/js/icheck.min.js"></script>
<script>
(function($){
	
	$(':radio,:checkbox').iCheck({
		checkboxClass : 'icheckbox_square-blue',
		radioClass : 'iradio_square-blue'
	});
	
	$('input[data-name="checkbox_all"]').on('ifChecked',function(){
		var type = $(this).attr("data-type");
		$('input[data-type='+type+']').iCheck("check");
	});
	$('input[data-name="checkbox_all"]').on('ifUnchecked',function(){
		var type = $(this).attr("data-type");
		$('input[data-type='+type+']').iCheck("uncheck");
	});
	
	// BEGIN CreateMaterialSubmit
	$('#updateComboBtn').click(function(){
		$('#updateComboModal').modal('show');
	});
	$('#update_combo_btn').click(function(){
		var name = $('input[name="name"]').val();
		var description = $('textarea[name="description"]').val();
		var material_ids = '';
		$('input[data-name="checkbox_ms"]:checked').each(function(){
			material_ids += $(this).val()+',';
		});
		material_ids = material_ids.substring(0, material_ids.length-1);
		
		var combo = {
			'id':${c.id},
			'name':name,
			'description':description,
			'material_ids':material_ids
		};
		
		$.post('${ctx}/management/product/combo/update', combo, function(json){
	   		$.jsonValidation(json, 'right');
		});
		
	});
	// END CreateMaterialSubmit
	
})(jQuery);
</script>
