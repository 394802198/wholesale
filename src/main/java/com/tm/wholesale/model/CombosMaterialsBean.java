package com.tm.wholesale.model;

import java.io.Serializable;
import java.util.List;

public class CombosMaterialsBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Page<Combo> page;
	private List<Material> ms;

	public Page<Combo> getPage() {
		return page;
	}

	public void setPage(Page<Combo> page) {
		this.page = page;
	}

	public List<Material> getMs() {
		return ms;
	}

	public void setMs(List<Material> ms) {
		this.ms = ms;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
