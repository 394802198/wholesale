package com.tm.wholesale.controller.front;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tm.wholesale.model.Order;
import com.tm.wholesale.model.OrderDetail;
import com.tm.wholesale.model.OrderLog;
import com.tm.wholesale.model.Wholesaler;
import com.tm.wholesale.service.front.OrderService;
import com.tm.wholesale.util.TMUtils;

@Controller
public class OrderController {
	
	private OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
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
	
	@RequestMapping("/order/fill-information")
	public String orderFillInformation(Model model) {
		model.addAttribute("title", "Fill Information - Order");
		return "wholesale/order/information";
	}
	
	@RequestMapping("/order/review-order")
	public String orderReviewOrder(Model model) {
		model.addAttribute("title", "Review Order - Order");
		return "wholesale/order/review-order";
	}
	
	@RequestMapping(value = "/order/review-order/submit", method = RequestMethod.POST)
	public String orderReviewOrderSubmit(HttpSession session,
			@RequestParam("memo") String memo) {
		
		Wholesaler wholesalerSession = (Wholesaler) session.getAttribute("wholesalerSession");
		Order orderSession = (Order) session.getAttribute("orderSession");
		
		orderSession.setCompany_id(wholesalerSession.getCompany_id());
		orderSession.setWholesaler_id(wholesalerSession.getId());
		orderSession.setWholesaler_company_name(wholesalerSession.getCompany_name());
		
		Double total_price = 0d;
		Double total_price_enduser = 0d;
		for (OrderDetail od : orderSession.getOds()) {
			if (od.getIs_wholesale()) {
				total_price += od.getPrice();
			}
			if (od.getIs_enduser()) {
				total_price_enduser += od.getPrice_enduser();
			}
		}
		orderSession.setTotal_price_gst(Double.valueOf(TMUtils.fillDecimalPeriod(total_price * 1.15)));
		orderSession.setTotal_price_gst_enduser(Double.valueOf(TMUtils.fillDecimalPeriod(total_price_enduser * 1.15)));
		orderSession.setStatus("pending");
		orderSession.setMemo(memo);
		orderSession.setCreate_date(new Date());
		orderSession.setPreferred_connection_date(TMUtils.parseDateDDMMYYYYHHMMSS(orderSession.getPreferred_connection_date_str()));
		
		OrderLog ol = new OrderLog();
		
		ol.setOperator_id(wholesalerSession.getId());
		ol.setOperator_type("wholesale");
		ol.setOperator(wholesalerSession.getCompany_name() + " - " + wholesalerSession.getName());
		ol.setStatus("pending");
		ol.setStatus_desc("");
		ol.setComment_date(new Date());
		
		ol.setComment(memo);
		
		orderSession.setOl(ol);
		
		orderService.createOrder(orderSession);
		
		session.removeAttribute("orderSession");
		
		return "redirect:/order/query";
	}
	
	@RequestMapping("/order/query")
	public String orderQuery(Model model) {
		model.addAttribute("title", "Query Order - Order");
		return "wholesale/order/order-query";
	}
	

}
