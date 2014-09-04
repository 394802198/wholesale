<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<jsp:include page="../header.jsp" />
<jsp:include page="../alert.jsp" />

<style>
.btn {
	padding: 0 12px;
}
.bootstrap-select.btn-group, .bootstrap-select.btn-group[class*="span"] {
	margin-bottom: 0;
}
</style>

<div class="container">
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-info" id="manager_view" ></div>
		</div>
	</div>
</div>

<!-- DELETE MANAGER Modal -->
<div class="modal fade" id="deleteManagerModal" tabindex="-1" role="dialog" aria-labelledby="deleteManagerModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				<div class="row">
					<div class="panel panel-info">
						<div class="panel-heading">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
							<h3 class="panel-title">
								<strong>DELETE MANAGER</strong>
							</h3>
						</div>
						<div class="panel-body">
							<p>
								Delete this manager?
							</p>
						</div>
						<div class="panel-footer">
							<div class="form-group">
								<button data-name="deleteManagerBtn" type="button" class="btn btn-xs btn-info col-md-2 col-md-offset-5" data-dismiss="modal">Delete</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Manager View Template -->
<script type="text/html" id="manager_view_table_tmpl">
<jsp:include page="manager-view-page.html" />
</script>
<jsp:include page="../footer.jsp" />
<jsp:include page="../script.jsp" />
<script type="text/javascript" src="${ctx}/public/bootstrap3/js/bootstrap-select.min.js"></script>
<script type="text/javascript" src="${ctx}/public/bootstrap3/js/jTmpl.js"></script>
<script type="text/javascript">
(function($){
	
	$('.selectpicker').selectpicker();
	
	$('#checkbox_managers_top').click(function(){
		var b = $(this).prop("checked");
		if (b) {
			$('input[name="checkbox_managers"]').prop("checked", true);
		} else {
			$('input[name="checkbox_managers"]').prop("checked", false);
		}
	});
	
	$.getManagerPage = function(pageNo) {
		
		$.get('${ctx}/management/system/manager/view/'+pageNo, function(page){
			page.ctx = '${ctx}';
			page.managerId = '${managerSession.id}';
	   		var $table = $('#manager_view');
			$table.html(tmpl('manager_view_table_tmpl', page));
			$table.find('tfoot a').click(function(){
				$.getManagerPage($(this).attr('data-pageNo'));
			});
			
			$('a[data-toggle="tooltip"]').tooltip();
			
			$('a[data-name="deleteManager"]').click(function(){
				$('button[data-name="deleteManagerBtn"]').attr('id',this.id);
				$('#deleteManagerModal').modal('show');
			});
			$('button[data-name="deleteManagerBtn"]').click(function(){
				$.post('${ctx}/management/system/manager/remove/'+this.id, function(json){
			   		$.jsonValidation(json, 'right');
				});
			});
			$('#deleteManagerModal').on('hidden.bs.modal',function(){
				$.getManagerPage(1);
			});

			
		}, "json");
	}
	
	$.getManagerPage(1);
	
})(jQuery);
</script>
<jsp:include page="../footer-end.jsp" />