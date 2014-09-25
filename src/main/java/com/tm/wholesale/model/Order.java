package com.tm.wholesale.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.tm.wholesale.util.TMUtils;
import com.tm.wholesale.validation.OrderSessionInformationValidatedMark;

public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * TABLE MAPPING PROPERTIES
	 */

	private Integer id;
	private Integer group_id;
	private Integer company_id;
	private Integer wholesaler_id;
	private String wholesaler_company_name;
	private Integer manager_id;
	private Double total_price_gst;
	private Double total_price_gst_enduser;
	private Date inservice_date;
	private String status;
	private String customer_type;
	private String broadband_type;
	@NotEmpty(groups = { OrderSessionInformationValidatedMark.class })
	@Length(max = 49, groups = { OrderSessionInformationValidatedMark.class })
	private String transition_provider_name;
	@NotEmpty(groups = { OrderSessionInformationValidatedMark.class })
	@Length(max = 49, groups = { OrderSessionInformationValidatedMark.class })
	private String transition_account_holder_name;
	@NotEmpty(groups = { OrderSessionInformationValidatedMark.class })
	@Length(max = 49, groups = { OrderSessionInformationValidatedMark.class })
	private String transition_account_number;
	@NotEmpty(groups = { OrderSessionInformationValidatedMark.class })
	@Length(max = 49, groups = { OrderSessionInformationValidatedMark.class })
	private String transition_porting_number;
	private String svlan;
	private String cvlan;
	private String pppoe_loginname;
	private String pppoe_password;
	private String order_wholesaler_pdf_path;
	private String order_customer_pdf_path;
	private String memo;
	private String broadband_asid;
	private Date rfs_date;
	private Date disconnected_date;
	private Date preferred_connection_date;
	private String previous_provider_invoice;
	private Date next_invoice_create_date;
	private Date next_invoice_create_date_flag;
	@NotEmpty(groups = { OrderSessionInformationValidatedMark.class })
	@Length(max = 49, groups = { OrderSessionInformationValidatedMark.class })
	private String first_name;
	@NotEmpty(groups = { OrderSessionInformationValidatedMark.class })
	@Length(max = 49, groups = { OrderSessionInformationValidatedMark.class })
	private String last_name;
	private String address;
	@NotEmpty(groups = { OrderSessionInformationValidatedMark.class })
	@Length(max = 49, groups = { OrderSessionInformationValidatedMark.class })
	@Email(groups = { OrderSessionInformationValidatedMark.class })
	private String email;
	private String phone;
	@NotEmpty(groups = { OrderSessionInformationValidatedMark.class })
	@Length(max = 49, groups = { OrderSessionInformationValidatedMark.class })
	private String mobile;
	private Date create_date;
	private String company_name;
	private String trade_name;
	private String title;
	private Integer hardware_post;
	private boolean is_wholesaler_invoice_mobile_notification;
	private boolean is_wholesaler_invoice_email_notification;
	private boolean is_customer_invoice_mobile_notification;
	private boolean is_customer_invoice_email_notification;


	/*
	 * END TABLE MAPPING PROPERTIES
	 */

	/*
	 * RELATED PROPERTIES
	 */

	private String wholesaler_name;

	private String create_date_str;
	private String inservice_date_str;
	private String rfs_date_str;
	private String disconnected_date_str;
	private String preferred_connection_date_str;
	private String next_invoice_create_date_str;
	private String service_type;

	private Map<String, Object> params = new HashMap<String, Object>();
	private Broadband broadband = new Broadband();
	private List<OrderDetail> ods = new ArrayList<OrderDetail>();
	private OrderLog ol = new OrderLog();

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

	public Date getInservice_date() {
		return inservice_date;
	}

	public void setInservice_date(Date inservice_date) {
		this.setInservice_date_str(TMUtils.dateFormatYYYYMMDD(inservice_date));
		this.inservice_date = inservice_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCustomer_type() {
		return customer_type;
	}

	public void setCustomer_type(String customer_type) {
		this.customer_type = customer_type;
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

	public String getOrder_wholesaler_pdf_path() {
		return order_wholesaler_pdf_path;
	}

	public void setOrder_wholesaler_pdf_path(String order_wholesaler_pdf_path) {
		this.order_wholesaler_pdf_path = order_wholesaler_pdf_path;
	}

	public String getOrder_customer_pdf_path() {
		return order_customer_pdf_path;
	}

	public void setOrder_customer_pdf_path(String order_customer_pdf_path) {
		this.order_customer_pdf_path = order_customer_pdf_path;
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
		this.setRfs_date_str(TMUtils.dateFormatYYYYMMDD(rfs_date));
		this.rfs_date = rfs_date;
	}

	public Date getDisconnected_date() {
		return disconnected_date;
	}

	public void setDisconnected_date(Date disconnected_date) {
		this.setDisconnected_date_str(TMUtils.dateFormatYYYYMMDD(disconnected_date));
		this.disconnected_date = disconnected_date;
	}

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
	}

	public Integer getManager_id() {
		return manager_id;
	}

	public void setManager_id(Integer manager_id) {
		this.manager_id = manager_id;
	}

	public Date getPreferred_connection_date() {
		return preferred_connection_date;
	}

	public void setPreferred_connection_date(Date preferred_connection_date) {
		this.setPreferred_connection_date_str(TMUtils.dateFormatYYYYMMDD(preferred_connection_date));
		this.preferred_connection_date = preferred_connection_date;
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
		this.setNext_invoice_create_date_str(TMUtils.dateFormatYYYYMMDD(next_invoice_create_date));
		this.next_invoice_create_date = next_invoice_create_date;
	}

	public Date getNext_invoice_create_date_flag() {
		return next_invoice_create_date_flag;
	}

	public void setNext_invoice_create_date_flag(Date next_invoice_create_date_flag) {
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
		this.setCreate_date_str(TMUtils.dateFormatYYYYMMDDHHMMSS(create_date));
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

	public List<OrderDetail> getOds() {
		return ods;
	}

	public void setOds(List<OrderDetail> ods) {
		this.ods = ods;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWholesaler_company_name() {
		return wholesaler_company_name;
	}

	public void setWholesaler_company_name(String wholesaler_company_name) {
		this.wholesaler_company_name = wholesaler_company_name;
	}

	public Integer getHardware_post() {
		return hardware_post;
	}

	public void setHardware_post(Integer hardware_post) {
		this.hardware_post = hardware_post;
	}

	public String getCreate_date_str() {
		return this.create_date_str;
	}

	public void setCreate_date_str(String create_date_str) {
		this.create_date_str = create_date_str;
	}

	public String getInservice_date_str() {
		return inservice_date_str;
	}

	public void setInservice_date_str(String inservice_date_str) {
		this.inservice_date_str = inservice_date_str;
	}

	public Double getTotal_price_gst() {
		return total_price_gst;
	}

	public void setTotal_price_gst(Double total_price_gst) {
		this.total_price_gst = total_price_gst;
	}

	public Double getTotal_price_gst_enduser() {
		return total_price_gst_enduser;
	}

	public void setTotal_price_gst_enduser(Double total_price_gst_enduser) {
		this.total_price_gst_enduser = total_price_gst_enduser;
	}

	public OrderLog getOl() {
		return ol;
	}

	public void setOl(OrderLog ol) {
		this.ol = ol;
	}

	public String getPreferred_connection_date_str() {
		return preferred_connection_date_str;
	}

	public void setPreferred_connection_date_str(String preferred_connection_date_str) {
		this.preferred_connection_date_str = preferred_connection_date_str;
	}

	public String getWholesaler_name() {
		return wholesaler_name;
	}

	public void setWholesaler_name(String wholesaler_name) {
		this.wholesaler_name = wholesaler_name;
	}

	public String getRfs_date_str() {
		return rfs_date_str;
	}

	public void setRfs_date_str(String rfs_date_str) {
		this.rfs_date_str = rfs_date_str;
	}

	public String getDisconnected_date_str() {
		return disconnected_date_str;
	}

	public void setDisconnected_date_str(String disconnected_date_str) {
		this.disconnected_date_str = disconnected_date_str;
	}

	public String getNext_invoice_create_date_str() {
		return next_invoice_create_date_str;
	}

	public void setNext_invoice_create_date_str(String next_invoice_create_date_str) {
		this.next_invoice_create_date_str = next_invoice_create_date_str;
	}

	public boolean isIs_wholesaler_invoice_mobile_notification() {
		return is_wholesaler_invoice_mobile_notification;
	}

	public void setIs_wholesaler_invoice_mobile_notification(
			boolean is_wholesaler_invoice_mobile_notification) {
		this.is_wholesaler_invoice_mobile_notification = is_wholesaler_invoice_mobile_notification;
	}

	public boolean isIs_wholesaler_invoice_email_notification() {
		return is_wholesaler_invoice_email_notification;
	}

	public void setIs_wholesaler_invoice_email_notification(
			boolean is_wholesaler_invoice_email_notification) {
		this.is_wholesaler_invoice_email_notification = is_wholesaler_invoice_email_notification;
	}

	public boolean isIs_customer_invoice_mobile_notification() {
		return is_customer_invoice_mobile_notification;
	}

	public void setIs_customer_invoice_mobile_notification(
			boolean is_customer_invoice_mobile_notification) {
		this.is_customer_invoice_mobile_notification = is_customer_invoice_mobile_notification;
	}

	public boolean isIs_customer_invoice_email_notification() {
		return is_customer_invoice_email_notification;
	}

	public void setIs_customer_invoice_email_notification(
			boolean is_customer_invoice_email_notification) {
		this.is_customer_invoice_email_notification = is_customer_invoice_email_notification;
	}

	public Integer getGroup_id() {
		return group_id;
	}

	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}
	
	public String getService_type() {
		return service_type;
	}

	public void setService_type(String service_type) {
		this.service_type = service_type;
	}

}
