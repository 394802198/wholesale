package com.tm.wholesale.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.tm.wholesale.util.TMUtils;

public class OrderDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * TABLE MAPPING PROPERTIES
	 */

	private Integer id;
	private Integer order_id;
	private String name;
	private String desc;
	private Double price;
	private Double price_enduser;
	private String subscribe;
	private String material_group;
	private String material_type;
	private String material_category;
	private Date plan_start_date;
	private Date plan_end_date;

	private String type;
	private String status;
	private Long data_flow;
	private Integer unit;
	private String number;
	private String password;
	private Date assign_date;
	private Integer calling_mins;
	private String track_code;
	private Boolean is_post;
	private Date expire_date;
	private Integer manager_id;
	private Boolean is_wholesale;
	private Boolean is_enduser;
	private String memo;

	/*
	 * END TABLE MAPPING PROPERTIES
	 */

	/*
	 * RELATED PROPERTIES
	 */
	
	private String plan_start_date_str;
	private String plan_end_date_str;

	private Map<String, Object> params = new HashMap<String, Object>();

	/*
	 * END RELATED PROPERTIES
	 */
	
	public String getMaterial_group() {
		return material_group;
	}

	public void setMaterial_group(String material_group) {
		this.material_group = material_group;
	}

	public String getMaterial_category() {
		return material_category;
	}

	public void setMaterial_category(String material_category) {
		this.material_category = material_category;
	}

	public OrderDetail() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getCalling_mins() {
		return calling_mins;
	}

	public void setCalling_mins(Integer calling_mins) {
		this.calling_mins = calling_mins;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	public String getMaterial_type() {
		return material_type;
	}

	public void setMaterial_type(String material_type) {
		this.material_type = material_type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getAssign_date() {
		return assign_date;
	}

	public void setAssign_date(Date assign_date) {
		this.assign_date = assign_date;
	}

	public String getTrack_code() {
		return track_code;
	}

	public void setTrack_code(String track_code) {
		this.track_code = track_code;
	}

	public Boolean getIs_post() {
		return is_post;
	}

	public void setIs_post(Boolean is_post) {
		this.is_post = is_post;
	}

	public Date getExpire_date() {
		return expire_date;
	}

	public void setExpire_date(Date expire_date) {
		this.expire_date = expire_date;
	}

	public Integer getManager_id() {
		return manager_id;
	}

	public void setManager_id(Integer manager_id) {
		this.manager_id = manager_id;
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public Double getPrice_enduser() {
		return price_enduser;
	}

	public void setPrice_enduser(Double price_enduser) {
		this.price_enduser = price_enduser;
	}

	public Boolean getIs_wholesale() {
		return is_wholesale;
	}

	public void setIs_wholesale(Boolean is_wholesale) {
		this.is_wholesale = is_wholesale;
	}

	public Boolean getIs_enduser() {
		return is_enduser;
	}

	public void setIs_enduser(Boolean is_enduser) {
		this.is_enduser = is_enduser;
	}

	public Long getData_flow() {
		return data_flow;
	}

	public void setData_flow(Long data_flow) {
		this.data_flow = data_flow;
	}

	public String getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}

	public Date getPlan_start_date() {
		return plan_start_date;
	}

	public void setPlan_start_date(Date plan_start_date) {
		this.setPlan_start_date_str(TMUtils.dateFormatYYYYMMDD(this.getPlan_start_date()));
		this.plan_start_date = plan_start_date;
	}

	public Date getPlan_end_date() {
		return plan_end_date;
	}

	public void setPlan_end_date(Date plan_end_date) {
		this.setPlan_end_date_str(TMUtils.dateFormatYYYYMMDD(this.getPlan_end_date()));
		this.plan_end_date = plan_end_date;
	}

	public String getPlan_start_date_str() {
		return plan_start_date_str;
	}

	public void setPlan_start_date_str(String plan_start_date_str) {
		this.plan_start_date_str = plan_start_date_str;
	}

	public String getPlan_end_date_str() {
		return plan_end_date_str;
	}

	public void setPlan_end_date_str(String plan_end_date_str) {
		this.plan_end_date_str = plan_end_date_str;
	}

}
