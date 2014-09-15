package com.tm.wholesale.controller.back;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tm.wholesale.model.JSONBean;
import com.tm.wholesale.model.Manager;
import com.tm.wholesale.model.Page;
import com.tm.wholesale.service.back.SystemServiceBack;
import com.tm.wholesale.validation.ManagerLoginValidatedMark;

@RestController
public class SystemRestControllerBack {
	
	private SystemServiceBack systemService;

	@Autowired
	public SystemRestControllerBack(SystemServiceBack systemService) {
		this.systemService = systemService;
	}
	
	/**
	 * BEGIN Login
	 */
	
	@RequestMapping(value = "/management/sign-in", method = RequestMethod.POST)
	public JSONBean<Manager> doSignin(
			@Validated(ManagerLoginValidatedMark.class) Manager manager, BindingResult result,
			HttpSession session) {
		
		JSONBean<Manager> json = new JSONBean<Manager>();
		json.setModel(manager);

		if (result.hasErrors()) {
			json.setJSONErrorMap(result);
			return json;
		}
		
		manager.getParams().put("login_name", manager.getLogin_name());
		manager.getParams().put("password", manager.getPassword());
		
		Manager managerSession = this.systemService.queryManager(manager);

		if (managerSession == null) {
			json.getErrorMap().put("alert-error", "Incorrect account or password");
			return json;
		}
		
		session.setAttribute("managerSession", managerSession);
		
		String url = "/management/index/redirect";
		json.setUrl(url);

		return json;
	}
	
	/**
	 * END Login
	 */
	
	/**
	 * BEGIN Manager
	 */

	@RequestMapping(value = "/management/system/manager/view/{pageNo}")
	public Page<Manager> toManagerView(Model model,
			@PathVariable("pageNo") Integer pageNo,
			HttpServletRequest req) {
		
		Page<Manager> page = new Page<Manager>();
		page.setPageNo(pageNo);
		page.setPageSize(30);
		
		this.systemService.queryManagerByPage(page);

		return page;
	}

	@RequestMapping(value = "/management/system/manager/remove/{id}", method=RequestMethod.POST)
	public JSONBean<Manager> doWholesaleRemove(Model model,
			@PathVariable("id") Integer id,
			RedirectAttributes attr) {
		
		JSONBean<Manager> json = new JSONBean<Manager>();
		
		this.systemService.removeManagerById(id);
		
		json.getSuccessMap().put("alert-success", "Successfully remove manager account.");

		return json;
	}
	
	/**
	 * END Manager
	 */

}
