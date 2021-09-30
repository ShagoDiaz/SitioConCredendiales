package com.edutecno.SitioProtegidoConCredenciales.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/errors")
public class ErrorController {

	@GetMapping("/403")
	public ModelAndView error403() {
		ModelAndView modelAndView = new ModelAndView("errors/403");
		return modelAndView;
	}
}
