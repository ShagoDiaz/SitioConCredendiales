package com.edutecno.SitioProtegidoConCredenciales.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@GetMapping("/admin")
	public ModelAndView home(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView("admin");
		return modelAndView;
	}
}
