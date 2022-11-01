package fr.phonetastik.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AcceuilController {

	// mapping pour methode Get
	@GetMapping(value={"/home","/"})
	public String viewTemplate(Model model) {
		return "index";
	}
	



	// mapping pour methode Get
	@GetMapping("/acceuil")
	public String viewTemplateAcceuil(Model model) {
		return "acceuil";
	}
	

		
	
	

}
