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
import com.tm.wholesale.test.Console;
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

	@RequestMapping(value = "management/provision/order/detail")
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

	@RequestMapping(value = "management/provision/order/customer-detail/update")
	public JSONBean<Order> doOrderCustomerDetailUpdate(Model model,
			Order o){
		
		JSONBean<Order> json = new JSONBean<Order>();
		
		o.getParams().put("id", o.getId());
		this.orderService.editOrder(o);
		
		json.getSuccessMap().put("alert-success", "Customer Detail Successfully Updated!");
		
		return json;
	}

	@RequestMapping(value = "management/provision/order/status/update")
	public JSONBean<Order> doOrderStatusUpdate(Model model,
			Order o){
		
		JSONBean<Order> json = new JSONBean<Order>();
		
		o.getParams().put("id", o.getId());
		if(o.getStatus()=="disconnected"){
			o.setDisconnected_date(TMUtils.parseDateYYYYMMDD(o.getDisconnected_date_str()));
		}
		Console.log(o);
		this.orderService.editOrder(o);
		
		json.getSuccessMap().put("alert-success", "Order Status Successfully Updated!");
		
		return json;
	}

}
