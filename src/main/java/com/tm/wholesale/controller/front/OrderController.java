package com.tm.wholesale.controller.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderController {

	public OrderController() {
	}
	
	@RequestMapping("/order/check-address")
	public String orderCheckAddress(Model model) {
		model.addAttribute("title", "Check Address - Order");
		return "wholesale/order/address-check";
	}
	
	@RequestMapping("/order/select-combo/{type}")
	public String orderSelectCombo(Model model,
			@PathVariable("type") String type) {
		model.addAttribute("title", "Select One Combo - Order");
		model.addAttribute("service_type", type);
		return "wholesale/order/select-combo";
	}

}
