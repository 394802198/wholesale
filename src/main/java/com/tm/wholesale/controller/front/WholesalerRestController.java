package com.tm.wholesale.controller.front;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tm.wholesale.model.JSONBean;
import com.tm.wholesale.model.Page;
import com.tm.wholesale.model.Wholesaler;
import com.tm.wholesale.service.back.WholesalerServiceBack;
import com.tm.wholesale.validation.ManagerLoginValidatedMark;
import com.tm.wholesale.validation.WholesalerChangePasswordValidatedMark;
import com.tm.wholesale.validation.WholesalerCreateValidatedMark;
import com.tm.wholesale.validation.WholesalerEditValidatedMark;
import com.tm.wholesale.validation.WholesalerLoginValidatedMark;

@RestController
public class WholesalerRestController {
	
	private WholesalerServiceBack wholesalerService;
	
	@Autowired
	public WholesalerRestController(WholesalerServiceBack wholesalerService) {
		this.wholesalerService = wholesalerService;
	}
	
	@RequestMapping(value = "/sign-in", method = RequestMethod.POST)
	public JSONBean<Wholesaler> doSignin(
			@Validated(WholesalerLoginValidatedMark.class) Wholesaler wholesaler, BindingResult result,
			HttpSession session) {
		
		JSONBean<Wholesaler> json = new JSONBean<Wholesaler>();
		json.setModel(wholesaler);

		if (result.hasErrors()) {
			json.setJSONErrorMap(result);
			return json;
		}
		
		wholesaler.getParams().put("login_name", wholesaler.getLogin_name());
		wholesaler.getParams().put("login_password", wholesaler.getLogin_password());
		
		Wholesaler wholesalerSession = this.wholesalerService.queryWholesaler(wholesaler);

		if (wholesalerSession == null) {
			json.getErrorMap().put("alert-error", "Incorrect account or password");
			return json;
		}
		
		session.setAttribute("wholesalerSession", wholesalerSession);
		
		json.setUrl("/index/redirect");

		return json;
	}
	
	@RequestMapping(value = "/system/user/view/{pageNo}")
	public Page<Wholesaler> systemUserView(
			@PathVariable("pageNo") int pageNo, HttpSession session){
		
		Wholesaler wholesalerSession = (Wholesaler) session.getAttribute("wholesalerSession");
		
		Page<Wholesaler> page = new Page<Wholesaler>();
		page.setPageNo(pageNo);
		page.setPageSize(30);
		page.getParams().put("orderby", "order by id");
		
		page.getParams().put("where", "query_wholesale_users");
		page.getParams().put("company_id", wholesalerSession.getCompany_id());
		
		this.wholesalerService.queryWholesalersByPage(page);
		
		return page;
	}
	
	@RequestMapping(value = "/system/user/create", method = RequestMethod.POST)
	public JSONBean<Wholesaler> systemUserCreate(
			@Validated(WholesalerCreateValidatedMark.class) Wholesaler wholesaler, BindingResult result, 
			HttpSession session) {
		
		JSONBean<Wholesaler> json = new JSONBean<Wholesaler>();
		json.setModel(wholesaler);
		
		if (result.hasErrors()) {
			json.setJSONErrorMap(result);
			return json;
		}

		wholesaler.getParams().put("login_name", wholesaler.getLogin_name());
		
		int count = this.wholesalerService.queryExistWholesaler(wholesaler);
		if (count > 0) {
			json.getErrorMap().put("login_name", "duplicate");
			return json;
		}
		
		Wholesaler wholesalerSession = (Wholesaler) session.getAttribute("wholesalerSession");
		
		wholesaler.setCompany_name(wholesalerSession.getCompany_name());
		wholesaler.setCompany_id(wholesalerSession.getCompany_id());
		
		this.wholesalerService.createWholesaler(wholesaler);
		
		json.setUrl("/system/user/view");
		
		return json;
	}
	
	@RequestMapping(value = "/system/user/edit/{id}/query")
	public Wholesaler systemUserEditQuery(
			@PathVariable("id") int id,
			HttpSession session) {
		
		Wholesaler wholesalerSession = (Wholesaler) session.getAttribute("wholesalerSession");
		
		Wholesaler wQ = new Wholesaler();
		wQ.getParams().put("id", id);
		if (id != wholesalerSession.getId())
			wQ.getParams().put("company_id", wholesalerSession.getCompany_id());

		
		Wholesaler w = this.wholesalerService.queryWholesaler(wQ);
		
		return w;
	}
	
	@RequestMapping(value = "/system/user/edit", method = RequestMethod.POST)
	public JSONBean<Wholesaler> systemUserEdit(
			@Validated(WholesalerEditValidatedMark.class) Wholesaler wholesaler, BindingResult result, 
			HttpSession session) {
		
		Wholesaler wholesalerSession = (Wholesaler) session.getAttribute("wholesalerSession");
		
		JSONBean<Wholesaler> json = new JSONBean<Wholesaler>();
		json.setModel(wholesaler);
		
		if (result.hasErrors()) {
			json.setJSONErrorMap(result);
			return json;
		}

		wholesaler.getParams().put("id_false", wholesaler.getId());
		wholesaler.getParams().put("login_name", wholesaler.getLogin_name());
		
		int count = this.wholesalerService.queryExistWholesaler(wholesaler);
		if (count > 0) {
			json.getErrorMap().put("login_name", "duplicate");
			return json;
		}
		
		wholesaler.getParams().put("id", wholesaler.getId());
		if (wholesaler.getId().intValue() != wholesalerSession.getId().intValue()) {
			System.out.println("wholesaler.getId(): " + wholesaler.getId());
			System.out.println("wholesalerSession.getId(): " + wholesalerSession.getId());
			wholesaler.getParams().put("company_id", wholesalerSession.getCompany_id());
		} else {
			wholesalerSession.setName(wholesaler.getName());
		}
			
		
		this.wholesalerService.editWholesaler(wholesaler);
		
		json.setUrl("/system/user/view");
		
		return json;
	}
	
	@RequestMapping(value = "/system/user/change-password", method = RequestMethod.POST)
	public JSONBean<Wholesaler> systemUserPasswordChange(
			@Validated(WholesalerChangePasswordValidatedMark.class) Wholesaler wholesaler, BindingResult result, 
			HttpSession session) {
		
		Wholesaler wholesalerSession = (Wholesaler) session.getAttribute("wholesalerSession");
		
		JSONBean<Wholesaler> json = new JSONBean<Wholesaler>();
		json.setModel(wholesaler);
		
		if (result.hasErrors()) {
			json.setJSONErrorMap(result);
			return json;
		}

		if (!wholesaler.getLogin_password().equals(wholesaler.getConfirm_password())) {
			json.getErrorMap().put("login_password", "new password and confirm password is different");
			return json;
		}
		
		if (!wholesaler.getOld_password().equals(wholesalerSession.getLogin_password())) {
			json.getErrorMap().put("old_password", "old password is wrong");
			return json;
		}
		
		wholesaler.getParams().put("id", wholesalerSession.getId());
			
		this.wholesalerService.editWholesaler(wholesaler);
		
		wholesalerSession.setLogin_password(wholesaler.getLogin_password());
		json.setUrl("/system/user/change-password/redirect");
		
		return json;
	}
}
