<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<jsp:include page="../header.jsp" />
<jsp:include page="../alert.jsp" />


<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-info" id="provision_view" ></div>
		</div>
	</div>
</div>

<!-- Provision View Template -->
<script type="text/html" id="provision_view_table_tmpl">
<jsp:include page="provision-view-page.html" />
</script>
<jsp:include page="../footer.jsp" />
<jsp:include page="../script.jsp" />
<script type="text/javascript" src="${ctx}/public/bootstrap3/js/bootstrap-select.min.js"></script>
<script type="text/javascript" src="${ctx}/public/bootstrap3/js/jTmpl.js"></script>
<script type="text/javascript">
(function($){
	
	$('.selectpicker').selectpicker();
	
	$('#checkbox_provisions_top').click(function(){
		var b = $(this).prop("checked");
		if (b) {
			$('input[name="checkbox_provisions"]').prop("checked", true);
		} else {
			$('input[name="checkbox_provisions"]').prop("checked", false);
		}
	});
	
	$.getProvisionPage = function(pageNo, status) {
		
		$.get('${ctx}/management/provision/view/'+pageNo+'/'+status, function(page){
			console.log('${ctx}/management/provision/view/'+pageNo+'/'+status);
			console.log(page);
			page.ctx = '${ctx}';
			page.userId = '${managerSession.id}';
			page.orderStatus = status;
	   		var $table = $('#provision_view');
			$table.html(tmpl('provision_view_table_tmpl', page));
			$table.find('tfoot a').click(function(){
				$.getProvisionPage($(this).attr('data-pageNo'));
			});
			
			$('button[data-name="query_order"]').click(function(){
				$.getProvisionPage(1, $(this).attr('data-status'));
			});
			
		}, "json");
	}
	
	
	
	$.getProvisionPage(1, 'pending');
	
})(jQuery);
</script>
<jsp:include page="../footer-end.jsp" />