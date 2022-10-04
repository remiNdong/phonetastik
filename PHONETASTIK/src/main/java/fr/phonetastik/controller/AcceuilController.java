package fr.phonetastik.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AcceuilController {

	// mapping pour methode Get
	@GetMapping("/home")
	public String viewTemplate(Model model) {
		// objet model permet d'inserer des attributs dans la vue et les recuperer
		return "index";
	}

	// mapping pour methode Get
	@GetMapping("/acceuil")
	public String viewTemplateAcceuil(Model model) {
		// objet model permet d'inserer des attributs dans la vue et les recuperer
		return "acceuil";
	}

}
