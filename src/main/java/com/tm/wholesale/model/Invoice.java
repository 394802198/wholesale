package com.tm.wholesale.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.tm.wholesale.util.TMUtils;

public class Invoice implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * TABLE MAPPING PROPERTIES
	 */
	
	private Integer id;
	private Integer company_id;
	private Integer order_id;
	private Date create_date;
	private Date due_date;
	private Double payable_amount;
	private Double credit_amount;
	private Double paid_amount;
	private Double balance_amount;
	private String invoice_pdf_path;
	private Integer previous_invoice_id;
	private String status;
	private String payment_status;
	private String memo;

	/*
	 * END TABLE MAPPING PROPERTIES
	 */
	
	private String create_date_str;
	private String due_date_str;

	private Map<String, Object> params = new HashMap<String, Object>();

	/*
	 * RELATED PROPERTIES
	 */
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCompany_id() {
		return company_id;
	}
	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.setCreate_date_str(TMUtils.dateFormatYYYYMMDD(create_date));
		this.create_date = create_date;
	}
	public Date getDue_date() {
		return due_date;
	}
	public void setDue_date(Date due_date) {
		this.setDue_date_str(TMUtils.dateFormatYYYYMMDD(due_date));
		this.due_date = due_date;
	}
	public Double getPayable_amount() {
		return payable_amount;
	}
	public void setPayable_amount(Double payable_amount) {
		this.payable_amount = payable_amount;
	}
	public Double getCredit_amount() {
		return credit_amount;
	}
	public void setCredit_amount(Double credit_amount) {
		this.credit_amount = credit_amount;
	}
	public Double getPaid_amount() {
		return paid_amount;
	}
	public void setPaid_amount(Double paid_amount) {
		this.paid_amount = paid_amount;
	}
	public Double getBalance_amount() {
		return balance_amount;
	}
	public void setBalance_amount(Double balance_amount) {
		this.balance_amount = balance_amount;
	}
	public String getInvoice_pdf_path() {
		return invoice_pdf_path;
	}
	public void setInvoice_pdf_path(String invoice_pdf_path) {
		this.invoice_pdf_path = invoice_pdf_path;
	}
	public Integer getPrevious_invoice_id() {
		return previous_invoice_id;
	}
	public void setPrevious_invoice_id(Integer previous_invoice_id) {
		this.previous_invoice_id = previous_invoice_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPayment_status() {
		return payment_status;
	}
	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCreate_date_str() {
		return create_date_str;
	}
	public void setCreate_date_str(String create_date_str) {
		this.create_date_str = create_date_str;
	}
	public String getDue_date_str() {
		return due_date_str;
	}
	public void setDue_date_str(String due_date_str) {
		this.due_date_str = due_date_str;
	}
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}


}
