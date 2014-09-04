<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

<div class="navbar navbar-default navbar-static-top" >
	<div class="container-fluid">
	
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${ctx}/index"> Wholesale Portal</a>
		</div>
		
		<c:if test="${wholesalerSession != null}">
		
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						System <span class="caret"></span>
					</a>
					<ul class="dropdown-menu" >
						<li><a href="${ctx }/system/user/change-password"><span class="glyphicon glyphicon-pencil"></span> Change Password</a></li>
						<li><a href="${ctx }/system/user/view"><span class="glyphicon glyphicon-list"></span> View User</a></li>
					</ul>
				</li>
			</ul>
			<p class="navbar-text navbar-right">
				<span class="glyphicon glyphicon-user"></span> 
				<a href="javascript:void(0);" class="navbar-link" >${wholesalerSession.name}</a> 
				<a href="${ctx}/signout" data-toggle="tooltip" data-placement="bottom" data-original-title="Sign out">
					<span class="glyphicon glyphicon-log-out" ></span> 
				</a> 
			</p>
		</div>
		
		</c:if>
	</div>
</div>