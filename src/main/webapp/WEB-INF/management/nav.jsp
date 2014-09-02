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
						Material <b class="caret"></b>
					</a>
					<ul class="dropdown-menu">
						<li>
							<a href="javascript:void(0);" id="create_group_nav">
								<span class="glyphicon glyphicon-plus"></span> Create Group
							</a>
						</li>
						<li>
							<a href="javascript:void(0);" id="create_type_nav">
								<span class="glyphicon glyphicon-plus" ></span> Create type
							</a>
						</li>
						<li class="divider"></li>
						<li>
							<a href="${ctx}/broadband-wholesale/material/create">
								<span class="glyphicon glyphicon-plus" ></span> Create Material
							</a>
						</li>
						<li>
							<a href="${ctx}/broadband-wholesale/material-combo/view">
								<span class="glyphicon glyphicon-list" ></span> View Material
							</a>
						</li>
						<li>
							<a href="${ctx}/broadband-wholesale/material/edit">
								<span class="glyphicon glyphicon-plus" ></span> Modify Material
							</a>
						</li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						CRM <b class="caret"></b>
					</a>
					<ul class="dropdown-menu">
						<li>
							<a href="${ctx}/broadband-user/crm/customer/view">
								<span class="glyphicon glyphicon-list" ></span> View Customer
							</a>
						</li>
						<li>
							<a href="${ctx}/broadband-user/crm/customer/personal/create">
								<span class="glyphicon glyphicon-plus" ></span> Create Personal Customer
							</a>
						</li>
						<li>
							<a href="${ctx}/broadband-user/crm/customer/business/create">
								<span class="glyphicon glyphicon-plus" ></span> Create Business Customer
							</a>
						</li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						Data <b class="caret"></b>
					</a>
					<ul class="dropdown-menu">
						<li>
	                   		<a href="${ctx }/broadband-user/data/operatre">
	                   			<span class="glyphicon glyphicon-cloud" ></span> Data Operate
	                   		</a>
	                   	</li>
	                   	<li>
	                   		<a href="${ctx }/broadband-user/data/customer/view">
	                   			<span class="glyphicon glyphicon-cloud" ></span> Data Customer View
	                   		</a>
	                   	</li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						System <b class="caret"></b>
					</a>
					<ul class="dropdown-menu">
						<li>
							<a href="${ctx}/broadband-user/system/user/view/1">
								<span class="glyphicon glyphicon-list" ></span> View User
							</a>
						</li>
						<li>
							<a href="${ctx}/broadband-user/system/user/create">
								<span class="glyphicon glyphicon-plus" ></span> Create User
							</a>
						</li>
						<li class="divider"></li>
						<li>
							<a href="${ctx}/broadband-user/system/company-detail/edit">
								<span class="glyphicon glyphicon-pencil" ></span> Edit Company Detail
							</a>
						</li>
						<li class="divider"></li>
                    	<li>
							<a href="${ctx}/broadband-user/system/chart/customer-register/0">
								<span class="glyphicon glyphicon-picture" ></span> Chart(Register Customer)
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
							<a href="${ctx}/broadband-user/agent/billing/invoice/view/1/unpaid">
								<span class="glyphicon glyphicon-list" ></span> View Invoice
							</a>
						</li>
						<li>
							<a href="${ctx}/broadband-user/agent/billing/chart/commission-statistic/0">
								<span class="glyphicon glyphicon-list" ></span> Chart(Commission)
							</a>
						</li>
					</ul>
				</li>
			</ul>
			<p class="navbar-text navbar-right">
				<span class="glyphicon glyphicon-user"></span> 
				<a href="javascript:void(0);" class="navbar-link" >${managerSession.username}</a> 
				<a href="${ctx}/broadband-user/signout" data-toggle="tooltip" data-placement="bottom" data-original-title="Sign out">
					<span class="glyphicon glyphicon-log-out" ></span> 
				</a> 
			</p>
		</div>
		
		
		</c:if>
	</div>
</div>