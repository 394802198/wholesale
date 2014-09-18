package com.tm.wholesale.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MaterialCategory implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * TABLE MAPPING PROPERTIES
	 */
	
	private Integer id;
	private String category_name;
	private String upper_category_name;

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
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	public String getUpper_category_name() {
		return upper_category_name;
	}
	public void setUpper_category_name(String upper_category_name) {
		this.upper_category_name = upper_category_name;
	}

}
