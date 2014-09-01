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


<div class="container">
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-info">
				<div class="panel-heading">
					<h4 class="panel-title">Operations</h4>
				</div>
				<div class="panel-body">
                    <ul class="list-unstyled">
                    	<li>
                    		<span class="glyphicon glyphicon-plus"></span>
                    		<a href="javascript:void(0);" data-name="create_material_group_type" data-type="group">Create Group</a>
                    	</li>
                    	<li>
                    		<span class="glyphicon glyphicon-plus"></span>
                    		<a href="javascript:void(0);" data-name="create_material_group_type" data-type="type">Create Type</a>
                    	</li>
                    	<li>
                    		<span class="glyphicon glyphicon-plus"></span>
                    		<a href="javascript:void(0);" data-name="create_material_group_type" data-type="category">Create Category</a>
                    	</li>
                    </ul>
				</div>
			</div>
		
			<!-- Nav tabs -->
			<ul class="nav nav-tabs">
				<li class="active"><a href="#material_view" data-toggle="tab"><strong>Material View</strong></a></li>
				<li><a href="#combo_view" data-toggle="tab"><strong>Combo View</strong></a></li>
			</ul>

			<!-- Tab panes -->
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

<!-- Material View Template -->
<script type="text/html" id="material_view_table_tmpl">
<jsp:include page="material-view-page.html" />
</script>
<!-- Combo View Template -->
<script type="text/html" id="combo_view_table_tmpl">
<jsp:include page="material-combo-view-page.html" />
</script>

<jsp:include page="../footer.jsp" />
<jsp:include page="../script.jsp" />
<script type="text/javascript" src="${ctx}/public/bootstrap3/js/bootstrap-select.min.js"></script>
<script type="text/javascript" src="${ctx}/public/bootstrap3/js/jTmpl.js"></script>
<script type="text/javascript">
(function($){
	
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
	
	$.getMaterialPage = function(pageNo) {
		
		$.get('${ctx}/management/product/material/view/'+pageNo, function(page){
			page.ctx = '${ctx}';
	   		var $table = $('#material_view');
			$table.html(tmpl('material_view_table_tmpl', page));
			$table.find('tfoot a').click(function(){
				$.getMaterialPage($(this).attr('data-pageNo'));
			});
			
		}, "json");
	}
	
	$.getComboPage = function(pageNo) {
		
		$.get('${ctx}/management/product/combo/view/'+pageNo, function(page){
			page.ctx = '${ctx}';
	   		var $table = $('#combo_view');
			$table.html(tmpl('combo_view_table_tmpl', page));
			$table.find('tfoot a').click(function(){
				$.getMaterialComboPage($(this).attr('data-pageNo'));
			});
			
		}, "json");
	}

	$.getMaterialPage(1);
	$.getComboPage(1);
	
	
})(jQuery);
</script>
<jsp:include page="../footer-end.jsp" />