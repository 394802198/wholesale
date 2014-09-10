package com.tm.wholesale.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tm.wholesale.model.ComboWholesaler;
import com.tm.wholesale.model.JSONBean;
import com.tm.wholesale.model.MaterialWholesaler;
import com.tm.wholesale.model.Page;
import com.tm.wholesale.model.Wholesaler;
import com.tm.wholesale.service.ProductService;
import com.tm.wholesale.service.WholesaleService;

@RestController
public class WholesaleRestController {
	
	private WholesaleService wholesaleService;
	private ProductService productService;

	@Autowired
	public WholesaleRestController(WholesaleService wholesaleService,
			ProductService productService) {
		this.wholesaleService = wholesaleService;
		this.productService = productService;
	}
	
	/**
	 * BEGIN Wholesaler
	 */

	@RequestMapping(value = "/management/wholesale/wholesaler/view/{pageNo}")
	public Page<Wholesaler> toWholesaleView(Model model,
			@PathVariable("pageNo") Integer pageNo,
			HttpServletRequest req) {
		
		Page<Wholesaler> page = new Page<Wholesaler>();
		page.setPageNo(pageNo);
		page.setPageSize(30);
		page.getParams().put("where", "query_primary_wholesalers");
		
		this.wholesaleService.queryWholesalerByPage(page);

		return page;
	}

	@RequestMapping(value = "/management/wholesale/wholesaler/remove/{id}", method=RequestMethod.POST)
	public JSONBean<Wholesaler> doWholesaleRemove(Model model,
			@PathVariable("id") Integer id,
			RedirectAttributes attr) {
		
		JSONBean<Wholesaler> json = new JSONBean<Wholesaler>();
		
		this.wholesaleService.removeWholesalerById(id);
		
		json.getSuccessMap().put("alert-success", "Successfully remove wholesaler account.");

		return json;
	}

	@RequestMapping(value = "/management/wholesale/wholesaler/create", method=RequestMethod.POST)
	public JSONBean<Wholesaler> doWholesaleCreate(Model model,
			@RequestBody Wholesaler wholesaler) {
		
		JSONBean<Wholesaler> json = new JSONBean<Wholesaler>();
		
		if("".equals(wholesaler.getCompany_name().trim())
		|| "".equals(wholesaler.getName().trim())
		|| "".equals(wholesaler.getLogin_name().trim())
		|| "".equals(wholesaler.getLogin_password().trim())){
			json.getErrorMap().put("alert-error", "Please fill essential field(s)!");
			return json;
		}
		
		List<ComboWholesaler> cws = wholesaler.getCws();
		List<MaterialWholesaler> mws = wholesaler.getMws();
		
		this.wholesaleService.createWholesaler(wholesaler);
		
		for (ComboWholesaler cw : cws) {
			cw.setCompany_id(wholesaler.getCompany_id());
			this.productService.createComboWholesaler(cw);
		}
		
		for (MaterialWholesaler mw : mws) {
			mw.setCompany_id(wholesaler.getCompany_id());
			this.productService.createMaterialWholesaler(mw);
		}
		
		json.getSuccessMap().put("alert-success", "New wholesaler has just been created!");

		return json;
	}

	@RequestMapping(value = "/management/wholesale/wholesaler/edit", method=RequestMethod.POST)
	public JSONBean<Wholesaler> doWholesaleEdit(Model model,
			@RequestBody Wholesaler wholesaler,
			RedirectAttributes attr) {
		
		JSONBean<Wholesaler> json = new JSONBean<Wholesaler>();
		
		if("".equals(wholesaler.getCompany_name().trim())
		|| "".equals(wholesaler.getName().trim())
		|| "".equals(wholesaler.getLogin_name().trim())
		|| "".equals(wholesaler.getLogin_password().trim())){
			json.getErrorMap().put("alert-error", "Please fill essential field(s)!");
			return json;
		}
		
		this.productService.removeMaterialWholesalerByWholesalerId(wholesaler.getId());
		this.productService.removeComboWholesalerByWholesalerId(wholesaler.getId());
		
		List<ComboWholesaler> cws = wholesaler.getCws();
		List<MaterialWholesaler> mws = wholesaler.getMws();
		for (ComboWholesaler cw : cws) {
			cw.setCompany_id(wholesaler.getCompany_id());
			this.productService.createComboWholesaler(cw);
		}
		
		for (MaterialWholesaler mw : mws) {
			mw.setCompany_id(wholesaler.getCompany_id());
			this.productService.createMaterialWholesaler(mw);
		}
		
		wholesaler.getParams().put("id", wholesaler.getId());
		this.wholesaleService.editWholesaler(wholesaler);

		json.getSuccessMap().put("alert-success", "Successfully update wholesaler details!");

		return json;
	}
	
	/**
	 * END Wholesaler
	 */
	
}
