package fr.phonetastik.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import fr.phonetastik.model.Marque;
import fr.phonetastik.service.MarqueService;

@Controller
public class AdministrationController {

	@Autowired
	MarqueService marqueService;

	@GetMapping(value = "/administration")
	public String viewTemplate(Model model) {
		return "administration";
	}

	@GetMapping(value = "/administration/creationMarque")
	public String viewTemplateCreation(@Valid Marque marque) {
		return "creationMarque";
	}

	@GetMapping(value = "listeMarques")
	public String viewTemplateListeMarques(Model model) {

		model.addAttribute("listeMarques", marqueService.lister());
		return "listeMarques";
	}

	@PostMapping(value = "/administration/creationMarque")
	public String viewTemplateCreationPost(@Valid Marque marque, Model model) {

		try {

			if (marqueService.findMarqueByNom(marque.getNom()).isEmpty()) {
				marqueService.enregistrer(marque);
				return "listeMarques";

			} else {
				model.addAttribute("erreur", "Il ne peut y avoir qu'une marque pour ce nom");
				return "redirect:/administration/creationMarque";
			}

		} catch (Exception e) {
			model.addAttribute("erreurVue", e.toString());
			return "erreurVue";
		}

	}

}
