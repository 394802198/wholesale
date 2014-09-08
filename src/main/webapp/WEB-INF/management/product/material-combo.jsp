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
thead th {text-align:center;}
tbody td {text-align:center;}
.input-xs{
	height:26px;
}
.form-group{
	margin-bottom:6px;
	padding-bottom:0px;
}
.btn {
	padding: 0 12px;
}
.bootstrap-select.btn-group, .bootstrap-select.btn-group[class*="span"] {
	margin-bottom: 0;
}
.align-right{
	text-align:right;
}
</style>


<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h4 class="panel-title">Material Operations
						<div class="pull-right" id="combo_selector" style="display:none;">
							<select id="combo_select_operations" class="selectpicker" multiple title="Combo Operation">
							    <optgroup label="Essential Operations">
							      	<option value="delete" data-type="combo-delete">Delete Selected Combo</option>
							    </optgroup>
							</select>
						</div>
						<div class="pull-right" id="material_selector">
							<select id="material_filter_operations" class="selectpicker" multiple title="Material Filter">
							    <optgroup label="Filtering Material Group">
							    	<option value="adsl" data-type="material-group">ADSL</option>
							    	<option value="vdsl" data-type="material-group">VDSL</option>
							    	<option value="ufb" data-type="material-group">UFB</option>
							    	<option value="pstn" data-type="material-group">PSTN</option>
							    	<option value="other" data-type="material-group">OTHER</option>
							    	<option value="hardware" data-type="material-group">HARDWARE</option>
							    </optgroup>
							    <optgroup label="Filtering Material Category">
							      	<option value="broadband-type" data-type="material-category">BROADBAND TYPE</option>
							      	<option value="phone-line" data-type="material-category">PHONE LINE</option>
							      	<option value="transition" data-type="material-category">TRANSITION</option>
							      	<option value="new-connection" data-type="material-category">NEW CONNECTION</option>
							      	<option value="installation" data-type="material-category">INSTALLATION</option>
							      	<option value="items" data-type="material-category">ITEMS</option>
							      	<option value="wire-vdsl" data-type="material-category">WIRE VDSL</option>
							      	<option value="hardware-router" data-type="material-category">HARDWARE ROUTER</option>
							    </optgroup>
							</select>
							<select id="material_select_operations" class="selectpicker" multiple title="Material Operation">
							    <optgroup label="Essential Operations">
							      	<option value="delete" data-type="material-delete">Delete Selected Material</option>
							    </optgroup>
							    <optgroup label="Operations">
							    	<option data-type="material-combine">Combine Selected Material</option>
							    </optgroup>
							</select>
						</div>
					</h4>
				</div>
				<div class="panel-body">
                    <ul class="list-unstyled">
                    	<li>
                    		<span class="glyphicon glyphicon-plus"></span>
                    		<a href="javascript:void(0);" data-name="create_material_group_type" data-type="group">Create Group</a>
                    		&nbsp;&nbsp;&nbsp;&nbsp;
                    		<span class="glyphicon glyphicon-plus"></span>
                    		<a href="javascript:void(0);" data-name="create_material_group_type" data-type="type">Create Type</a>
                    		&nbsp;&nbsp;&nbsp;&nbsp;
                    		<span class="glyphicon glyphicon-plus"></span>
                    		<a href="javascript:void(0);" data-name="create_material_group_type" data-type="category">Create Category</a>
                    	</li>
                    </ul>
				</div>
			</div>
			
			<!-- Nav tabs -->
			<ul class="nav nav-tabs">
				<li class="active" data-name="tab-li" data-type="material"><a href="#material_view" data-toggle="tab"><strong>Material View</strong></a></li>
				<li data-name="tab-li" data-type="combo"><a href="#combo_view" data-toggle="tab"><strong>Combo View</strong></a></li>
			</ul>

			<!-- Tab panel -->
			<div class="tab-content panel panel-default">
				<div class="panel-body tab-pane fade in active" id="material_view" ></div>
				<div class="panel-body tab-pane fade" id="combo_view"></div>
			</div>
		</div>
	</div>
</div>

<form class="form-horizontal" id="group_type_form">
	<input type="hidden" name="group_or_type"/>
	<div class="modal fade" id="createGroupTypeModal" tabindex="-1" role="dialog" aria-labelledby="createGroupTypeModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<div class="row">
						<div class="panel panel-info">
							<div class="panel-heading">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								<h3 class="panel-title">
									<strong>CREATE A MATERIAL <span id="createGroupTypeTitle"></span></strong>
								</h3>
							</div>
							<div class="panel-body">
								<div class="form-group" id="group_name_select_div" style="display:none;">
									<div class="col-md-5">
										<label for="group_name_select" class="control-label pull-right">Group Name&nbsp;&nbsp;</label>
									</div>
									<div class="col-md-4">
										<select id="group_name_select" class="form-control input-sm" >
										</select>
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-5">
										<label for="material_group" id="material_group_or_type_label" class="control-label pull-right"></label>
									</div>
									<div class="col-md-4">
										<input type="text" id="group_or_type_name" class="form-control input-sm" />
									</div>
								</div>
							</div>
							<div class="panel-footer">
								<div class="form-group">
									<button id="create_group_type_btn" type="button" class="btn btn-xs btn-primary col-md-2 col-md-offset-5" data-dismiss="modal">Create</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form>

<form class="form-horizontal">
	<div class="modal fade" id="comboDeleteModal" tabindex="-1" role="dialog" aria-labelledby="comboDeleteModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<div class="panel panel-info">
						<div class="panel-heading">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h3 class="panel-title">
								<strong>DELETE SELECTED COMBO(S)</strong>
							</h3>
						</div>
						<div class="panel-body">
							<div class="form-group">
								<p class="form-control-static col-md-12">
									Delete Selected Combo(s)?
								</p>
							</div>
						</div>
						<div class="panel-footer">
							<div class="form-group">
								<button id="combo_delete_btn" type="button" class="btn btn-xs btn-danger col-md-2 col-md-offset-5" data-dismiss="modal">Delete</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form>

<form class="form-horizontal">
	<div class="modal fade" id="materialDeleteModal" tabindex="-1" role="dialog" aria-labelledby="materialDeleteModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<div class="panel panel-info">
						<div class="panel-heading">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h3 class="panel-title">
								<strong>DELETE SELECTED MATERIAL(S)</strong>
							</h3>
						</div>
						<div class="panel-body">
							<div class="form-group">
								<p class="form-control-static col-md-12">
									Delete Selected Material(s)?
								</p>
							</div>
						</div>
						<div class="panel-footer">
							<div class="form-group">
								<button id="material_delete_btn" type="button" class="btn btn-xs btn-danger col-md-2 col-md-offset-5" data-dismiss="modal">Delete</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form>

<form class="form-horizontal">
	<div class="modal fade" id="materialCombineModal" tabindex="-1" role="dialog" aria-labelledby="materialCombineModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-body">
					<div class="panel panel-info">
						<div class="panel-heading">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h3 class="panel-title">
								<strong>Combine Materials As a Combo</strong>
							</h3>
						</div>
						<div class="panel-body">
							<div class="form-group">
								<div class="col-md-5">
									<label for="combo_name" class="control-label pull-right">Combo Name&nbsp;&nbsp;</label>
								</div>
								<div class="col-md-4">
									<input type="text" id="modal_combo_name" class="form-control input-sm" />
								</div>
							</div>
						</div>
						<div class="panel-footer">
							<div class="form-group">
								<button id="material_combine_btn" type="button" class="btn btn-xs btn-primary col-md-2 col-md-offset-5" data-dismiss="modal">Create</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</form>

<!-- Material View Template -->
<script type="text/html" id="material_view_table_tmpl">
<jsp:include page="material-view-page.html" />
</script>
<!-- Combo View Template -->
<script type="text/html" id="combo_view_table_tmpl">
<jsp:include page="combo-view-page.html" />
</script>

<jsp:include page="../footer.jsp" />
<jsp:include page="../script.jsp" />
<script type="text/javascript" src="${ctx}/public/bootstrap3/js/bootstrap-select.min.js"></script>
<script type="text/javascript" src="${ctx}/public/bootstrap3/js/jTmpl.js"></script>
<script type="text/javascript">
(function($){
	
	// BEGIN Material Group & Type
	$('a[data-name="create_material_group_type"]').click(function(){
		
		if($(this).attr('data-type')=='group'){
			$('#material_group_or_type_label').html('Group Name&nbsp;&nbsp;');
			$('input[name="group_or_type"]').val('group');
			$('#createGroupTypeTitle').html('GROUP');
			$('#group_name_select_div').css('display','none');
		} else if($(this).attr('data-type')=='type'){

			$.get('${ctx}/management/product/material/groups/show', function(json){
				
				var groups = json.models;
				var group_name_select = $('#group_name_select');
				group_name_select.empty();
				
				for(var i=0; i<groups.length; i++){
					group_name_select.append('<option value="'+groups[i].id+'">'+groups[i].group_name+'</option>');
				}
				
			});
			
			$('#material_group_or_type_label').html('Type Name&nbsp;&nbsp;');
			$('input[name="group_or_type"]').val('type');
			$('#createGroupTypeTitle').html('TYPE');
			$('#group_name_select_div').css('display','');
		} else {
			$('#material_group_or_type_label').html('Category Name&nbsp;&nbsp;');
			$('input[name="group_or_type"]').val('category');
			$('#createGroupTypeTitle').html('CATEGORY');
			$('#group_name_select_div').css('display','none');
		}
		
		$('#createGroupTypeModal').modal('show');
		
	});
	
	$('#create_group_type_btn').click(function(){
		
		var group_or_type = $('input[name="group_or_type"]');
		var group_or_type_name = $('#group_or_type_name').val();
		
		var data = {
			'group_or_type':group_or_type.val(),
			'group_or_type_name':group_or_type_name,
			'group_id':$('#group_name_select option:selected').val()+''
		};
		
		$('#group_or_type_name').val('');
		
		$.post('${ctx}/management/product/material/group-type/create', data, function(json){
	   		$.jsonValidation(json, 'right');
		});
	});
	// END Material Group & Type

	// BEGIN Material Operations
	$('#material_select_operations').change(function(){
		var $this = $(this);
		var type = $this.find('option:selected').attr('data-type');
		if(type=='material-delete'){
			$('#materialDeleteModal').modal('show');
		} else if(type=='material-combine'){
			$(this).selectpicker('refresh');
			$('#materialCombineModal').modal('show');
		}
		$(this).selectpicker('deselectAll');
	});
	
	
	$('#material_delete_btn').click(function(){
		var material_ids = '';
		$('input[name="checkbox_ms"]:checked').each(function(){
			material_ids += $(this).val()+',';
		});
		material_ids = material_ids.substring(0, material_ids.length-1);
		var data = {
			material_ids:material_ids
		};
		$.post('${ctx}/management/product/material/delete', data, function(json){
	   		$.jsonValidation(json, 'right');
	   		$.getMaterialPage(1);
		});
	});
	
	$('#material_combine_btn').click(function(){
		var material_ids = '';
		$('input[name="checkbox_ms"]:checked').each(function(){
			material_ids += $(this).val()+',';
		});
		material_ids = material_ids.substring(0, material_ids.length-1);
		var combo = {
			name:$('#modal_combo_name').val(),
			material_ids:material_ids
		};
		$.ajax({
			type: 'post'
			, contentType:'application/json;charset=UTF-8'         
	   		, url: '${ctx}/management/product/material/material-combine'
		   	, data: JSON.stringify(combo)
		   	, dataType: 'json'
		   	, success: function(json){
		   		$.jsonValidation(json, 'right');
				$.getComboPage(1);
		   	}
		});
	});
	// END Material Operations
	
	// BEGIN Material Filter
	$('#material_filter_operations').change(function(){
		var $this = $(this);
		var material = {				
			material_group: $this.find('option[data-type="material-group"]:selected').val() || null
			, material_category: $this.find('option[data-type="material-category"]:selected').val() || null
		};
		
		$.get('${ctx}/management/product/material/view/filter', material, function(){
			$.getMaterialPage(1);
		});
	});
	
	$('#material_filter_operations').find('option').each(function(){
		if (this.value == '${materialFilter.material_group}' 
			|| this.value == '${materialFilter.material_category}') {
			$(this).attr("selected", "selected");
			$('.selectpicker').selectpicker('refresh');
		}
	});
	// END Material Filter

	// BEGIN Combo Operations
	$('#combo_select_operations').change(function(){
		var $this = $(this);
		var type = $this.find('option:selected').attr('data-type');
		if(type=='combo-delete'){
			$('#comboDeleteModal').modal('show');
		}
		$(this).selectpicker('deselectAll');
	});
	
	$('#combo_delete_btn').click(function(){
		var combo_ids = '';
		$('input[name="checkbox_cs"]:checked').each(function(){
			combo_ids += $(this).val()+',';
		});
		combo_ids = combo_ids.substring(0, combo_ids.length-1);
		var data = {
			combo_ids:combo_ids
		};
		$.post('${ctx}/management/product/combo/delete', data, function(json){
	   		$.jsonValidation(json, 'right');
	   		$.getComboPage(1);
		});
	});
	// END Combo Operations
	
	// BEGIN Material||Combo Tab
	$('li[data-name="tab-li"]').click(function(){
		if($(this).attr('data-type')=='material'){
			$('#material_selector').css('display', '');
			$('#combo_selector').css('display', 'none');
		} else {
			$('#combo_selector').css('display', '');
			$('#material_selector').css('display', 'none');
		}
	});
	// END Material||Combo Tab
	
	// BEGIN Material Page
	$.getMaterialPage = function(pageNo) {
		
		$.get('${ctx}/management/product/material/view/'+pageNo, function(page){
			page.ctx = '${ctx}';
	   		var $table = $('#material_view');
			$table.html(tmpl('material_view_table_tmpl', page));
			$table.find('tfoot a').click(function(){
				$.getMaterialPage($(this).attr('data-pageNo'));
			});
			
			$('#checkbox_ms_top').click(function(){
				var b = $(this).prop("checked");
				if (b) $('input[name="checkbox_ms"]').prop("checked", true);
				else $('input[name="checkbox_ms"]').prop("checked", false);
			});
			
			$('.selectpicker').selectpicker();

			
		}, "json");
	}
	// END Material Page
	
	// BEGIN Combo Page
	$.getComboPage = function(pageNo) {
		
		$.get('${ctx}/management/product/combo/view/'+pageNo, function(page){
			page.ctx = '${ctx}';
			page.ms = [];
			<c:forEach var="m" items="${ms}">
				var m = {};
				m.id = '${m.id}';
				m.name = '${m.name}';
				page.ms.push(m);
			</c:forEach>
	   		var $table = $('#combo_view');
			$table.html(tmpl('combo_view_table_tmpl', page));
			$table.find('tfoot a').click(function(){
				$.getComboPage($(this).attr('data-pageNo'));
			});
			
		}, "json");
	}
	// END Combo Page

	$.getMaterialPage(1);
	$.getComboPage(1);
	
	
})(jQuery);
</script>
<jsp:include page="../footer-end.jsp" />