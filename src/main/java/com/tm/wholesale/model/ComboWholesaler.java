package com.tm.wholesale.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComboWholesaler implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * TABLE MAPPING PROPERTIES
	 */

	private Integer id;
	private Integer combo_id;
	private String name;
	private String description;
	private String material_ids;
	private Integer company_id;
	private String status;

	/*
	 * END TABLE MAPPING PROPERTIES
	 */

	/*
	 * RELATED PROPERTIES
	 */

	private Map<String, Object> params = new HashMap<String, Object>();
	private List<MaterialWholesaler> materials = new ArrayList<MaterialWholesaler>();

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

	public Integer getCompany_id() {
		return company_id;
	}

	public void setCompany_id(Integer company_id) {
		this.company_id = company_id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public List<MaterialWholesaler> getMaterials() {
		return materials;
	}

	public void setMaterials(List<MaterialWholesaler> materials) {
		this.materials = materials;
	}
}
