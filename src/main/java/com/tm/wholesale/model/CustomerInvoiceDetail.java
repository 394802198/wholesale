package com.tm.wholesale.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.tm.wholesale.util.TMUtils;

public class CustomerInvoiceDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * TABLE MAPPING PROPERTIES
	 */
	
	private Integer id;
	private Integer invoice_id;
	private String name;
	private Date detail_start_date;
	private Date detail_end_date;
	private String desc;
	private Double payable_amount;
	private Integer unit;
	private String material_group;
	private String material_type;
	private String material_category;

	/*
	 * END TABLE MAPPING PROPERTIES
	 */

	/*
	 * RELATED PROPERTIES
	 */
	
	private String detail_start_date_str;
	private String detail_end_date_str;

	private Map<String, Object> params = new HashMap<String, Object>();

	/*
	 * END RELATED PROPERTIES
	 */
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getInvoice_id() {
		return invoice_id;
	}
	public void setInvoice_id(Integer invoice_id) {
		this.invoice_id = invoice_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDetail_start_date() {
		return detail_start_date;
	}
	public void setDetail_start_date(Date detail_start_date) {
		this.setDetail_start_date_str(TMUtils.dateFormatYYYYMMDD(detail_start_date));
		this.detail_start_date = detail_start_date;
	}
	public Date getDetail_end_date() {
		return detail_end_date;
	}
	public void setDetail_end_date(Date detail_end_date) {
		this.setDetail_end_date_str(TMUtils.dateFormatYYYYMMDD(detail_end_date));
		this.detail_end_date = detail_end_date;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Double getPayable_amount() {
		return payable_amount;
	}
	public void setPayable_amount(Double payable_amount) {
		this.payable_amount = payable_amount;
	}
	public Integer getUnit() {
		return unit;
	}
	public void setUnit(Integer unit) {
		this.unit = unit;
	}
	public String getMaterial_group() {
		return material_group;
	}
	public void setMaterial_group(String material_group) {
		this.material_group = material_group;
	}
	public String getMaterial_type() {
		return material_type;
	}
	public void setMaterial_type(String material_type) {
		this.material_type = material_type;
	}
	public String getMaterial_category() {
		return material_category;
	}
	public void setMaterial_category(String material_category) {
		this.material_category = material_category;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getDetail_start_date_str() {
		return detail_start_date_str;
	}
	public void setDetail_start_date_str(String detail_start_date_str) {
		this.detail_start_date_str = detail_start_date_str;
	}
	public String getDetail_end_date_str() {
		return detail_end_date_str;
	}
	public void setDetail_end_date_str(String detail_end_date_str) {
		this.detail_end_date_str = detail_end_date_str;
	}
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

}
