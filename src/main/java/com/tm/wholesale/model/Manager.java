package com.tm.wholesale.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.tm.wholesale.validation.ManagerLoginValidatedMark;

public class Manager implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/*
	 * TABLE MAPPING PROPERTIES
	 */
	
	private Integer id;
	@NotEmpty(groups = { ManagerLoginValidatedMark.class })
	@Length(min = 0, max = 20, groups = { ManagerLoginValidatedMark.class })
	private String login_name;
	@NotEmpty(groups = { ManagerLoginValidatedMark.class })
	@Length(min = 0, max = 20, groups = { ManagerLoginValidatedMark.class })
	private String password;
	private String username;
	private String role;
	private String mobile;
	private String email;
	private String auth;

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

	public String getLogin_name() {
		return login_name;
	}

	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
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
