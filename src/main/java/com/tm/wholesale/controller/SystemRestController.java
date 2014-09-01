package com.tm.wholesale.controller;

import java.util.List;

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
import com.tm.wholesale.model.Wholesaler;
import com.tm.wholesale.service.MaterialService;
import com.tm.wholesale.service.SystemService;
import com.tm.wholesale.validation.ManagerLoginValiatedMark;

@RestController
public class SystemRestController {
	
	private MaterialService MaterialService;
	private SystemService SystemService;

	@Autowired
	public SystemRestController(MaterialService MaterialService,
			SystemService SystemService) {
		this.MaterialService = MaterialService;
		this.SystemService = SystemService;
	}
	
	/**
	 * BEGIN Login
	 */
	
	@RequestMapping(value = "/management/sign-in", method = RequestMethod.POST)
	public JSONBean<Manager> login(
			@Validated(ManagerLoginValiatedMark.class) Manager manager, BindingResult result,
			HttpSession session) {
		
		JSONBean<Manager> json = new JSONBean<Manager>();
		json.setModel(manager);

		if (result.hasErrors()) {
			json.setJSONErrorMap(result);
			return json;
		}
		
		manager.getParams().put("login_name", manager.getLogin_name());
		manager.getParams().put("password", manager.getPassword());
		
		Manager managerSession = this.SystemService.queryManager(manager);

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
		
		this.SystemService.queryWholesalerByPage(page);

		return page;
	}

	@RequestMapping(value = "/management/wholesale/wholesaler/remove/{id}", method=RequestMethod.POST)
	public JSONBean<Wholesaler> doWholesaleRemove(Model model,
			@PathVariable("id") Integer id,
			RedirectAttributes attr) {
		
		JSONBean<Wholesaler> json = new JSONBean<Wholesaler>();
		
		this.SystemService.removeWholesalerById(id);
		
		json.getSuccessMap().put("alert-success", "Successfully remove wholesaler account.");

		return json;
	}
	
	/**
	 * END Wholesaler
	 */

}
