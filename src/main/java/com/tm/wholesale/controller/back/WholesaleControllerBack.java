package com.tm.wholesale.controller.back;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tm.wholesale.model.Combo;
import com.tm.wholesale.model.ComboWholesaler;
import com.tm.wholesale.model.Material;
import com.tm.wholesale.model.MaterialWholesaler;
import com.tm.wholesale.model.Wholesaler;
import com.tm.wholesale.service.back.ProductServiceBack;
import com.tm.wholesale.service.back.WholesaleServiceBack;

@Controller
public class WholesaleControllerBack {

	private ProductServiceBack productService;
	private WholesaleServiceBack wholesaleService;

	@Autowired
	public WholesaleControllerBack(ProductServiceBack productService, WholesaleServiceBack wholesaleService) {
		this.productService = productService;
		this.wholesaleService = wholesaleService;
	}

	/**
	 * BEGIN Wholesaler
	 */

	@RequestMapping(value = "/management/wholesale/wholesaler/create")
	public String toWholesaleCreate(Model model) {
		
		model.addAttribute("wholesaler", new Wholesaler());
		model.addAttribute("ms", this.productService.queryMaterials(new Material()));
		model.addAttribute("cs", this.productService.queryCombos(new Combo()));
		model.addAttribute("mcs", this.productService.queryMaterialCategories());
		model.addAttribute("panelheading", "Wholesale Create");
		model.addAttribute("action", "/management/wholesale/wholesaler/create");

		return "management/wholesale/wholesaler";
	}

	@RequestMapping(value = "/management/wholesale/wholesaler/edit/{id}")
	public String toWholesaleEdit(Model model,
			@PathVariable("id") Integer id) {
		
		Wholesaler wholesaler = new Wholesaler();
		wholesaler.getParams().put("id", id);
		wholesaler = this.wholesaleService.queryWholesalers(wholesaler).get(0);
		
		MaterialWholesaler mwQuery = new MaterialWholesaler();
		mwQuery.getParams().put("company_id", id);
		List<MaterialWholesaler> mws = this.productService.queryMaterialWholesalers(mwQuery);
		String[] midArr = new String[mws.size()];
		for (int i = 0; i < midArr.length; i++) {
			/**
			 * SET MaterialWholesaler Id &
			 */
			midArr[i] = String.valueOf(mws.get(i).getMaterial_id());
			/**
			 * SET Materials Individually to MaterialWholesaler Map
			 */
			wholesaler.getMwMaps().put(mws.get(i).getMaterial_id(), mws.get(i));
		}
		wholesaler.setMidArr(midArr);
		
		ComboWholesaler cwQuery = new ComboWholesaler();
		cwQuery.getParams().put("company_id", id);
		List<ComboWholesaler> cws = this.productService.queryComboWholesalers(cwQuery);
		
		String[] cidArr = new String[cws.size()];
		for (int i = 0; i < cidArr.length; i++) {
			cidArr[i] = String.valueOf(cws.get(i).getCombo_id());
			/**
			 * SET Combos Individually to ComboWholesaler Map
			 */
			wholesaler.getCwMaps().put(cws.get(i).getCombo_id(), cws.get(i));
		}
		wholesaler.setCidArr(cidArr);
		
		model.addAttribute("wholesaler", wholesaler);
		model.addAttribute("ms", this.productService.queryMaterials(new Material()));
		model.addAttribute("cs", this.productService.queryCombos(new Combo()));
		model.addAttribute("mcs", this.productService.queryMaterialCategories());
		model.addAttribute("panelheading", "Wholesale Update");
		model.addAttribute("action", "/management/wholesale/wholesaler/edit");

		return "management/wholesale/wholesaler";
	}

	@RequestMapping(value = "/management/wholesale/wholesaler/view")
	public String toWholesaleView(Model model) {
		return "management/wholesale/wholesaler-view";
	}
	
	/**
	 * END Wholesaler
	 */
	
}
