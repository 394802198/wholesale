package com.tm.wholesale.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tm.wholesale.model.Material;
import com.tm.wholesale.model.MaterialCategory;
import com.tm.wholesale.model.MaterialGroup;
import com.tm.wholesale.model.MaterialType;
import com.tm.wholesale.model.Wholesaler;
import com.tm.wholesale.service.ProductService;
import com.tm.wholesale.service.WholesaleService;

@Controller
public class ProductController {

	private ProductService productService;
	private WholesaleService wholesaleService;

	@Autowired
	public ProductController(ProductService productService, WholesaleService wholesaleService) {
		this.productService = productService;
		this.wholesaleService = wholesaleService;
	}

	/**
	 * BEGIN Material
	 */
	
	@RequestMapping("/management/product/material-combo/view")
	public String toMaterialComboView(Model model){
		model.addAttribute("ms", this.productService.queryMaterials(new Material()));
		return "management/product/material-combo";
	}
	
	@RequestMapping("/management/product/material/create")
	public String toMaterialCreate(Model model) {
		
		List<MaterialGroup> mgs = this.productService.queryMaterialGroups(new MaterialGroup());
		List<MaterialCategory> mcs = this.productService.queryMaterialCategorys(new MaterialCategory());
		
		Wholesaler wQuery = new Wholesaler();
		wQuery.getParams().put("where", "query_primary_wholesaler");
		model.addAttribute("ws", this.wholesaleService.queryWholesalers(wQuery));
		model.addAttribute("mgs", mgs);
		model.addAttribute("mcs", mcs);
		model.addAttribute("m", new Material());
		
		return "management/product/material";
	}
	
	@RequestMapping("/management/product/material/edit/{id}")
	public String toMaterialEdit(Model model,
			@PathVariable("id") Integer id){
		
		List<MaterialGroup> mgs = this.productService.queryMaterialGroups(new MaterialGroup());
		
		// List material by id
		Material mQuery = new Material();
		mQuery.getParams().put("id", id);
		Material m = this.productService.queryMaterials(mQuery).get(0);
		
		// List group by group_name
		MaterialGroup groupQuery = new MaterialGroup();
		groupQuery.getParams().put("group_name", m.getMaterial_group());
		MaterialGroup group = this.productService.queryMaterialGroups(groupQuery).get(0);
		
		// List types by group_id
		MaterialType typeQuery = new MaterialType();
		typeQuery.getParams().put("group_id", group.getId());
		List<MaterialType> mts = this.productService.queryMaterialTypes(typeQuery);

		Wholesaler wQuery = new Wholesaler();
		wQuery.getParams().put("where", "query_primary_wholesaler");
		model.addAttribute("ws", this.wholesaleService.queryWholesalers(wQuery));
		model.addAttribute("mcs", this.productService.queryMaterialCategorys(new MaterialCategory()));
		model.addAttribute("mgs", mgs);
		model.addAttribute("mts", mts);
		model.addAttribute("m", m);
		
		return "management/product/material";
	}
	
	/**
	 * END Material
	 */
	
	
	/**
	 * BEGIN Combo
	 */
	
	/**
	 * END Combo
	 */
	
}
