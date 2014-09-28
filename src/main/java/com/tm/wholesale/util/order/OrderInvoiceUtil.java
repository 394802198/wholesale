package com.tm.wholesale.util.order;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.tm.wholesale.model.CustomerInvoiceDetail;
import com.tm.wholesale.model.InvoiceDetail;
import com.tm.wholesale.model.Order;
import com.tm.wholesale.model.OrderDetail;

public class OrderInvoiceUtil {

	/**
	 * @param o
	 * @category
	 * 	1. ASSIGN next_invoice_create_date_flag = inservice_date + prepay_month <br/>
	 * 	2. ASSIGN next_invoice_create_date = inservice_date + prepay_month + offset_date(-7) <br/>
	 */
	@Transactional
	public static Map<String, Object> setNextInvoiceCreateDate(Order o){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		Integer prepay_month = o.getPrepay_month()!=null ? o.getPrepay_month() : 1;
		Integer offset_date = -7;
		
		Calendar nextInvoiceFlagCal = Calendar.getInstance();
		nextInvoiceFlagCal.setTime(o.getInservice_date());
		nextInvoiceFlagCal.add(Calendar.MONTH, prepay_month);
		o.setNext_invoice_create_date_flag(nextInvoiceFlagCal.getTime());
		map.put("next_invoice_create_date_flag", nextInvoiceFlagCal.getTime());
		nextInvoiceFlagCal.add(Calendar.DAY_OF_MONTH, offset_date);
		o.setNext_invoice_create_date(nextInvoiceFlagCal.getTime());
		
		return map;
	}
	
	/**
	 * @param invoice_id
	 * @param od
	 * @param ids
	 * @param cids
	 * @category
	 * 	1. Add Wholesaler & EndUser's Order Details 2 Separate Invoices 
	 */
	@Transactional
	public static void addOrderDetails2InvoiceDetails(Integer invoice_id, OrderDetail od, List<InvoiceDetail> ids, List<CustomerInvoiceDetail> cids){
		
		if(od.getIs_wholesale()){
			addOrderDetails2InvoiceDetails(invoice_id, od, ids);
		} else if(od.getIs_enduser()) {
			addOrderDetails2CustomerInvoiceDetails(invoice_id, od, cids);
		}
		
	}
	
	@Transactional
	public static void addOrderDetails2InvoiceDetails(Integer invoice_id, OrderDetail od, List<InvoiceDetail> ids){

		InvoiceDetail id = new InvoiceDetail();
		id.setInvoice_id(invoice_id);
		id.setName(od.getName());
		id.setDesc(od.getDesc());
		id.setDetail_start_date(od.getDetail_start_date());
		id.setDetail_end_date(od.getDetail_end_date());
		id.setMaterial_group(od.getMaterial_group());
		id.setMaterial_type(od.getMaterial_type());
		id.setMaterial_category(od.getMaterial_category());
		id.setUnit(od.getUnit());
		id.setPayable_amount(od.getPrice());
		ids.add(id);
	
	}
	
	@Transactional
	public static void addOrderDetails2CustomerInvoiceDetails(Integer invoice_id, OrderDetail od, List<CustomerInvoiceDetail> cids){

		CustomerInvoiceDetail cid = new CustomerInvoiceDetail();
		cid.setInvoice_id(invoice_id);
		cid.setName(od.getName());
		cid.setDesc(od.getDesc());
		cid.setDetail_start_date(od.getDetail_start_date());
		cid.setDetail_end_date(od.getDetail_end_date());
		cid.setMaterial_group(od.getMaterial_group());
		cid.setMaterial_type(od.getMaterial_type());
		cid.setMaterial_category(od.getMaterial_category());
		cid.setUnit(od.getUnit());
		cid.setPayable_amount(od.getPrice_enduser());
		cids.add(cid);
	
	}

}
