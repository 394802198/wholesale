package com.tm.wholesale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tm.wholesale.model.Material;
import com.tm.wholesale.model.MaterialCategory;
import com.tm.wholesale.model.MaterialGroup;
import com.tm.wholesale.model.MaterialType;
import com.tm.wholesale.model.Wholesaler;

import com.tm.wholesale.service.MaterialService;
import com.tm.wholesale.service.SystemService;

@Controller
public class MaterialController {

	private MaterialService MaterialService;
	private SystemService SystemService;

	@Autowired
	public MaterialController(MaterialService MaterialService, SystemService SystemService) {
		this.MaterialService = MaterialService;
		this.SystemService = SystemService;
	}

	/**
	 * BEGIN Material
	 */
	
	@RequestMapping("/management/material/material-combo/view")
	public String toMaterialComboView(Model model){
		
		return "management/material/material-combo";
	}
	
	@RequestMapping("/management/material/create")
	public String toMaterialCreate(Model model) {
		
		List<MaterialGroup> mgs = this.MaterialService.queryMaterialGroups(new MaterialGroup());
		List<MaterialCategory> mcs = this.MaterialService.queryMaterialCategorys(new MaterialCategory());
		
		Wholesaler wQuery = new Wholesaler();
		wQuery.getParams().put("where", "query_primary_wholesaler");
		model.addAttribute("ws", this.SystemService.queryWholesalers(wQuery));
		model.addAttribute("mgs", mgs);
		model.addAttribute("mcs", mcs);
		model.addAttribute("m", new Material());
		
		return "management/material/material";
	}
	
	@RequestMapping("/management/material/edit/{id}")
	public String toMaterialEdit(Model model,
			@PathVariable("id") Integer id){
		
		List<MaterialGroup> mgs = this.MaterialService.queryMaterialGroups(new MaterialGroup());
		
		// List material by id
		Material mQuery = new Material();
		mQuery.getParams().put("id", id);
		Material m = this.MaterialService.queryMaterials(mQuery).get(0);
		
		// List group by group_name
		MaterialGroup groupQuery = new MaterialGroup();
		groupQuery.getParams().put("group_name", m.getMaterial_group());
		MaterialGroup group = this.MaterialService.queryMaterialGroups(groupQuery).get(0);
		
		// List types by group_id
		MaterialType typeQuery = new MaterialType();
		typeQuery.getParams().put("group_id", group.getId());
		List<MaterialType> mts = this.MaterialService.queryMaterialTypes(typeQuery);

		Wholesaler wQuery = new Wholesaler();
		wQuery.getParams().put("where", "query_primary_wholesaler");
		model.addAttribute("ws", this.SystemService.queryWholesalers(wQuery));
		model.addAttribute("mcs", this.MaterialService.queryMaterialCategorys(new MaterialCategory()));
		model.addAttribute("mgs", mgs);
		model.addAttribute("mts", mts);
		model.addAttribute("m", m);
		
		return "management/material/material";
	}
	
	/**
	 * END Material
	 */
	
}
