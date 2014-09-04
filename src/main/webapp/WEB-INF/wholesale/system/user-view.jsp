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
					<h3 class="panel-title">View User</h3>
				</div>
				<div class="col-md-6">
					<a href="${ctx }/system/user/create" class="btn btn-info btn-xs pull-right">
  						<span class="glyphicon glyphicon-pencil"></span> Create User
					</a>
				</div>
			</div>
		</div>
		<div id="user-view-page"></div>
	</div>
</div>

<script type="text/html" id="user_view_page_tmpl" data-ctx="${ctx }">
<jsp:include page="user-view-page.html" />
</script>

<jsp:include page="../footer.jsp" />
<jsp:include page="../script.jsp" />
<script type="text/javascript" src="${ctx }/public/wholesale/system/user-view.js?ver=2014921127"></script>
<jsp:include page="../footer-end.jsp" />