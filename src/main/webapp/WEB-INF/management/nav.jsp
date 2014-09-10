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
			<a class="navbar-brand" href="${ctx}/management/index">TMS Wholesale Management System</a>
		</div>
		
		<c:if test="${managerSession != null}">
		
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						System <b class="caret"></b>
					</a>
					<ul class="dropdown-menu">
						<li>
							<a href="${ctx}/management/system/manager/view">
								<span class="glyphicon glyphicon-list" ></span> View Manager
							</a>
						</li>
						<li>
							<a href="${ctx}/management/system/manager/create">
								<span class="glyphicon glyphicon-plus" ></span> Create Manager
							</a>
						</li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						Product <b class="caret"></b>
					</a>
					<ul class="dropdown-menu">
						<li>
							<a href="${ctx}/management/product/material-combo/view">
								<span class="glyphicon glyphicon-list" ></span> View Material & Combo
							</a>
						</li>
						<li>
							<a href="${ctx}/management/product/material/create">
								<span class="glyphicon glyphicon-plus" ></span> Create Material
							</a>
						</li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						Wholesale <b class="caret"></b>
					</a>
					<ul class="dropdown-menu">
						<li>
							<a href="${ctx}/management/wholesale/wholesaler/view">
								<span class="glyphicon glyphicon-list" ></span> View Wholesaler
							</a>
						</li>
						<li>
							<a href="${ctx}/management/wholesale/wholesaler/create">
								<span class="glyphicon glyphicon-plus" ></span> Create Wholesaler
							</a>
						</li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						Provision <b class="caret"></b>
					</a>
					<ul class="dropdown-menu">
						<li>
							<a href="${ctx}/management/wholesale/wholesaler/view">
								<span class="glyphicon glyphicon-list" ></span> View Wholesaler
							</a>
						</li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						Billing <b class="caret"></b>
					</a>
					<ul class="dropdown-menu">
						<li>
							<a href="${ctx}/management/wholesale/wholesaler/view">
								<span class="glyphicon glyphicon-list" ></span> View Wholesaler
							</a>
						</li>
					</ul>
				</li>
			</ul>
			<p class="navbar-text navbar-right">
				<span class="glyphicon glyphicon-user"></span> 
				<a href="javascript:void(0);" class="navbar-link" >${managerSession.username}</a> 
				<a href="${ctx}/management/signout" data-toggle="tooltip" data-placement="bottom" data-original-title="Sign out">
					<span class="glyphicon glyphicon-log-out" ></span> 
				</a> 
			</p>
		</div>
		
		
		</c:if>
	</div>
</div>