package com.tm.wholesale.service.front;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tm.wholesale.mapper.ComboWholesalerMapper;
import com.tm.wholesale.mapper.MaterialWholesalerMapper;
import com.tm.wholesale.mapper.OrderDetailMapper;
import com.tm.wholesale.mapper.OrderLogMapper;
import com.tm.wholesale.mapper.OrderMapper;
import com.tm.wholesale.model.ComboWholesaler;
import com.tm.wholesale.model.MaterialWholesaler;
import com.tm.wholesale.model.Order;
import com.tm.wholesale.model.OrderDetail;
import com.tm.wholesale.model.OrderLog;
import com.tm.wholesale.model.Page;

@Service
public class OrderService {
	
	private ComboWholesalerMapper comboWholesalerMapper;
	private MaterialWholesalerMapper materialWholesalerMapper;
	private OrderMapper orderMapper;
	private OrderDetailMapper orderDetailMapper;
	private OrderLogMapper orderLogMapper;

	@Autowired
	public OrderService(ComboWholesalerMapper comboWholesalerMapper
			, MaterialWholesalerMapper materialWholesalerMapper
			, OrderMapper orderMapper
			, OrderDetailMapper orderDetailMapper
			, OrderLogMapper orderLogMapper) {
		this.comboWholesalerMapper = comboWholesalerMapper;
		this.materialWholesalerMapper = materialWholesalerMapper;
		this.orderMapper = orderMapper;
		this.orderDetailMapper = orderDetailMapper;
		this.orderLogMapper = orderLogMapper;
	}
	
	public OrderService(){}
	
	@Transactional
	public void createOrder(Order order) {
		this.orderMapper.insertOrder(order);
		for (OrderDetail od : order.getOds()) {
			od.setOrder_id(order.getId());
			this.orderDetailMapper.insertOrderDetail(od);
		}
		if (order.getOl() != null) {
			this.orderLogMapper.insertOrderLog(order.getOl());
		}
	}
	
	@Transactional
	public Page<Order> queryOrdersByPage(Page<Order> page) {
		page.setTotalRecord(this.orderMapper.selectOrdersSum(page));
		page.setResults(this.orderMapper.selectOrdersByPage(page));
		return page;
	}
	
	@Transactional
	public Order queryOrder(Order orderQuery) {
		List<Order> orders = this.orderMapper.selectOrders(orderQuery);
		Order order = orders != null && orders.size() > 0 ? orders.get(0) : null;
		List<OrderDetail> ods = this.orderDetailMapper.selectOrderDetails(orderQuery.getOd());
		order.setOds(ods);
		return order;
	}
	
	/*@Transactional
	public List<ComboWholesaler> queryComboWholesalers(ComboWholesaler cw) {
		return this.comboWholesalerMapper.selectComboWholesalers(cw);
	}
	
	@Transactional
	public List<MaterialWholesaler> queryMaterialWholesalers(MaterialWholesaler mw) {
		return this.materialWholesalerMapper.selectMaterialWholesalers(mw);
	}*/

}
