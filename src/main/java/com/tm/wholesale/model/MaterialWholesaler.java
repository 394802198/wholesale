package com.tm.wholesale.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MaterialWholesaler implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * TABLE MAPPING PROPERTIES
	 */

	private Integer id;
	private Integer material_id;
	private String name;
	private String material_group;
	private String material_type;
	private String material_category;
	private String description;
	private Double wholesale_price;
	private Integer wholesaler_id;
	private String status;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getWholesaler_id() {
		return wholesaler_id;
	}

	public void setWholesaler_id(Integer wholesaler_id) {
		this.wholesaler_id = wholesaler_id;
	}

	public Double getWholesale_price() {
		return wholesale_price;
	}

	public void setWholesale_price(Double wholesale_price) {
		this.wholesale_price = wholesale_price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getMaterial_id() {
		return material_id;
	}

	public void setMaterial_id(Integer material_id) {
		this.material_id = material_id;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
}
