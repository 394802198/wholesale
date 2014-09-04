package com.tm.wholesale.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tm.wholesale.model.Combo;
import com.tm.wholesale.model.JSONBean;
import com.tm.wholesale.model.Material;
import com.tm.wholesale.model.MaterialCategory;
import com.tm.wholesale.model.MaterialGroup;
import com.tm.wholesale.model.MaterialType;
import com.tm.wholesale.model.Page;
import com.tm.wholesale.service.ProductService;
import com.tm.wholesale.test.Console;

@RestController
@SessionAttributes("materialFilter")
public class ProductRestController {

	private ProductService productService;

	@Autowired
	public ProductRestController(ProductService productService) {
		this.productService = productService;
	}
	
	/**
	 * BEGIN MaterialGroup
	 */	
	@RequestMapping(value="/management/product/material/group-type/create", method=RequestMethod.POST)
	public JSONBean<String> doMaterialGroupCreate(Model model,
			@RequestParam("group_id") String group_id,
			@RequestParam("group_or_type") String group_or_type,
			@RequestParam("group_or_type_name") String group_or_type_name) {
		
		JSONBean<String> json = new JSONBean<String>();
		
		if("group".equals(group_or_type)){
			MaterialGroup group = new MaterialGroup();
			group.getParams().put("group_name", group_or_type_name);
			if(this.productService.queryMaterialGroups(group)!=null && this.productService.queryMaterialGroups(group).size() > 0){
				json.getErrorMap().put("alert-error", "Group name existed, can't be recreate!");
				return json;
			}
			group.setGroup_name(group_or_type_name);
			this.productService.createMaterialGroup(group);
		} else if("type".equals(group_or_type)) {
			MaterialType type = new MaterialType();
			type.getParams().put("type_name", group_or_type_name);
			if(this.productService.queryMaterialTypes(type)!=null && this.productService.queryMaterialTypes(type).size() > 0){
				json.getErrorMap().put("alert-error", "Type name existed, can't be recreate!");
				return json;
			}
			type.setGroup_id(Integer.parseInt(group_id));
			type.setType_name(group_or_type_name);
			this.productService.createMaterialType(type);
		} else {
			MaterialCategory category = new MaterialCategory();
			category.getParams().put("category_name", group_or_type_name);
			if(this.productService.queryMaterialCategorys(category)!=null && this.productService.queryMaterialCategorys(category).size() > 0){
				json.getErrorMap().put("alert-error", "Category name existed, can't be recreate!");
				return json;
			}
			category.setCategory_name(group_or_type_name);
			this.productService.createMaterialCategory(category);
		}
		
		
		json.getSuccessMap().put("alert-success", "New Material "+group_or_type+" has just been created!");
		
		return json;
	}
	
	@RequestMapping(value="/management/product/material/group-type/edit", method=RequestMethod.POST)
	public JSONBean<MaterialType> doMaterialGroupTypeEdit(Model model,
			MaterialType mt) {
		
		JSONBean<MaterialType> json = new JSONBean<MaterialType>();
		
		this.productService.createMaterialType(mt);
		
		json.getSuccessMap().put("alert-success", "New Material Type has just been created!");
		
		return json;
	}

	@RequestMapping(value="/management/product/material/groups/show")
	public JSONBean<MaterialGroup> showMaterialGroups(Model model) {
		
		JSONBean<MaterialGroup> json = new JSONBean<MaterialGroup>();
		
		List<MaterialGroup> groups = this.productService.queryMaterialGroups(new MaterialGroup());
		
		json.setModels(groups);
		
		return json;
	}
	/**
	 * END MaterialGroup
	 */

	/**
	 * BEGIN MaterialType
	 */
	@RequestMapping("/management/product/material/type/show")
	public JSONBean<MaterialType> showMaterialTypes(Model model,
			@RequestParam("group_id") Integer group_id) {
		
		JSONBean<MaterialType> json = new JSONBean<MaterialType>();
		
		MaterialType type = new MaterialType();
		type.getParams().put("group_id", group_id);
		
		List<MaterialType> types = this.productService.queryMaterialTypes(type);
		
		json.setModels(types);
		
		return json;
	}
	/**
	 * END MaterialType
	 */

	/**
	 * BEGIN Material
	 */
	
	@RequestMapping(value="/management/product/material/update_create",method=RequestMethod.POST)
	public JSONBean<Material> doMaterialCreate(Model model,
			Material m,
			@RequestParam("submit_type") String submit_type,
			@RequestParam("material_group_id") Integer material_group_id){
		
		JSONBean<Material> json = new JSONBean<Material>();
		
		if(m.getName()!=null && !m.getName().trim().equals("")){
			
			if("All".equals(m.getMaterial_group())){
				MaterialGroup groupQuery = new MaterialGroup();
				groupQuery.getParams().put("id", material_group_id);
				m.setMaterial_group(this.productService.queryMaterialGroups(groupQuery).get(0).getGroup_name());
			}
			
			if("update".equals(submit_type)){
				m.getParams().put("id", m.getId());
				this.productService.editMaterial(m);
			} else {
				this.productService.createMaterial(m);
			}
			
			json.getSuccessMap().put("alert-success", "Successfully "+("update".equals(submit_type)?"update current":"create new")+" material!");
			
		} else {

			json.getErrorMap().put("alert-error", "Please fill essential detail(s) for the material!");
			
		}
		
		return json;
	}
	
	@RequestMapping("/management/product/material/view/{pageNo}")
	public Page<Material> toMaterialView(Model model,
			@PathVariable("pageNo") Integer pageNo,
			HttpServletRequest req){
		
		Page<Material> page = new Page<Material>();
		page.setPageNo(pageNo);
		page.setPageSize(30);
		page.getParams().put("orderby", "ORDER BY material_category ASC");
		
		Material materialFilter = (Material) req.getSession().getAttribute("materialFilter");
		if (materialFilter != null) {
			if (!"".equals(materialFilter.getMaterial_group()))
				page.getParams().put("material_group", materialFilter.getMaterial_group());
			if (!"".equals(materialFilter.getMaterial_category()))
				page.getParams().put("material_category", materialFilter.getMaterial_category());
		}
		
		this.productService.queryMaterialsByPage(page);
		
		return page;
	}
	
	@RequestMapping(value = "/management/product/material/view/filter")
	public void doMaterialViewFilter(Model model, Material material) {
		model.addAttribute("materialFilter", material);
		System.out.println("do material filter");
	}
	
	@RequestMapping(value = "/management/product/material/delete", method = RequestMethod.POST)
	public JSONBean<Material> deleteMaterials(Model model,
			@RequestParam(value = "material_ids", required = false) String[] material_ids) {
		
		JSONBean<Material> json = new JSONBean<Material>();

		if (material_ids==null || material_ids.length==0) {
			json.getErrorMap().put("alert-error", "Please choose one material at least.");
			return json;
		} else {
			this.productService.removeMaterialsById(material_ids);
			json.getSuccessMap().put("alert-success", "Selected material(s) has(have) been removed!");
		}
		
		return json;
	}

	@RequestMapping(value = "/management/product/material/material-combine", method = RequestMethod.POST)
	public JSONBean<Material> combineMaterialsByIds(Model model,
			 @RequestBody Combo c) {
		
		JSONBean<Material> json = new JSONBean<Material>();
		
		if (c.getMaterial_ids()==null || "".equals(c.getMaterial_ids())) {
			json.getErrorMap().put("alert-error", "Please choose one material at least.");
			return json;
		} else if (c.getName()==null || "".equals(c.getName())){
			json.getErrorMap().put("alert-error", "Please give combination a name.");
			return json;
		} else {
			this.productService.createCombo(c);
			json.getSuccessMap().put("alert-success", "Combo has been created!");
		}
		return json;
	}
	
	/**
	 * END Material
	 */

	
	/**
	 * BEGIN Combo
	 */
	
	@RequestMapping("/management/product/combo/view/{pageNo}")
	public Page<Combo> toComboView(Model model,
			@PathVariable("pageNo") Integer pageNo){
		
		Page<Combo> page = new Page<Combo>();
		page.setPageNo(pageNo);
		page.setPageSize(30);
		
		this.productService.queryCombosByPage(page);
		
		return page;
	}
	
	@RequestMapping(value = "/management/product/combo/delete", method = RequestMethod.POST)
	public JSONBean<Combo> deleteCombos(
			Model model, @RequestParam(value = "combo_ids", required = false) String[] combo_ids,
			HttpServletRequest req, RedirectAttributes attr) {
		
		JSONBean<Combo> json = new JSONBean<Combo>();
		
		if (combo_ids==null || combo_ids.length==0) {
			json.getErrorMap().put("alert-error", "Please choose one combo at least.");
			return json;
		} else {
			this.productService.removeCombosById(combo_ids);
			json.getSuccessMap().put("alert-success", "Selected combo(s) has(have) been removed!");
		}
		return json;
	}
	
	/**
	 * END Combo
	 */
}
