package com.tm.wholesale.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class OrderGroup implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * TABLE MAPPING PROPERTIES
	 */
	
	private Integer id;
	private String first_name;
	private String last_name;
	private String email;
	private String mobile;
	private String is_send_invoice_statement;
	private String is_send_invoice_together;

	/*
	 * END TABLE MAPPING PROPERTIES
	 */

	/*
	 * RELATED PROPERTIES
	 */

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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getIs_send_invoice_statement() {
		return is_send_invoice_statement;
	}


	public void setIs_send_invoice_statement(String is_send_invoice_statement) {
		this.is_send_invoice_statement = is_send_invoice_statement;
	}


	public String getIs_send_invoice_together() {
		return is_send_invoice_together;
	}


	public void setIs_send_invoice_together(String is_send_invoice_together) {
		this.is_send_invoice_together = is_send_invoice_together;
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
