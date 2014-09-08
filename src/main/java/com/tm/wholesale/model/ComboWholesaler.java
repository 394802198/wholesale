package com.tm.wholesale.model;

import java.io.Serializable;

public class ComboWholesaler implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * TABLE MAPPING PROPERTIES
	 */

	private Integer id;
	private Integer combo_id;
	private String name;
	private String material_ids;
	private Integer wholesaler_id;
	private String status;

	/*
	 * END TABLE MAPPING PROPERTIES
	 */

	/*
	 * RELATED PROPERTIES
	 */

//	private Map<String, Object> params = new HashMap<String, Object>();

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

	public Integer getWholesaler_id() {
		return wholesaler_id;
	}

	public void setWholesaler_id(Integer wholesaler_id) {
		this.wholesaler_id = wholesaler_id;
	}

	public Integer getCombo_id() {
		return combo_id;
	}

	public void setCombo_id(Integer combo_id) {
		this.combo_id = combo_id;
	}

	public String getMaterial_ids() {
		return material_ids;
	}

	public void setMaterial_ids(String material_ids) {
		this.material_ids = material_ids;
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
}
