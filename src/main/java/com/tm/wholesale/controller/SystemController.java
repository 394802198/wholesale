package com.tm.wholesale.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tm.wholesale.model.Wholesaler;
import com.tm.wholesale.service.MaterialService;
import com.tm.wholesale.service.SystemService;

@Controller
public class SystemController {
	
	private MaterialService MaterialService;
	private SystemService SystemService;

	@Autowired
	public SystemController(MaterialService MaterialService, SystemService SystemService) {
		this.MaterialService = MaterialService;
		this.SystemService = SystemService;
	}
	
	/**
	 * BEGIN Login
	 */

	@RequestMapping(value = { "/management", "/management/sign-in" })
	public String signin(Model model) {
		model.addAttribute("title", "Wholesale Management System");
		return "management/sign-in";
	}

	@RequestMapping(value = "/management/signout")
	public String signout(HttpSession session) {
		session.removeAttribute("managerSession");
		return "redirect:/management/sign-in";
	}
	
	@RequestMapping("/management/index/redirect")
	public String indexRedirect(RedirectAttributes attr) {
		attr.addFlashAttribute("success", "Welcome to Wholesale Management System.");
		return "redirect:/management/index";
	}

	@RequestMapping(value = "/management/index")
	public String index(Model model) {
		model.addAttribute("title", "Home - Wholesale Management System");
		return "management/index";
	}
	
	/**
	 * BEGIN Login
	 */
	
	/**
	 * BEGIN Wholesaler
	 */

	@RequestMapping(value = "/management/system/wholesaler/create")
	public String toWholesaleCreate(Model model) {
		
		Wholesaler wholesalerQuery = new Wholesaler();
		wholesalerQuery.getParams().put("where", "query_primary_wholesaler");

		model.addAttribute("primaryWholesalers", this.SystemService.queryWholesalers(wholesalerQuery));
		model.addAttribute("wholesaler", new Wholesaler());
		model.addAttribute("panelheading", "Wholesale Create");
		model.addAttribute("action", "/management/system/wholesaler/create");

		return "management/system/wholesaler";
	}

	@RequestMapping(value = "/management/system/wholesaler/create", method=RequestMethod.POST)
	public String doWholesaleCreate(Model model,
			@ModelAttribute("wholesaler") Wholesaler wholesaler,
			RedirectAttributes attr,
			HttpServletRequest req) {
		
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < wholesaler.getAuthArray().length; i++) {
			buff.append(wholesaler.getAuthArray()[i]);
			if(i<wholesaler.getAuthArray().length-1){
				buff.append(",");
			}
		}
		
		// Set authorizations
		wholesaler.setAuth(String.valueOf(buff));
		
		Wholesaler wholesalerSession = (Wholesaler) req.getSession().getAttribute("wholesalerSession");
		
		// If not create by user then use current wholesaler's company name as new wholesaler's company name
		if(wholesalerSession!=null){
			wholesaler.setCompany_name(wholesalerSession.getCompany_name());
			wholesaler.setWholesaler_id(wholesalerSession.getId());
		} else {
			Wholesaler wholesalerQuery = new Wholesaler();
			wholesalerQuery.getParams().put("where", "query_primary_wholesaler_by_company_name");
			wholesalerQuery.getParams().put("company_name", wholesaler.getCompany_name());
			List<Wholesaler> wholesalers = this.SystemService.queryWholesalers(wholesalerQuery);
			if(wholesalers!=null && wholesalers.size()>0){
				wholesaler.setWholesaler_id(wholesalers.get(0).getId());
			}
		}
		
		this.SystemService.createWholesaler(wholesaler);

		attr.addFlashAttribute("success", "Successfully create new wholesaler account.");

		return "redirect:/management/system/wholesaler/view";
	}

	@RequestMapping(value = "/management/system/wholesaler/edit/{id}")
	public String toWholesaleEdit(Model model,
			@PathVariable("id") Integer id) {
		
		Wholesaler wholesaler = new Wholesaler();
		wholesaler.getParams().put("id", id);
		wholesaler = this.SystemService.queryWholesalers(wholesaler).get(0);
		
		// iterating auths of this wholesaler
		if (wholesaler.getAuth() != null && !"".equals(wholesaler.getAuth())) {
			wholesaler.setAuthArray(wholesaler.getAuth().split(","));
		}
		
		model.addAttribute("wholesaler", wholesaler);
		model.addAttribute("panelheading", "Wholesale Update");
		model.addAttribute("action", "/management/system/wholesaler/edit");

		return "management/system/wholesaler";
	}

	@RequestMapping(value = "/management/system/wholesaler/edit", method=RequestMethod.POST)
	public String doWholesaleEdit(Model model,
			@ModelAttribute("wholesaler") Wholesaler wholesaler,
			RedirectAttributes attr) {
		
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < wholesaler.getAuthArray().length; i++) {
			buff.append(wholesaler.getAuthArray()[i]);
			if(i<wholesaler.getAuthArray().length-1){
				buff.append(",");
			}
		}
		
		wholesaler.setAuth(String.valueOf(buff));
		wholesaler.getParams().put("id", wholesaler.getId());
		
		this.SystemService.editWholesaler(wholesaler);

		attr.addFlashAttribute("success", "Successfully update wholesaler details.");

		return "redirect:/management/system/wholesaler/view";
	}

	@RequestMapping(value = "/management/system/wholesaler/view")
	public String toWholesaleView(Model model) {
		return "management/system/wholesaler-view";
	}
	
	/**
	 * END Wholesaler
	 */

}
