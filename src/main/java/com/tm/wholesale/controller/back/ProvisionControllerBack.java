package com.tm.wholesale.controller.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tm.wholesale.service.back.OrderServiceBack;

@Controller
public class ProvisionControllerBack {
	
	private OrderServiceBack orderService;
	
	@Autowired
	public ProvisionControllerBack(OrderServiceBack orderService) {
		super();
		this.orderService = orderService;
	}

	@RequestMapping(value = "management/provision/view")
	public String toProvisionView(Model model){
		return "management/provision/provision-view";
	}

}
