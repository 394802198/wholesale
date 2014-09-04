package com.tm.wholesale.controller;

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

import com.tm.wholesale.model.Manager;
import com.tm.wholesale.service.SystemService;

@Controller
public class SystemController {
	
	private SystemService SystemService;

	@Autowired
	public SystemController(SystemService SystemService) {
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
	 * END Login
	 */

	/**
	 * BEGIN Manager
	 */

	@RequestMapping(value = "/management/system/manager/create")
	public String toManagerCreate(Model model) {
		
		model.addAttribute("manager", new Manager());
		model.addAttribute("panelheading", "Manager Create");
		model.addAttribute("action", "/management/system/manager/create");

		return "management/system/manager";
	}

	@RequestMapping(value = "/management/system/manager/create", method=RequestMethod.POST)
	public String doManagerCreate(Model model,
			@ModelAttribute("manager") Manager manager,
			RedirectAttributes attr,
			HttpServletRequest req) {
		
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < manager.getAuthArray().length; i++) {
			buff.append(manager.getAuthArray()[i]);
			if(i<manager.getAuthArray().length-1){
				buff.append(",");
			}
		}
		
		// Set authorizations
		manager.setAuth(String.valueOf(buff));
		
		this.SystemService.createManager(manager);

		attr.addFlashAttribute("success", "Successfully create new manager account.");

		return "redirect:/management/system/manager/view";
	}

	@RequestMapping(value = "/management/system/manager/edit/{id}")
	public String toManagerEdit(Model model,
			@PathVariable("id") Integer id) {
		
		Manager manager = new Manager();
		manager.getParams().put("id", id);
		manager = this.SystemService.queryManagers(manager).get(0);
		
		// iterating auths of this wholesaler
		if (manager.getAuth() != null && !"".equals(manager.getAuth())) {
			manager.setAuthArray(manager.getAuth().split(","));
		}
		
		model.addAttribute("manager", manager);
		model.addAttribute("panelheading", "Manager Update");
		model.addAttribute("action", "/management/system/manager/edit");

		return "management/system/manager";
	}

	@RequestMapping(value = "/management/system/manager/edit", method=RequestMethod.POST)
	public String doManagerEdit(Model model,
			@ModelAttribute("manager") Manager manager,
			RedirectAttributes attr) {
		
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < manager.getAuthArray().length; i++) {
			buff.append(manager.getAuthArray()[i]);
			if(i<manager.getAuthArray().length-1){
				buff.append(",");
			}
		}
		
		manager.setAuth(String.valueOf(buff));
		manager.getParams().put("id", manager.getId());
		
		this.SystemService.editManager(manager);

		attr.addFlashAttribute("success", "Successfully update manager's details.");

		return "redirect:/management/system/manager/view";
	}

	@RequestMapping(value = "/management/system/manager/view")
	public String toManagerView(Model model) {
		return "management/system/manager-view";
	}
	
	/**
	 * END Manager
	 */
	

}
