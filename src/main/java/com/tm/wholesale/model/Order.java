package com.tm.wholesale.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * TABLE MAPPING PROPERTIES
	 */

	private Integer id;
	private Integer wholesaler_id;
	private Integer maker_id;
	private Double total_price;
	private Date inservice_date;
	private String status;
	private String type;
	private String broadband_type;
	private String transition_provider_name;
	private String transition_account_holder_name;
	private String transition_account_number;
	private String transition_porting_number;
	private String svlan;
	private String cvlan;
	private String pppoe_loginname;
	private String pppoe_password;
	private String order_pdf_path;
	private String memo;
	private String broadband_asid;
	private Date rfs_date;
	private Date disconnected_date;
	private String connection_date;
	private String previous_provider_invoice;
	private Date next_invoice_create_date;
	private Date next_invoice_create_date_flag;
	private String first_name;
	private String last_name;
	private String address;
	private String email;
	private String phone;
	private String mobile;
	private Date create_date;
	private String company_name;
	private String trade_name;

	/*
	 * END TABLE MAPPING PROPERTIES
	 */

	/*
	 * RELATED PROPERTIES
	 */

	private Map<String, Object> params = new HashMap<String, Object>();
	private Broadband broadband = new Broadband();
	
	/*
	 * END RELATED PROPERTIES
	 */

	public Order() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWholesaler_id() {
		return wholesaler_id;
	}

	public void setWholesaler_id(Integer wholesaler_id) {
		this.wholesaler_id = wholesaler_id;
	}

	public Integer getMaker_id() {
		return maker_id;
	}

	public void setMaker_id(Integer maker_id) {
		this.maker_id = maker_id;
	}

	public Double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Double total_price) {
		this.total_price = total_price;
	}

	public Date getInservice_date() {
		return inservice_date;
	}

	public void setInservice_date(Date inservice_date) {
		this.inservice_date = inservice_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBroadband_type() {
		return broadband_type;
	}

	public void setBroadband_type(String broadband_type) {
		this.broadband_type = broadband_type;
	}

	public String getTransition_provider_name() {
		return transition_provider_name;
	}

	public void setTransition_provider_name(String transition_provider_name) {
		this.transition_provider_name = transition_provider_name;
	}

	public String getTransition_account_holder_name() {
		return transition_account_holder_name;
	}

	public void setTransition_account_holder_name(
			String transition_account_holder_name) {
		this.transition_account_holder_name = transition_account_holder_name;
	}

	public String getTransition_account_number() {
		return transition_account_number;
	}

	public void setTransition_account_number(String transition_account_number) {
		this.transition_account_number = transition_account_number;
	}

	public String getTransition_porting_number() {
		return transition_porting_number;
	}

	public void setTransition_porting_number(String transition_porting_number) {
		this.transition_porting_number = transition_porting_number;
	}

	public String getSvlan() {
		return svlan;
	}

	public void setSvlan(String svlan) {
		this.svlan = svlan;
	}

	public String getCvlan() {
		return cvlan;
	}

	public void setCvlan(String cvlan) {
		this.cvlan = cvlan;
	}

	public String getPppoe_loginname() {
		return pppoe_loginname;
	}

	public void setPppoe_loginname(String pppoe_loginname) {
		this.pppoe_loginname = pppoe_loginname;
	}

	public String getPppoe_password() {
		return pppoe_password;
	}

	public void setPppoe_password(String pppoe_password) {
		this.pppoe_password = pppoe_password;
	}

	public String getOrder_pdf_path() {
		return order_pdf_path;
	}

	public void setOrder_pdf_path(String order_pdf_path) {
		this.order_pdf_path = order_pdf_path;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getBroadband_asid() {
		return broadband_asid;
	}

	public void setBroadband_asid(String broadband_asid) {
		this.broadband_asid = broadband_asid;
	}

	public Date getRfs_date() {
		return rfs_date;
	}

	public void setRfs_date(Date rfs_date) {
		this.rfs_date = rfs_date;
	}

	public Date getDisconnected_date() {
		return disconnected_date;
	}

	public void setDisconnected_date(Date disconnected_date) {
		this.disconnected_date = disconnected_date;
	}

	public String getConnection_date() {
		return connection_date;
	}

	public void setConnection_date(String connection_date) {
		this.connection_date = connection_date;
	}

	public String getPrevious_provider_invoice() {
		return previous_provider_invoice;
	}

	public void setPrevious_provider_invoice(String previous_provider_invoice) {
		this.previous_provider_invoice = previous_provider_invoice;
	}

	public Date getNext_invoice_create_date() {
		return next_invoice_create_date;
	}

	public void setNext_invoice_create_date(Date next_invoice_create_date) {
		this.next_invoice_create_date = next_invoice_create_date;
	}

	public Date getNext_invoice_create_date_flag() {
		return next_invoice_create_date_flag;
	}

	public void setNext_invoice_create_date_flag(
			Date next_invoice_create_date_flag) {
		this.next_invoice_create_date_flag = next_invoice_create_date_flag;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getTrade_name() {
		return trade_name;
	}

	public void setTrade_name(String trade_name) {
		this.trade_name = trade_name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public Broadband getBroadband() {
		return broadband;
	}

	public void setBroadband(Broadband broadband) {
		this.broadband = broadband;
	}
	
	
	
}
