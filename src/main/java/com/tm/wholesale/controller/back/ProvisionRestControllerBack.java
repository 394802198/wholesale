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
		this.orderService.queryOrdersByPage(page);
		
		return page;
	}

}
