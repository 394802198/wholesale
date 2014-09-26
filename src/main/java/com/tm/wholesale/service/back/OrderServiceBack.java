package com.tm.wholesale.service.back;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tm.wholesale.mapper.OrderDetailMapper;
import com.tm.wholesale.mapper.OrderLogMapper;
import com.tm.wholesale.mapper.OrderMapper;
import com.tm.wholesale.model.Order;
import com.tm.wholesale.model.OrderDetail;
import com.tm.wholesale.model.OrderLog;
import com.tm.wholesale.model.Page;
import com.tm.wholesale.util.TMUtils;

@Service
public class OrderServiceBack {
	
	private OrderMapper orderMapper;
	private OrderDetailMapper orderDetailMapper;
	private OrderLogMapper orderLogMapper;

	@Autowired
	public OrderServiceBack(OrderMapper orderMapper
			,OrderDetailMapper orderDetailMapper
			,OrderLogMapper orderLogMapper) {
		this.orderMapper = orderMapper;
		this.orderDetailMapper = orderDetailMapper;
		this.orderLogMapper = orderLogMapper;
	}
	
	public OrderServiceBack(){}

	// BEGIN Order
	@Transactional
	public Page<Order> queryOrdersByPage(Page<Order> page) {
		page.setTotalRecord(this.orderMapper.selectOrdersSum(page));
		page.setResults(this.orderMapper.selectOrdersByPage(page));
		return page;
	}

	@Transactional
	public void createOrder(Order o) {
		this.orderMapper.insertOrder(o);
	}
	
	@Transactional
	public List<Order> queryOrders(Order o) {
		return this.orderMapper.selectOrders(o);
	}
	
	@Transactional
	public Order queryOrder(Order o) {
		List<Order> os = this.orderMapper.selectOrders(o);
		return os!=null && os.size()>0 ? os.get(0) : null;
	}
	
	@Transactional
	public int queryOrdersSumByPage(Page<Order> page) {
		return this.orderMapper.selectOrdersSum(page);
	}

	@Transactional
	public void removeOrder(int id) {
		this.orderMapper.deleteOrderById(id);
	}

	@Transactional
	public void editOrder(Order o) {
		this.orderMapper.updateOrder(o);
	}
	// END Order

	// BEGIN OrderDetail
	@Transactional
	public Page<OrderDetail> queryOrderDetailsByPage(Page<OrderDetail> page) {
		page.setTotalRecord(this.orderDetailMapper.selectOrderDetailsSum(page));
		page.setResults(this.orderDetailMapper.selectOrderDetailsByPage(page));
		return page;
	}

	@Transactional
	public void createOrderDetail(OrderDetail od) {
		this.orderDetailMapper.insertOrderDetail(od);
	}
	
	@Transactional
	public int queryOrderDetailsSumByPage(Page<OrderDetail> page) {
		return this.orderDetailMapper.selectOrderDetailsSum(page);
	}

	@Transactional
	public void removeOrderDetail(int id) {
		this.orderDetailMapper.deleteOrderDetailById(id);
	}

	@Transactional
	public void editOrderDetail(OrderDetail od) {
		this.orderDetailMapper.updateOrderDetail(od);
	}
	// END OrderDetail

	// BEGIN OrderLog
	@Transactional
	public Page<OrderLog> queryOrderLogsByPage(Page<OrderLog> page) {
		page.setTotalRecord(this.orderLogMapper.selectOrderLogsSum(page));
		page.setResults(this.orderLogMapper.selectOrderLogsByPage(page));
		return page;
	}

	@Transactional
	public void createOrderLog(OrderLog ol) {
		this.orderLogMapper.insertOrderLog(ol);
	}
	
	@Transactional
	public int queryOrderLogsSumByPage(Page<OrderLog> page) {
		return this.orderLogMapper.selectOrderLogsSum(page);
	}

	@Transactional
	public void removeOrderLog(int id) {
		this.orderLogMapper.deleteOrderLogById(id);
	}

	@Transactional
	public void editOrderLog(OrderLog ol) {
		this.orderLogMapper.updateOrderLog(ol);
	}
	// END OrderLog
	
	
	
	/**
	 * BEGIN EXTENDED METHODS
	 */

	@Transactional
	public OrderDetail addPlan(Date startDate, Date endDate
			, Integer order_id
			, String plan_name
			, Double price
			, Double price_enduser
			, String subscribe
			, Integer unit
			, Long data_flow){
		
		OrderDetail od = new OrderDetail();
		od.setPlan_start_date(startDate);
		od.setPlan_end_date(endDate);
		od.setOrder_id(order_id);
		od.setName(plan_name);
		od.setPrice(price);
		od.setPrice_enduser(price_enduser);
		od.setSubscribe(subscribe);
		od.setData_flow(data_flow);
		od.setUnit(unit);
		
		return od;
	}
	
	
	/**
	 * @param o
	 * @category
	 * 	1. ASSIGN inservice_date <br/>
	 * 	2. ASSIGN next_invoice_create_date_flag = inservice_date + prepay_month <br/>
	 * 	3. ASSIGN next_invoice_create_date = inservice_date + prepay_month + offset_date(-7) <br/>
	 * 	4. <br/>
	 * 	5. <br/>
	 * 	6. <br/>
	 * 	7. <br/>
	 *  8. <br/>
	 * 	9. <br/>
	 */
	@Transactional
	public void editInService(Order o) {
		
		// 1.
		
		o.setInservice_date(TMUtils.parseDateYYYYMMDD(o.getInservice_date_str()));
		
		// 2.
		
		Integer prepay_month = o.getPrepay_month()!=null ? o.getPrepay_month() : 1;
		Integer offset_date = -7;
		
		Calendar nextInvoiceCal = Calendar.getInstance();
		nextInvoiceCal.setTime(o.getInservice_date());
		nextInvoiceCal.add(Calendar.MONTH, prepay_month);
		o.setNext_invoice_create_date_flag(nextInvoiceCal.getTime());
		nextInvoiceCal.add(Calendar.DAY_OF_MONTH, offset_date);
		o.setNext_invoice_create_date(nextInvoiceCal.getTime());

		o.getParams().put("id", o.getId());
		this.editOrder(o);
		
		Order oQuery = new Order();
		oQuery.getParams().put("id", o.getId());
		oQuery = this.queryOrder(oQuery);
		
		
		
		if("pre-pay".equals(oQuery.getPay_type())){
			
			
			
		} else if("post-pay".equals(oQuery.getPay_type())){
			
			
			
		}
	}
	
	
	/**
	 * END EXTENDED METHODS
	 */

}
