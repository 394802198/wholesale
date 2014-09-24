package com.tm.wholesale.controller.front;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tm.wholesale.model.Broadband;
import com.tm.wholesale.model.Combo;
import com.tm.wholesale.model.ComboWholesaler;
import com.tm.wholesale.model.JSONBean;
import com.tm.wholesale.model.MaterialWholesaler;
import com.tm.wholesale.model.Order;
import com.tm.wholesale.model.Page;
import com.tm.wholesale.model.Wholesaler;
import com.tm.wholesale.service.back.ProductServiceBack;
import com.tm.wholesale.service.front.OrderService;
import com.tm.wholesale.util.broadband.BroadbandCapability;
import com.tm.wholesale.validation.OrderSessionInformationValidatedMark;
import com.tm.wholesale.validation.OrderSessionSelectComboValidatedMark;

@RestController
public class OrderRestController {
	
	private ProductServiceBack productService;
	private OrderService orderService;

	@Autowired
	public OrderRestController(ProductServiceBack productService
			, OrderService orderService) {
		this.productService = productService;
		this.orderService= orderService;
	}
	
	@RequestMapping("/order/check-address/{address}")
	public Broadband checkAddress(HttpSession session
			, @PathVariable("address") String address) {
		System.out.println("address: " + address);
		Order orderSession = new Order();
		orderSession.setAddress(address);
		orderSession.setCustomer_type("personal");
		session.setAttribute("orderSession", orderSession);
		
		Broadband broadband = this.returnBroadband(address);
		broadband.setAddress(address);
		orderSession.setBroadband(broadband);
		return broadband;
	}
	
	private Broadband returnBroadband(String address) {
		String message = "";
		try {
			message = BroadbandCapability.getCapabilityResultByAddress(address);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Broadband broadband = new Broadband();
		String services_available = "";
		if (message.contains("> 10")) {
			broadband.setAdsl_available(true);
			services_available += "ADSL";
		} 
		if (message.contains("> 20")) {
			broadband.setVdsl_available(true);
			services_available += "VDSL";
		} 
		if (message.contains("Business fibre available") || message.contains("Network capability:<\\/h4><ul><li>UFB fibre up to 100 Mbps")) {
			broadband.setUfb_available(true);
			services_available += "UFB";
		} 
		broadband.setScheduled(message.substring(message.lastIndexOf(",") + 1));
		broadband.setServices_available(services_available);
	
		return broadband;
	}
	
	@RequestMapping("/order/select-combo/combo-loading")
	public List<ComboWholesaler> orderComboLoading(HttpSession session) {
		
		Wholesaler wholesalerSession = (Wholesaler) session.getAttribute("wholesalerSession");
		
		return this.productService.queryComboWholesalersWithMaterialWholesalers(wholesalerSession.getCompany_id());
	}
	
	@RequestMapping("/order/select-combo/material-loading")
	public List<MaterialWholesaler> orderMaterialLoading(HttpSession session) {
		
		Wholesaler wholesalerSession = (Wholesaler) session.getAttribute("wholesalerSession");
		
		MaterialWholesaler mw = new MaterialWholesaler();
		mw.getParams().put("company_id", wholesalerSession.getCompany_id());
		
		List<MaterialWholesaler> materials = this.productService.queryMaterialWholesalers(mw);
		
		return materials;
	}
	
	@RequestMapping(value = "/order/select-combo/submit", method = RequestMethod.POST)
	public JSONBean<Order> orderSelectComboSubmit(HttpSession session,
			@Validated(OrderSessionSelectComboValidatedMark.class) @RequestBody Order order, BindingResult result) {

		JSONBean<Order> json = new JSONBean<Order>();
		json.setModel(order);
		
		Order orderSession = (Order) session.getAttribute("orderSession");
		
		orderSession.setOds(order.getOds());
		
		orderSession.setBroadband_type(order.getBroadband_type());
		
		json.setUrl("/order/fill-information");
		
		return json;
	}
	
	@RequestMapping(value = "/order/information/loading")
	public Order orderInformationLoading(HttpSession session) {
		Order orderSession = (Order) session.getAttribute("orderSession");
		return orderSession;
	}
	
	@RequestMapping(value = "/order/fill-information/submit", method = RequestMethod.POST)
	public JSONBean<Order> orderInformationSubmit(HttpSession session,
			@Validated(OrderSessionInformationValidatedMark.class) @RequestBody Order order, BindingResult result) {

		JSONBean<Order> json = new JSONBean<Order>();
		json.setModel(order);
		
		Order orderSession = (Order) session.getAttribute("orderSession");
		
		if (result.hasErrors()) {
			json.setJSONErrorMap(result);
			if (!"transition".equals(orderSession.getBroadband_type())) {
				System.out.println("remove transition");
				json.getErrorMap().remove("transition_porting_number");
				json.getErrorMap().remove("transition_provider_name");
				json.getErrorMap().remove("transition_account_number");
				json.getErrorMap().remove("transition_account_holder_name");
			}
			if (json.isHasErrors()) return json;
		}
		
		orderSession.setCustomer_type(order.getCustomer_type());
		orderSession.setCompany_name(order.getCompany_name());
		orderSession.setTrade_name(order.getTrade_name());
		orderSession.setTitle(order.getTitle());
		orderSession.setFirst_name(order.getFirst_name());
		orderSession.setLast_name(order.getLast_name());
		orderSession.setEmail(order.getEmail());
		orderSession.setMobile(order.getMobile());
		orderSession.setPhone(order.getPhone());
		orderSession.setPreferred_connection_date(order.getPreferred_connection_date());
		
		orderSession.setTransition_porting_number(order.getTransition_porting_number());
		orderSession.setTransition_provider_name(order.getTransition_provider_name());
		orderSession.setTransition_account_number(order.getTransition_account_number());
		orderSession.setTransition_account_holder_name(order.getTransition_account_holder_name());
		
		json.setUrl("/order/review-order");
		
		return json;
	}
	
	@RequestMapping(value = "/order/review-order/loading")
	public Order orderReviewOrderLoading(HttpSession session) {
		Order orderSession = (Order) session.getAttribute("orderSession");
		return orderSession;
	}
	
	@RequestMapping("/order/query/{pageNo}")
	public Page<Order> orderQuery(HttpSession session
			, @PathVariable("pageNo") int pageNo) {
		
		Wholesaler wholesalerSession = (Wholesaler) session.getAttribute("wholesalerSession");
		
		Page<Order> page = new Page<Order>();
		page.setPageNo(pageNo);
		page.setPageSize(50);
		page.getParams().put("orderby", "order by create_date");
		page.getParams().put("company_id", wholesalerSession.getCompany_id());
		
		this.orderService.queryOrdersByPage(page);
		
		return page;
	}

}
