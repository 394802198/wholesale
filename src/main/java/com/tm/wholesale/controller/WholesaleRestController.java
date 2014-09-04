package com.tm.wholesale.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tm.wholesale.model.JSONBean;
import com.tm.wholesale.model.Page;
import com.tm.wholesale.model.Wholesaler;
import com.tm.wholesale.service.WholesaleService;

@RestController
public class WholesaleRestController {
	
	private WholesaleService wholesaleService;

	@Autowired
	public WholesaleRestController(WholesaleService wholesaleService) {
		this.wholesaleService = wholesaleService;
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
		
		Wholesaler wholesalerSession = (Wholesaler) req.getSession().getAttribute("wholesalerSession");
		if(wholesalerSession!=null && wholesalerSession.getWholesaler_id()==null){
			page.getParams().put("where", "query_by_wholesaler_id");
			page.getParams().put("id", wholesalerSession.getId());
		}
		
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
	
	/**
	 * END Wholesaler
	 */
	
}
