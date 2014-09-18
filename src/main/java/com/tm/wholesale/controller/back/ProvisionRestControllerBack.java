package com.tm.wholesale.controller.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tm.wholesale.model.Order;
import com.tm.wholesale.model.Page;
import com.tm.wholesale.service.back.OrderServiceBack;

@RestController
public class ProvisionRestControllerBack {
	
	private OrderServiceBack orderService;
	
	@Autowired
	public ProvisionRestControllerBack(OrderServiceBack orderService) {
		super();
		this.orderService = orderService;
	}

	@RequestMapping(value = "management/provision/view/{pageNo}/{status}")
	public Page<Order> toProvisionViewPage(Model model,
			@PathVariable("pageNo") Integer pageNo,
			@PathVariable("status") String status){
		
		Page<Order> page = new Page<Order>();
		page.setPageNo(pageNo);
		page.setPageSize(30);
		page.getParams().put("orderby", "order by create_date desc");
		page.getParams().put("status", status);
		this.orderService.queryOrdersByPage(page);
		
		Page<Order> pageSum = new Page<Order>();
		pageSum.getParams().put("status", "pending");
		page.getParams().put("pendingSum", this.orderService.queryOrdersSumByPage(pageSum));
		pageSum.getParams().put("status", "processing");
		page.getParams().put("processingSum", this.orderService.queryOrdersSumByPage(pageSum));
		pageSum.getParams().put("status", "reconnection");
		page.getParams().put("reconnectionSum", this.orderService.queryOrdersSumByPage(pageSum));
		pageSum.getParams().put("status", "rfs");
		page.getParams().put("rfsSum", this.orderService.queryOrdersSumByPage(pageSum));
		pageSum.getParams().put("status", "in-service");
		page.getParams().put("inServiceSum", this.orderService.queryOrdersSumByPage(pageSum));
		pageSum.getParams().put("status", "suspend");
		page.getParams().put("suspendSum", this.orderService.queryOrdersSumByPage(pageSum));
		pageSum.getParams().put("status", "disconnected");
		page.getParams().put("disconnectedSum", this.orderService.queryOrdersSumByPage(pageSum));
		pageSum.getParams().put("status", "void");
		page.getParams().put("voidSum", this.orderService.queryOrdersSumByPage(pageSum));
		pageSum.getParams().put("status", "cancel");
		page.getParams().put("cancelSum", this.orderService.queryOrdersSumByPage(pageSum));
		
		return page;
	}

}
