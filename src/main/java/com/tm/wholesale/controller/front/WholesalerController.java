package com.tm.wholesale.controller.front;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class WholesalerController {

	@RequestMapping(value = { "", "/sign-in" })
	public String signin(Model model) {
		model.addAttribute("title", "Sign in - Wholesale Portal");
		return "wholesale/sign-in";
	}
	
	@RequestMapping("/index/redirect")
	public String indexRedirect(RedirectAttributes attr) {
		attr.addFlashAttribute("success", "Welcome to Wholesale Portal System of Total Moblie Solution.");
		return "redirect:/index";
	}
	
	@RequestMapping("/index")
	public String index(Model model){
		model.addAttribute("title", "Home - Wholesale Portal");
		return "wholesale/index";
	}
	
	@RequestMapping(value = "/signout")
	public String signout(HttpSession session) {
		session.removeAttribute("wholesalerSession");
		return "redirect:/sign-in";
	}
	
	@RequestMapping("/system/user/view")
	public String systemUserView(Model model) {
		model.addAttribute("title", "View User - System");
		return "wholesale/system/user-view";
	}
	
	@RequestMapping("/system/user/create")
	public String systemUserCreate(Model model) {
		model.addAttribute("title", "Create User - System");
		model.addAttribute("action", "create");
		return "wholesale/system/user";
	}
	
	@RequestMapping("/system/user/edit/{id}")
	public String systemUserEdit(Model model,
			@PathVariable("id") int id) {
		model.addAttribute("title", "Create Edit - System");
		model.addAttribute("action", "edit");
		model.addAttribute("id", id);
		return "wholesale/system/user";
	}
	
	@RequestMapping("/system/user/change-password")
	public String systemUserChangePassword(Model model) {
		model.addAttribute("title", "Change Password - System");
		return "wholesale/system/user-change-password";
	}
	
	@RequestMapping("/system/user/change-password/redirect")
	public String systemUserChangePasswordRedirect(RedirectAttributes attr) {
		attr.addFlashAttribute("success", "Change passowrd is successful.");
		return "redirect:/system/user/change-password";
	}
}
