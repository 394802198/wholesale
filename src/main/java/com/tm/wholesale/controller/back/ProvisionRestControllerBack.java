package com.tm.wholesale.controller.back;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tm.wholesale.model.JSONBean;
import com.tm.wholesale.model.Order;
import com.tm.wholesale.model.Page;
import com.tm.wholesale.model.Wholesaler;
import com.tm.wholesale.service.back.OrderServiceBack;
import com.tm.wholesale.service.back.WholesalerServiceBack;
import com.tm.wholesale.util.TMUtils;

@RestController
public class ProvisionRestControllerBack {
	
	private OrderServiceBack orderService;
	private WholesalerServiceBack wholesalerServiceBack;
	
	@Autowired
	public ProvisionRestControllerBack(OrderServiceBack orderService,
			WholesalerServiceBack wholesalerServiceBack) {
		super();
		this.orderService = orderService;
		this.wholesalerServiceBack = wholesalerServiceBack;
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
		
		
		// RELEASE MEMORY
		pageSum = null;
		
		return page;
	}

	@RequestMapping(value = "management/provision/order/info")
	public JSONBean<Order> toOrderDetailView(Model model,
			@RequestParam("id") Integer id){
		
		JSONBean<Order> json = new JSONBean<Order>();
		
		Order oQuery = new Order();
		oQuery.getParams().put("id", id);
		Order o = this.orderService.queryOrder(oQuery);
		
		Wholesaler wQuery = new Wholesaler();
		wQuery.getParams().put("id", o.getWholesaler_id());
		o.setWholesaler_name(this.wholesalerServiceBack.queryWholesaler(wQuery).getName());
		
		json.setModel(o);
		
		
		// RELEASE MEMORY
		oQuery = null;
		o = null;
		wQuery = null;
		
		return json;
	}

	@RequestMapping(value = "management/provision/order/customer-info/update")
	public JSONBean<Order> doOrderCustomerInfoUpdate(Model model,
			Order o){
		
		JSONBean<Order> json = new JSONBean<Order>();
		
		if("".equals(o.getFirst_name().trim())
		 ||"".equals(o.getLast_name().trim())
		 ||"".equals(o.getPhone().trim())
		 ||"".equals(o.getMobile().trim())
		 ||"".equals(o.getEmail().trim())
		 ||"".equals(o.getAddress().trim())){
			
			if("".equals(o.getFirst_name().trim())){
				json.getErrorMap().put("order_customer_first_name", "First Name Musn't be Empty");
			}
			if("".equals(o.getLast_name().trim())){
				json.getErrorMap().put("order_customer_last_name", "Last Name Musn't be Empty");
			}
			if("".equals(o.getPhone().trim())){
				json.getErrorMap().put("order_customer_phone", "Phone Musn't be Empty");
			}
			if("".equals(o.getMobile().trim())){
				json.getErrorMap().put("order_customer_mobile", "Mobile Musn't be Empty");
			}
			if("".equals(o.getEmail().trim())){
				json.getErrorMap().put("order_customer_email", "Email Musn't be Empty");
			}
			if("".equals(o.getAddress().trim())){
				json.getErrorMap().put("order_customer_address", "Address Musn't be Empty");
			}
			
		} else {
			
			o.getParams().put("id", o.getId());
			this.orderService.editOrder(o);
			
			json.getSuccessMap().put("alert-success", "Customer Info Successfully Updated!");
		}
		
		return json;
	}

	@RequestMapping(value = "management/provision/order/status/update")
	public JSONBean<Order> doOrderStatusUpdate(Model model,
			Order o){
		
		JSONBean<Order> json = new JSONBean<Order>();
		
		o.getParams().put("id", o.getId());
		if("disconnected".equals(o.getStatus())){
			o.setDisconnected_date(TMUtils.parseDateYYYYMMDD(o.getDisconnected_date_str()));
		}
		this.orderService.editOrder(o);
		
		json.getSuccessMap().put("alert-success", "Order Status Successfully Updated!");
		
		return json;
	}

	@RequestMapping(value = "management/provision/order/pppoe/update")
	public JSONBean<Order> doOrderPPPoEUpdate(Model model,
			Order o){
		
		JSONBean<Order> json = new JSONBean<Order>();
		
		if("".equals(o.getPppoe_loginname().trim())
		 ||"".equals(o.getPppoe_password().trim())){
			
			if("".equals(o.getPppoe_loginname().trim())){
				json.getErrorMap().put("pppoe_loginname", "LoginName Musn't be Empty");
			}
			if("".equals(o.getPppoe_password().trim())){
				json.getErrorMap().put("pppoe_password", "Password Musn't be Empty");
			}
			
		} else {
			
			o.getParams().put("id", o.getId());
			this.orderService.editOrder(o);
			
			json.getSuccessMap().put("alert-success", "Order PPPoE Successfully Updated!");
		}
		
		return json;
	}

	@RequestMapping(value = "management/provision/order/svcvlan/update")
	public JSONBean<Order> doOrderSVCVLanUpdate(Model model,
			Order o){
		
		JSONBean<Order> json = new JSONBean<Order>();
		
		if("".equals(o.getSvlan().trim())
		 ||"".equals(o.getCvlan().trim())){
			
			if("".equals(o.getSvlan().trim())){
				json.getErrorMap().put("svlan", "SVLan Musn't be Empty");
			}
			if("".equals(o.getCvlan().trim())){
				json.getErrorMap().put("cvlan", "CVLan Musn't be Empty");
			}
			
		} else {
			
			o.getParams().put("id", o.getId());
			o.setRfs_date(TMUtils.parseDateYYYYMMDD(o.getRfs_date_str()));
			this.orderService.editOrder(o);
			
			json.getSuccessMap().put("alert-success", "Order SV/CVLan Successfully Updated!");
		}
		
		return json;
	}

	@RequestMapping(value = "management/provision/order/service-giving/update")
	public JSONBean<Order> doOrderServiceGivingUpdate(Model model,
			Order o){
		
		JSONBean<Order> json = new JSONBean<Order>();
		
		this.orderService.editInService(o);
		
		json.getSuccessMap().put("alert-success", "Order In Service Date & Next Invoice Date Successfully Updated!");
		
		return json;
	}

	@RequestMapping(value = "management/provision/order/broadband-asid/update")
	public JSONBean<Order> doOrderBroadbandASIDUpdate(Model model,
			Order o){
		
		JSONBean<Order> json = new JSONBean<Order>();
		
		if("".equals(o.getBroadband_asid().trim())){
			json.getErrorMap().put("order_broadband_asid", "ASID Musn't be Empty");
		} else {
			
			o.getParams().put("id", o.getId());
			this.orderService.editOrder(o);
			
			json.getSuccessMap().put("alert-success", "Order Broadband ASID Successfully Updated!");
		}
		
		return json;
	}

}
