package com.tm.wholesale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tm.wholesale.model.JSONBean;
import com.tm.wholesale.model.Page;
import com.tm.wholesale.model.Combo;
import com.tm.wholesale.model.Material;
import com.tm.wholesale.model.MaterialCategory;
import com.tm.wholesale.model.MaterialGroup;
import com.tm.wholesale.model.MaterialType;
import com.tm.wholesale.service.MaterialService;

@RestController
public class MaterialRestController {

	private MaterialService MaterialService;

	@Autowired
	public MaterialRestController(MaterialService MaterialService) {
		this.MaterialService = MaterialService;
	}
	
	/**
	 * BEGIN MaterialGroup
	 */	
	@RequestMapping(value="/management/material/group-type/create", method=RequestMethod.POST)
	public JSONBean<String> doMaterialGroupCreate(Model model,
			@RequestParam("group_id") String group_id,
			@RequestParam("group_or_type") String group_or_type,
			@RequestParam("group_or_type_name") String group_or_type_name) {
		
		JSONBean<String> json = new JSONBean<String>();
		
		if("group".equals(group_or_type)){
			MaterialGroup group = new MaterialGroup();
			group.getParams().put("group_name", group_or_type_name);
			if(this.MaterialService.queryMaterialGroups(group)!=null && this.MaterialService.queryMaterialGroups(group).size() > 0){
				json.getErrorMap().put("alert-error", "Group name existed, can't be recreate!");
				return json;
			}
			group.setGroup_name(group_or_type_name);
			this.MaterialService.createMaterialGroup(group);
		} else if("type".equals(group_or_type)) {
			MaterialType type = new MaterialType();
			type.getParams().put("type_name", group_or_type_name);
			if(this.MaterialService.queryMaterialTypes(type)!=null && this.MaterialService.queryMaterialTypes(type).size() > 0){
				json.getErrorMap().put("alert-error", "Type name existed, can't be recreate!");
				return json;
			}
			type.setGroup_id(Integer.parseInt(group_id));
			type.setType_name(group_or_type_name);
			this.MaterialService.createMaterialType(type);
		} else {
			MaterialCategory category = new MaterialCategory();
			category.getParams().put("category_name", group_or_type_name);
			if(this.MaterialService.queryMaterialCategorys(category)!=null && this.MaterialService.queryMaterialCategorys(category).size() > 0){
				json.getErrorMap().put("alert-error", "Category name existed, can't be recreate!");
				return json;
			}
			category.setCategory_name(group_or_type_name);
			this.MaterialService.createMaterialCategory(category);
		}
		
		
		json.getSuccessMap().put("alert-success", "New Material "+group_or_type+" has just been created!");
		
		return json;
	}
	
	@RequestMapping(value="/management/material/group-type/edit", method=RequestMethod.POST)
	public JSONBean<MaterialType> doMaterialGroupTypeEdit(Model model,
			MaterialType mt) {
		
		JSONBean<MaterialType> json = new JSONBean<MaterialType>();
		
		this.MaterialService.createMaterialType(mt);
		
		json.getSuccessMap().put("alert-success", "New Material Type has just been created!");
		
		return json;
	}

	@RequestMapping(value="/management/material/groups/show")
	public JSONBean<MaterialGroup> showMaterialGroups(Model model) {
		
		JSONBean<MaterialGroup> json = new JSONBean<MaterialGroup>();
		
		List<MaterialGroup> groups = this.MaterialService.queryMaterialGroups(new MaterialGroup());
		
		json.setModels(groups);
		
		return json;
	}
	/**
	 * END MaterialGroup
	 */

	/**
	 * BEGIN MaterialType
	 */
	@RequestMapping("/management/material/type/show")
	public JSONBean<MaterialType> showMaterialTypes(Model model,
			@RequestParam("group_id") Integer group_id) {
		
		JSONBean<MaterialType> json = new JSONBean<MaterialType>();
		
		MaterialType type = new MaterialType();
		type.getParams().put("group_id", group_id);
		
		List<MaterialType> types = this.MaterialService.queryMaterialTypes(type);
		
		json.setModels(types);
		
		return json;
	}
	/**
	 * END MaterialType
	 */

	
	/**
	 * BEGIN Material
	 */
	
	@RequestMapping(value="/management/material/update_create",method=RequestMethod.POST)
	public JSONBean<Material> doMaterialCreate(Model model,
			Material m,
			@RequestParam("submit_type") String submit_type,
			@RequestParam("material_group_id") Integer material_group_id){
		
		JSONBean<Material> json = new JSONBean<Material>();
		
		if(m.getName().trim().equals("")){
			
			if("All".equals(m.getMaterial_group())){
				MaterialGroup groupQuery = new MaterialGroup();
				groupQuery.getParams().put("id", material_group_id);
				m.setMaterial_group(this.MaterialService.queryMaterialGroups(groupQuery).get(0).getGroup_name());
			}
			
			if("update".equals(submit_type)){
				m.getParams().put("id", m.getId());
				this.MaterialService.editMaterial(m);
			} else {
				this.MaterialService.createMaterial(m);
			}
			
			json.getSuccessMap().put("alert-success", "Successfully "+("update".equals(submit_type)?"update current":"create new")+" material!");
			
		} else {

			json.getErrorMap().put("alert-error", "Please fill essential detail(s) for the material!");
			
		}
		
		return json;
	}
	
	@RequestMapping("/management/material/view/{pageNo}")
	public Page<Material> toMaterialView(Model model,
			@PathVariable("pageNo") Integer pageNo){
		
		Page<Material> page = new Page<Material>();
		page.setPageNo(pageNo);
		page.setPageSize(30);
		page.getParams().put("orderby", "ORDER BY material_category ASC");
		
		this.MaterialService.queryMaterialsByPage(page);
		
		return page;
	}
	
	@RequestMapping("/management/material/combo/view/{pageNo}")
	public Page<Combo> toComboView(Model model,
			@PathVariable("pageNo") Integer pageNo){
		
		Page<Combo> page = new Page<Combo>();
		page.setPageNo(pageNo);
		page.setPageSize(30);
		
		this.MaterialService.queryCombosByPage(page);
		
		return page;
	}
	
	/**
	 * END Material
	 */
	
}
