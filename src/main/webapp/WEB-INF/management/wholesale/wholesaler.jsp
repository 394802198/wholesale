<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<jsp:include page="../header.jsp" />
<jsp:include page="../alert.jsp" />

<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h4 class="panel-title">${panelheading }</h4>
				</div>
				<div class="panel-body">
					<form:form modelAttribute="wholesaler" class="form-horizontal">
						<div class="form-group">
							<label for="company_name" class="control-label col-md-4">Company Name</label>
							<div class="col-md-3">
								<form:input path="company_name" name="company_name" class="form-control" placeholder="Company Name"/>
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="control-label col-md-4">Name</label>
							<div class="col-md-3">
								<form:input path="name" name="name" class="form-control" placeholder="Name" />
							</div>
						</div>
						<div class="form-group">
							<label for="login_name" class="control-label col-md-4">Login Name</label>
							<div class="col-md-3">
								<form:input path="login_name" name="login_name" class="form-control" placeholder="Login Name" />
							</div>
						</div>
						<div class="form-group">
							<label for="login_password" class="control-label col-md-4">Login Password</label>
							<div class="col-md-3">
								<form:input path="login_password" name="login_password" class="form-control" placeholder="Login Password" />
							</div>
						</div>
						<div class="form-group">
							<label for="email" class="control-label col-md-4">Email</label>
							<div class="col-md-3">
								<form:input path="email" name="email" class="form-control" placeholder="Email" />
							</div>
						</div>
						<div class="form-group">
							<label for="cellphone" class="control-label col-md-4">Cellphone</label>
							<div class="col-md-3">
								<form:input path="cellphone" name="cellphone" class="form-control" placeholder="Cellphone" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-4">Wholesaler Status</label>
							<div class="col-md-6">
								<p class="form-control-static">
									<label><form:radiobutton path="status" name="status" value="active" checked="checked"/>Active</label>
									<label><form:radiobutton path="status" name="status" value="disabled"/>Disabled</label>
									<label><form:radiobutton path="status" name="status" value="pending"/>Pending</label>
								</p>
							</div>
						</div>
						<hr/><h3><span class="text-info">Materials</span><span class="pull-right">ALL MATERIALS: <label><input type="radio" name="material-shelf-radio" data-shelf-status="on-shelf" />On Shelf</label>&nbsp;&nbsp;&nbsp;&nbsp;<label><input type="radio" name="material-shelf-radio" data-shelf-status="off-shelf" />Off Shelf</label></span></h3><hr/>
						<div class="form-group">
							<table class="table table-hover table-bordered table-condensed">
								<tr>
									<td colspan="4" class="bg-info">
										<label>
											<input type="checkbox" data-name="checkbox_all" data-type="checkbox_material" /> All
										</label>
									</td>
								</tr>
								<!-- Material Category -->
							<c:forEach var="mc" items="${mcs}">
								<tr>
									<td class="bg-info">
										<strong>
											<label>
												<input type="checkbox" data-name="checkbox_all_mc_material" data-category-type="checkbox_${mc}" data-type="checkbox_material" /> ${mc}
											</label>
										</strong>
									</td>
									<td class="bg-info"><label>Description</label></td>
									<td class="bg-info"><label>Price</label></td>
									<td class="bg-info"><label>Status</label></td>
								</tr>
								<!-- Material -->
								<c:forEach var="m" items="${ms}">
									<c:if test="${m.material_category==mc}">
									<tr>
										<td>
										<label>
											<form:checkbox id="${m.id}" path="midArr" data-material="true" value="${m.id}" data-m-suitable="${m.suitable}" data-m-name="${m.name}" data-material-group="${m.material_group}" data-material-type="${m.material_type}" data-material-category="${m.material_category}" data-name="checkbox_ms" data-category-type="checkbox_${mc}" data-type="checkbox_material" /> ${wholesaler.mwMaps[m.id].name!=null ? wholesaler.mwMaps[m.id].name : m.name}
										</label>
										</td>
										<td><input class="form-control" data-name="m_description_${m.id}" value="${wholesaler.mwMaps[m.id].description!=null  ? wholesaler.mwMaps[m.id].description : m.description}"/></td>
										<td><input class="form-control" data-name="m_wholesale_price_${m.id}" value="${wholesaler.mwMaps[m.id].wholesale_price!=null ? wholesaler.mwMaps[m.id].wholesale_price : m.wholesale_price}"/></td>
										<td>
											<select class="form-control" data-name="m_status_${m.id}">
												<option data-name="material-on-shelf" value="on-shelf" ${wholesaler.mwMaps[m.id].status=='on-shelf' ? 'selected="selected"' : ''}>On Shelf</option>
												<option data-name="material-off-shelf" value="off-shelf" ${wholesaler.id!=null && wholesaler.mwMaps[m.id].status!='on-shelf' ? 'selected="selected"' : ''}>Off Shelf</option>
											</select>
										</td>
									</tr>
									</c:if>
								</c:forEach>
							</c:forEach>
							</table>
						</div>
						<hr/><h3><span class="text-info">Combos</span><span class="pull-right">ALL COMBOS: <label><input type="radio" name="combo-shelf-radio" data-shelf-status="on-shelf" />On Shelf</label>&nbsp;&nbsp;&nbsp;&nbsp;<label><input type="radio" name="combo-shelf-radio" data-shelf-status="off-shelf" />Off Shelf</label></span></h3><hr/>
						<div class="form-group">
							<table class="table table-hover table-bordered table-condensed">
								<tr>
									<td class="bg-info">
										<label>
											<input type="checkbox" data-name="checkbox_all" data-type="checkbox_combo" /> All
										</label>
									</td>
									<td class="bg-info" colspan="2"><label>Description</label></td>
									<td class="bg-info"><label>Status</label></td>
								</tr>
								<c:forEach var="c" items="${cs}">
								<input type="hidden" data-name="c_material_ids_${c.id}" value="${c.material_ids}"/>
								<tr>
									<td>
									<label>
										<form:checkbox id="${c.id}" path="cidArr" data-combo="true" value="${c.id}" data-c-material_ids="${wholesaler.cwMaps[c.id].material_ids!=null ? wholesaler.cwMaps[c.id].material_ids : c.material_ids}" data-c-name="${wholesaler.cwMaps[c.id].name!=null ? wholesaler.cwMaps[c.id].name : c.name}" data-name="checkbox_cs" data-type="checkbox_combo" />${wholesaler.cwMaps[c.id].name!=null ? wholesaler.cwMaps[c.id].name : c.name}
									</label>
									</td>
									<td colspan="2"><input class="form-control" data-name="c_description_${c.id}" value="${wholesaler.cwMaps[c.id].description!=null ? wholesaler.cwMaps[c.id].description : c.description}"/></td>
									<td>
										<select class="form-control" data-name="c_status_${c.id}">
											<option data-name="combo-on-shelf" value="on-shelf" ${wholesaler.cwMaps[c.id].status=='on-shelf' ? 'selected="selected"' : ''}>On Shelf</option>
											<option data-name="combo-off-shelf" value="off-shelf" ${wholesaler.id!=null && wholesaler.cwMaps[c.id].status!='on-shelf' ? 'selected="selected"' : ''}>Off Shelf</option>
										</select>
									</td>
								</tr>
								</c:forEach>
							</table>
						</div>
						<hr/>
						<div class="form-group">
							<div class="col-md-offset-5">
								<a data-name="submit_material" class="btn btn-primary">${wholesaler.id!=null ? 'Update' : 'Save'}</a>
							</div>
						</div>
						<hr/>
						<div class="form-group">
							<label for="memo" class="control-label col-md-4">Memo</label>
							<div class="col-md-6">
								<form:textarea path="memo" name="memo" class="form-control" rows="6"></form:textarea>
							</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Create Wholesaler Modal -->
<div class="modal fade" id="createUpdateWholesalerModal" tabindex="-1" role="dialog" aria-labelledby="createUpdateWholesalerModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				<div class="row">
					<div class="panel panel-info">
						<div class="panel-heading">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h3 class="panel-title">
								<strong>${wholesaler.id!=null ? 'UPDATE' : 'CREATE'} WHOLESALER</strong>
							</h3>
						</div>
						<div class="panel-body">
							<p>
								${wholesaler.id!=null ? 'Update' : 'Create'} this wholesaler?
							</p>
						</div>
						<div class="panel-footer">
							<div class="form-group">
								<button data-name="createUpdateWholesalerBtn" type="button" class="btn btn-xs btn-info col-md-2 col-md-offset-5" data-dismiss="modal">${wholesaler.id!=null ? 'Update' : 'Create'}</button>
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
<script type="text/javascript">
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
	
	$('input[data-name="checkbox_all_mc_material"]').on('ifChecked',function(){
		var type = $(this).attr("data-category-type");
		$('input[data-category-type='+type+']').iCheck("check");
	});
	$('input[data-name="checkbox_all_mc_material"]').on('ifUnchecked',function(){
		var type = $(this).attr("data-category-type");
		$('input[data-category-type='+type+']').iCheck("uncheck");
	});
	
	$('input[name="combo-shelf-radio"]').on('ifChecked',function(){
		var type = $(this).attr('data-shelf-status');
		if(type=='on-shelf'){
			$('option[data-name="combo-on-shelf"]').attr('selected', true);
		} else {
			$('option[data-name="combo-off-shelf"]').attr('selected', true);
		}
	});
	
	$('input[name="material-shelf-radio"]').on('ifChecked',function(){
		var type = $(this).attr('data-shelf-status');
		if(type=='on-shelf'){
			$('option[data-name="material-on-shelf"]').attr('selected', true);
		} else {
			$('option[data-name="material-off-shelf"]').attr('selected', true);
		}
	});
	
	$('a[data-name="submit_material"]').click(function(){
		
		$btn = $(this); $btn.button('loading');
		
		$('#createUpdateWholesalerModal').modal('show');
		
	});
	
	$('#createUpdateWholesalerModal').on('hidden.bs.modal', function (e) {
		$('a[data-name="submit_material"]').button('reset');
	});
	
	$('button[data-name="createUpdateWholesalerBtn"]').click(function(){
		
		var wholesaler = {
			id: '${wholesaler.id}'+'',
			company_name: $('input[name="company_name"]').val(),
			name: $('input[name="name"]').val(),
			login_name: $('input[name="login_name"]').val(),
			login_password: $('input[name="login_password"]').val(),
			email: $('input[name="email"]').val(),
			cellphone: $('input[name="cellphone"]').val(),
			status: $('input[name="status"]:checked').val(),
			memo: $('textarea[name="memo"]').val(),
			company_id: '${wholesaler.company_id}',
			mws: [],
			cws: []
		};
		
		$('input[data-material="true"]:checked').each(function(){
			var m = $(this);
			var mData = {
				material_id:this.id,
				suitable: m.attr('data-m-suitable'),
				name: m.attr('data-m-name'),
				material_group: m.attr('data-material-group'),
				material_type: m.attr('data-material-type'),
				material_category: m.attr('data-material-category'),
				description: $('input[data-name="m_description_'+this.id+'"]').val(),
				wholesale_price: Number($('input[data-name="m_wholesale_price_'+this.id+'"]').val()),
				status: $('select[data-name="m_status_'+this.id+'"]').val()
			};
			
			wholesaler.mws.push(mData);
		});
		
		$('input[data-combo="true"]:checked').each(function(){
			var c = $(this);
			var cData = {
				combo_id:this.id,
				name: c.attr('data-c-name'),
				material_ids: c.attr('data-c-material_ids'),
				description: $('input[data-name="c_description_'+this.id+'"]').val(),
				status: $('select[data-name="c_status_'+this.id+'"]').val()
			};
			
			wholesaler.cws.push(cData);
		});
		
		console.log(wholesaler);
		
		var url;
		if($(this).text()=='Create'){
			url = '${ctx}/management/wholesale/wholesaler/create';
		} else {
			url = '${ctx}/management/wholesale/wholesaler/edit';
		}

		$.ajax({
			type: 'post'
			, contentType:'application/json;charset=UTF-8'         
	   		, url: url
		   	, data: JSON.stringify(wholesaler)
		   	, dataType: 'json'
		   	, success: function(json){
		   		$.jsonValidation(json, 'right');
		   	}
		});
		
	});
	
})(jQuery);
</script>
<jsp:include page="../footer-end.jsp" />