package com.tm.wholesale.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.tm.wholesale.util.TMUtils;

public class WholesalerOrderTransaction implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * TABLE MAPPING PROPERTIES
	 */
	private Integer wholesaler_id;
	private Integer order_id;
	private String description;
	private Double amount;
	private String status;
	private Date create_date;
	private Date last_update_date;

	/*
	 * END TABLE MAPPING PROPERTIES
	 */

	
	/*
	 * TABLE RELATED PROPERTIES
	 */
	
	private String create_date_str;
	private String last_update_date_str;
	
	private Map<String, Object> params = new HashMap<String, Object>();
	

	/*
	 * END TABLE RELATED PROPERTIES
	 */


	public Integer getWholesaler_id() {
		return wholesaler_id;
	}

	public void setWholesaler_id(Integer wholesaler_id) {
		this.wholesaler_id = wholesaler_id;
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.setCreate_date_str(TMUtils.dateFormatYYYYMMDD(this.getCreate_date()));
		this.create_date = create_date;
	}

	public Date getLast_update_date() {
		return last_update_date;
	}

	public void setLast_update_date(Date last_update_date) {
		this.setLast_update_date_str(TMUtils.dateFormatYYYYMMDD(this.getLast_update_date()));
		this.last_update_date = last_update_date;
	}

	public String getCreate_date_str() {
		return create_date_str;
	}

	public void setCreate_date_str(String create_date_str) {
		this.create_date_str = create_date_str;
	}

	public String getLast_update_date_str() {
		return last_update_date_str;
	}

	public void setLast_update_date_str(String last_update_date_str) {
		this.last_update_date_str = last_update_date_str;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
